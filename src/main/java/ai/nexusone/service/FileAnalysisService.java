package ai.nexusone.service;

import ai.nexusone.dto.FileAnalysisResult;
import ai.nexusone.dto.FileFinding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class FileAnalysisService {
    private final DockerfileAnalyzer dockerfileAnalyzer;
    private final PropertiesAnalyzer propertiesAnalyzer;
    private final YamlAnalyzer yamlAnalyzer;

    @Value("${nexusone.repositories.path:C:/NexusOne-Sample-Applications}")
    private String repositoryBasePath;

    public FileAnalysisService(DockerfileAnalyzer dockerfileAnalyzer,
                               PropertiesAnalyzer propertiesAnalyzer,
                               YamlAnalyzer yamlAnalyzer) {
        this.dockerfileAnalyzer = dockerfileAnalyzer;
        this.propertiesAnalyzer = propertiesAnalyzer;
        this.yamlAnalyzer = yamlAnalyzer;
    }

    public FileAnalysisResult analyzeRepository(String repoName) throws IOException {
        validateRepositoryName(repoName);
        Path base = Paths.get(repositoryBasePath).toAbsolutePath().normalize();
        Path repo = base.resolve(repoName).normalize();
        if (!repo.startsWith(base)) throw new IllegalArgumentException("Invalid repository path.");
        if (!Files.isDirectory(repo)) throw new IllegalArgumentException("Repository not found: " + repo);

        List<FileFinding> findings = new ArrayList<>();
        int analyzed = 0;
        try (Stream<Path> paths = Files.walk(repo)) {
            for (Path file : paths.filter(Files::isRegularFile).filter(this::supported).toList()) {
                String relative = repo.relativize(file).toString().replace('\\', '/');
                String name = file.getFileName().toString().toLowerCase(Locale.ROOT);
                if ("dockerfile".equals(name)) findings.addAll(dockerfileAnalyzer.analyze(file, relative));
                else if (name.endsWith(".properties")) findings.addAll(propertiesAnalyzer.analyze(file, relative));
                else findings.addAll(yamlAnalyzer.analyze(file, relative));
                analyzed++;
            }
        }

        findings.sort(Comparator.comparingInt((FileFinding f) -> weight(f.getSeverity()))
                .reversed().thenComparing(FileFinding::getFile));
        return build(repoName, repo, analyzed, findings);
    }

    private boolean supported(Path file) {
        String n = file.getFileName().toString().toLowerCase(Locale.ROOT);
        return "dockerfile".equals(n) || n.endsWith(".properties")
                || n.endsWith(".yml") || n.endsWith(".yaml");
    }

    private FileAnalysisResult build(String name, Path path, int analyzed, List<FileFinding> findings) {
        int critical = count(findings, "CRITICAL");
        int high = count(findings, "HIGH");
        int medium = count(findings, "MEDIUM");
        int low = count(findings, "LOW");
        int score = Math.min(100, critical * 40 + high * 25 + medium * 10 + low * 3);
        String severity = critical > 0 || score >= 70 ? "CRITICAL"
                : score >= 51 ? "HIGH" : score >= 21 ? "MEDIUM" : "LOW";

        FileAnalysisResult result = new FileAnalysisResult();
        result.setRepositoryName(name);
        result.setRepositoryPath(path.toString());
        result.setFilesAnalyzed(analyzed);
        result.setTotalFindings(findings.size());
        result.setCriticalFindings(critical);
        result.setHighFindings(high);
        result.setMediumFindings(medium);
        result.setLowFindings(low);
        result.setRiskScore(score);
        result.setOverallSeverity(severity);
        result.setFindings(findings);
        return result;
    }

    private int count(List<FileFinding> findings, String severity) {
        return (int) findings.stream().filter(f -> severity.equalsIgnoreCase(f.getSeverity())).count();
    }

    private int weight(String severity) {
        return switch (severity.toUpperCase(Locale.ROOT)) {
            case "CRITICAL" -> 4;
            case "HIGH" -> 3;
            case "MEDIUM" -> 2;
            default -> 1;
        };
    }

    private void validateRepositoryName(String repoName) {
        if (repoName == null || repoName.isBlank())
            throw new IllegalArgumentException("Repository name is required.");
        if (!repoName.matches("[a-zA-Z0-9._-]+"))
            throw new IllegalArgumentException("Repository name contains invalid characters.");
    }
}
