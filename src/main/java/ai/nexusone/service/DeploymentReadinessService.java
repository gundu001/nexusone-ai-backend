package ai.nexusone.service;

import ai.nexusone.dto.DeploymentReadinessResult;
import ai.nexusone.entity.DeploymentReadinessEntity;
import ai.nexusone.repository.DeploymentReadinessRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class DeploymentReadinessService {
    private static final long MAX_TEXT_FILE_BYTES = 2_000_000L;

    private final DeploymentReadinessRepository repository;
    private final ObjectMapper objectMapper;
    private final Path repositoriesRoot;

    public DeploymentReadinessService(
            DeploymentReadinessRepository repository,
            ObjectMapper objectMapper,
            @Value("${nexusone.repositories.path:C:/NexusOne-Sample-Applications}") String repositoriesPath
    ) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        this.repositoriesRoot = Paths.get(repositoriesPath).toAbsolutePath().normalize();
    }

    @Transactional
    public DeploymentReadinessResult analyze(String repoName) {
        String validatedName = validateRepositoryName(repoName);
        Path repositoryPath = resolveRepositoryPath(validatedName);

        List<Path> files = listFiles(repositoryPath);
        List<String> strengths = new ArrayList<>();
        List<String> warnings = new ArrayList<>();
        List<String> recommendations = new ArrayList<>();
        int score = 0;

        if (hasFile(files, "dockerfile")) {
            score += 20;
            strengths.add("Dockerfile found.");
        } else {
            warnings.add("Dockerfile was not found.");
            recommendations.add("Add and validate a production-ready Dockerfile.");
        }

        if (hasKubernetesManifest(files)) {
            score += 20;
            strengths.add("Kubernetes deployment manifest found.");
        } else {
            warnings.add("Kubernetes deployment manifest was not found.");
            recommendations.add("Add Kubernetes deployment and service manifests.");
        }

        if (hasHealthConfiguration(files)) {
            score += 15;
            strengths.add("Health-check configuration or endpoint found.");
        } else {
            warnings.add("Health-check configuration was not detected.");
            recommendations.add("Expose a health endpoint and configure liveness and readiness probes.");
        }

        if (hasFile(files, "readme.md") || hasFile(files, "readme.txt") || hasFile(files, "readme")) {
            score += 10;
            strengths.add("README documentation found.");
        } else {
            warnings.add("README documentation was not found.");
            recommendations.add("Add build, deployment and rollback instructions to a README file.");
        }

        if (hasPipeline(files)) {
            score += 20;
            strengths.add("CI/CD pipeline configuration found.");
        } else {
            warnings.add("CI/CD pipeline configuration was not found.");
            recommendations.add("Add a Jenkinsfile, GitHub Actions workflow, GitLab CI file or Azure Pipelines file.");
        }

        if (hasRollbackStrategy(files)) {
            score += 15;
            strengths.add("Rollback strategy evidence found.");
        } else {
            warnings.add("Rollback strategy was not detected.");
            recommendations.add("Document and automate a tested rollback strategy.");
        }

        DeploymentReadinessEntity entity = new DeploymentReadinessEntity();
        entity.setRepositoryName(validatedName);
        entity.setRepositoryPath(repositoryPath.toString());
        entity.setReadinessScore(score);
        entity.setDeploymentDecision(resolveDecision(score));
        entity.setStrengths(toJson(strengths));
        entity.setWarnings(toJson(warnings));
        entity.setRecommendations(toJson(recommendations));
        entity.setGeneratedAt(LocalDateTime.now());

        return toResult(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public DeploymentReadinessResult latest(String repoName) {
        String validatedName = validateRepositoryName(repoName);
        return repository.findTopByRepositoryNameIgnoreCaseOrderByGeneratedAtDesc(validatedName)
                .map(this::toResult)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No deployment-readiness result found for repository: " + validatedName));
    }

    @Transactional(readOnly = true)
    public List<DeploymentReadinessResult> history(String repoName) {
        if (repoName == null || repoName.isBlank()) {
            return repository.findTop20ByOrderByGeneratedAtDesc().stream().map(this::toResult).toList();
        }
        return repository.findTop20ByRepositoryNameIgnoreCaseOrderByGeneratedAtDesc(repoName.trim())
                .stream().map(this::toResult).toList();
    }

    private String validateRepositoryName(String repoName) {
        if (repoName == null || repoName.isBlank()) {
            throw new IllegalArgumentException("repoName is required.");
        }
        String name = repoName.trim();
        if (name.contains("..") || name.contains("/") || name.contains("\\")) {
            throw new IllegalArgumentException("repoName contains invalid path characters.");
        }
        return name;
    }

    private Path resolveRepositoryPath(String repoName) {
        Path exact = repositoriesRoot.resolve(repoName).normalize();
        if (exact.startsWith(repositoriesRoot) && Files.isDirectory(exact)) return exact;

        try (Stream<Path> children = Files.list(repositoriesRoot)) {
            return children
                    .filter(Files::isDirectory)
                    .filter(path -> path.getFileName().toString().equalsIgnoreCase(repoName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Repository directory not found under " + repositoriesRoot + ": " + repoName));
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to access repositories path: " + repositoriesRoot, exception);
        }
    }

    private List<Path> listFiles(Path root) {
        try (Stream<Path> stream = Files.walk(root, 12)) {
            return stream.filter(Files::isRegularFile).toList();
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to scan repository: " + root, exception);
        }
    }

    private boolean hasFile(List<Path> files, String fileName) {
        return files.stream().anyMatch(path -> path.getFileName().toString().equalsIgnoreCase(fileName));
    }

    private boolean hasKubernetesManifest(List<Path> files) {
        return files.stream()
                .filter(this::isYaml)
                .anyMatch(path -> path.toString().toLowerCase(Locale.ROOT).contains("k8s")
                        || path.toString().toLowerCase(Locale.ROOT).contains("kubernetes")
                        || fileContains(path, "kind: Deployment")
                        || fileContains(path, "kind: StatefulSet"));
    }

    private boolean hasPipeline(List<Path> files) {
        return files.stream().anyMatch(path -> {
            String name = path.getFileName().toString().toLowerCase(Locale.ROOT);
            String full = path.toString().replace('\\', '/').toLowerCase(Locale.ROOT);
            return name.equals("jenkinsfile")
                    || name.equals(".gitlab-ci.yml")
                    || name.equals(".gitlab-ci.yaml")
                    || name.equals("azure-pipelines.yml")
                    || name.equals("azure-pipelines.yaml")
                    || full.contains("/.github/workflows/");
        });
    }

    private boolean hasHealthConfiguration(List<Path> files) {
        return files.stream()
                .filter(this::isTextCandidate)
                .anyMatch(path -> fileContains(path, "readinessProbe")
                        || fileContains(path, "livenessProbe")
                        || fileContains(path, "/actuator/health")
                        || fileContains(path, "management.endpoints.web.exposure"));
    }

    private boolean hasRollbackStrategy(List<Path> files) {
        return files.stream()
                .filter(this::isTextCandidate)
                .anyMatch(path -> fileContains(path, "rollback")
                        || fileContains(path, "roll back")
                        || fileContains(path, "helm rollback"));
    }

    private boolean isYaml(Path path) {
        String name = path.getFileName().toString().toLowerCase(Locale.ROOT);
        return name.endsWith(".yml") || name.endsWith(".yaml");
    }

    private boolean isTextCandidate(Path path) {
        String name = path.getFileName().toString().toLowerCase(Locale.ROOT);
        return name.endsWith(".java") || name.endsWith(".properties") || name.endsWith(".yml")
                || name.endsWith(".yaml") || name.endsWith(".xml") || name.endsWith(".md")
                || name.endsWith(".txt") || name.equals("jenkinsfile");
    }

    private boolean fileContains(Path path, String value) {
        try {
            if (Files.size(path) > MAX_TEXT_FILE_BYTES) return false;
            String content = Files.readString(path, StandardCharsets.UTF_8);
            return content.toLowerCase(Locale.ROOT).contains(value.toLowerCase(Locale.ROOT));
        } catch (IOException | RuntimeException ignored) {
            return false;
        }
    }

    private String resolveDecision(int score) {
        if (score >= 80) return "READY_TO_DEPLOY";
        if (score >= 60) return "DEPLOY_WITH_CAUTION";
        if (score >= 40) return "MANUAL_REVIEW_REQUIRED";
        return "NOT_READY_TO_DEPLOY";
    }

    private String toJson(List<String> values) {
        try {
            return objectMapper.writeValueAsString(values);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Unable to serialize deployment-readiness data.", exception);
        }
    }

    private List<String> fromJson(String raw) {
        if (raw == null || raw.isBlank()) return new ArrayList<>();
        try {
            return objectMapper.readValue(raw, new TypeReference<List<String>>() { });
        } catch (JsonProcessingException exception) {
            return List.of(raw);
        }
    }

    private DeploymentReadinessResult toResult(DeploymentReadinessEntity entity) {
        return new DeploymentReadinessResult(
                entity.getId(), entity.getRepositoryName(), entity.getRepositoryPath(),
                entity.getReadinessScore(), entity.getDeploymentDecision(),
                fromJson(entity.getStrengths()), fromJson(entity.getWarnings()),
                fromJson(entity.getRecommendations()), entity.getGeneratedAt());
    }
}
