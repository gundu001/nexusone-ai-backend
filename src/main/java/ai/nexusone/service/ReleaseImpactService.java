package ai.nexusone.service;

import ai.nexusone.dto.ReleaseImpactRequest;
import ai.nexusone.dto.ReleaseImpactResult;
import ai.nexusone.entity.ReleaseImpactEntity;
import ai.nexusone.repository.ReleaseImpactRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Service
public class ReleaseImpactService {
    private static final long MAX_FILE_SIZE=2_000_000L;
    private final ReleaseImpactRepository repository;
    private final ObjectMapper objectMapper;
    private final Path repositoriesRoot;

    public ReleaseImpactService(ReleaseImpactRepository repository, ObjectMapper objectMapper,
                                @Value("${nexusone.repositories.path:C:/NexusOne-Sample-Applications}") String root) {
        this.repository=repository; this.objectMapper=objectMapper;
        this.repositoriesRoot=Paths.get(root).toAbsolutePath().normalize();
    }

    @Transactional
    public ReleaseImpactResult analyze(ReleaseImpactRequest request) {
        if(request==null) throw new IllegalArgumentException("Request body is required.");
        String repoName=validateName(request.getRepositoryName());
        Path source=resolveRepository(repoName);
        List<String> changed=normalizeChangedFiles(request.getChangedFiles());
        List<String> impacted=new ArrayList<>();
        List<String> reasons=new ArrayList<>();

        String sourceToken=normalizedToken(repoName);
        for(Path candidate:listRepositoryDirectories()) {
            if(candidate.equals(source)) continue;
            List<Path> files=listTextFiles(candidate);
            boolean referencesSource=files.stream().anyMatch(p->containsToken(p, sourceToken));
            if(referencesSource) {
                String candidateName=candidate.getFileName().toString();
                impacted.add(candidateName);
                reasons.add(candidateName+" contains a reference to "+repoName+".");
            }
        }

        boolean apiChange=changed.stream().anyMatch(this::isApiChange);
        boolean databaseChange=changed.stream().anyMatch(this::isDatabaseChange);
        boolean configChange=changed.stream().anyMatch(this::isConfigChange);
        if(apiChange) reasons.add("Changed files include API or controller artifacts.");
        if(databaseChange) reasons.add("Changed files include database or migration artifacts.");
        if(configChange) reasons.add("Changed files include deployment or configuration artifacts.");
        if(changed.isEmpty()) reasons.add("No changed-file list was supplied; dependency impact is based on repository references only.");

        int score=Math.min(100, impacted.size()*20+(apiChange?25:0)+(databaseChange?25:0)+(configChange?15:0));
        List<String> recommendations=buildRecommendations(impacted,apiChange,databaseChange,configChange);

        ReleaseImpactEntity entity=new ReleaseImpactEntity();
        entity.setRepositoryName(repoName); entity.setChangedFiles(toJson(changed));
        entity.setImpactedServices(toJson(impacted)); entity.setImpactReasons(toJson(reasons));
        entity.setRecommendations(toJson(recommendations)); entity.setImpactScore(score);
        entity.setImpactLevel(level(score)); entity.setAnalyzedAt(LocalDateTime.now());
        return toResult(repository.save(entity));
    }

    @Transactional(readOnly=true)
    public ReleaseImpactResult latest(String repoName){
        String name=validateName(repoName);
        return repository.findTopByRepositoryNameIgnoreCaseOrderByAnalyzedAtDesc(name).map(this::toResult)
                .orElseThrow(()->new IllegalArgumentException("No release-impact result found for repository: "+name));
    }
    @Transactional(readOnly=true)
    public List<ReleaseImpactResult> history(String repoName){
        List<ReleaseImpactEntity> rows=(repoName==null||repoName.isBlank())
                ?repository.findTop20ByOrderByAnalyzedAtDesc()
                :repository.findTop20ByRepositoryNameIgnoreCaseOrderByAnalyzedAtDesc(validateName(repoName));
        return rows.stream().map(this::toResult).toList();
    }

    private List<String> normalizeChangedFiles(List<String> files){
        if(files==null) return List.of();
        return files.stream().filter(Objects::nonNull).map(String::trim).filter(s->!s.isBlank()).distinct().toList();
    }
    private String validateName(String name){
        if(name==null||name.isBlank()) throw new IllegalArgumentException("repositoryName is required.");
        String value=name.trim();
        if(value.contains("..")||value.contains("/")||value.contains("\\")) throw new IllegalArgumentException("Invalid repositoryName.");
        return value;
    }
    private Path resolveRepository(String name){
        return listRepositoryDirectories().stream().filter(p->p.getFileName().toString().equalsIgnoreCase(name)).findFirst()
                .orElseThrow(()->new IllegalArgumentException("Repository directory not found: "+name));
    }
    private List<Path> listRepositoryDirectories() {

        try (Stream<Path> stream =
                     Files.list(repositoriesRoot)) {

            return stream

                    .filter(Files::isDirectory)

                    .filter(path -> {

                        String folderName =
                                path.getFileName()
                                        .toString();

                        return !folderName.startsWith(".")
                                && !folderName.equalsIgnoreCase(".idea")
                                && !folderName.equalsIgnoreCase("target")
                                && !folderName.equalsIgnoreCase("node_modules")
                                && !folderName.equalsIgnoreCase(".git")
                                && !folderName.equalsIgnoreCase(".settings")
                                && !folderName.equalsIgnoreCase(".vscode");
                    })

                    .toList();

        } catch (IOException exception) {

            throw new IllegalStateException(
                    "Unable to access repositories path: "
                            + repositoriesRoot,
                    exception
            );
        }
    }
    private List<Path> listTextFiles(Path root){
        try(Stream<Path> s=Files.walk(root,12)){return s.filter(Files::isRegularFile).filter(this::isTextCandidate).toList();}
        catch(IOException e){throw new IllegalStateException("Unable to scan repository: "+root,e);}
    }
    private boolean isTextCandidate(Path p){
        String n=p.getFileName().toString().toLowerCase(Locale.ROOT);
        return n.endsWith(".java")||n.endsWith(".ts")||n.endsWith(".tsx")||n.endsWith(".js")||n.endsWith(".json")
                ||n.endsWith(".xml")||n.endsWith(".yml")||n.endsWith(".yaml")||n.endsWith(".properties")||n.endsWith(".md");
    }
    private String normalizedToken(String name){return name.toLowerCase(Locale.ROOT).replace("-","").replace("_","").replace(" ","");}
    private boolean containsToken(Path p,String token){
        try{if(Files.size(p)>MAX_FILE_SIZE)return false;String text=Files.readString(p,StandardCharsets.UTF_8).toLowerCase(Locale.ROOT);
            return normalizedToken(text).contains(token);}catch(IOException|RuntimeException e){return false;}
    }
    private boolean isApiChange(String f){String n=f.toLowerCase(Locale.ROOT);return n.contains("controller")||n.contains("api")||n.contains("openapi")||n.contains("swagger");}
    private boolean isDatabaseChange(String f){String n=f.toLowerCase(Locale.ROOT);return n.contains("migration")||n.contains("schema")||n.endsWith(".sql")||n.contains("entity");}
    private boolean isConfigChange(String f){String n=f.toLowerCase(Locale.ROOT);return n.endsWith(".yml")||n.endsWith(".yaml")||n.endsWith(".properties")||n.contains("dockerfile")||n.contains("jenkinsfile");}
    private List<String> buildRecommendations(List<String> impacted,boolean api,boolean db,boolean config){
        List<String> r=new ArrayList<>();
        if(!impacted.isEmpty())r.add("Run integration and regression tests for impacted services: "+String.join(", ",impacted)+".");
        if(api)r.add("Validate API backward compatibility and consumer contracts.");
        if(db)r.add("Review database migration compatibility and rollback scripts.");
        if(config)r.add("Validate configuration and deployment manifests in a non-production environment.");
        if(r.isEmpty())r.add("Run the standard build, unit-test and deployment validation workflow.");
        return r;
    }
    private String level(int score){return score>=75?"CRITICAL":score>=50?"HIGH":score>=25?"MEDIUM":"LOW";}
    private String toJson(List<String> v){try{return objectMapper.writeValueAsString(v);}catch(JsonProcessingException e){throw new IllegalStateException("Unable to serialize impact data.",e);}}
    private List<String> fromJson(String v){if(v==null||v.isBlank())return List.of();try{return objectMapper.readValue(v,new TypeReference<List<String>>(){});}catch(Exception e){return List.of(v);}}
    private ReleaseImpactResult toResult(ReleaseImpactEntity e){return new ReleaseImpactResult(e.getId(),e.getRepositoryName(),fromJson(e.getChangedFiles()),fromJson(e.getImpactedServices()),fromJson(e.getImpactReasons()),fromJson(e.getRecommendations()),e.getImpactScore(),e.getImpactLevel(),e.getAnalyzedAt());}
}
