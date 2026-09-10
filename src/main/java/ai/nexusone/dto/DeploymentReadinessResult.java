package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DeploymentReadinessResult {
    private Long id;
    private String repositoryName;
    private String repositoryPath;
    private Integer readinessScore;
    private String deploymentDecision;
    private List<String> strengths;
    private List<String> warnings;
    private List<String> recommendations;
    private LocalDateTime generatedAt;

    public DeploymentReadinessResult() {
    }

    public DeploymentReadinessResult(Long id, String repositoryName, String repositoryPath,
                                     Integer readinessScore, String deploymentDecision,
                                     List<String> strengths, List<String> warnings,
                                     List<String> recommendations, LocalDateTime generatedAt) {
        this.id = id;
        this.repositoryName = repositoryName;
        this.repositoryPath = repositoryPath;
        this.readinessScore = readinessScore;
        this.deploymentDecision = deploymentDecision;
        this.strengths = strengths;
        this.warnings = warnings;
        this.recommendations = recommendations;
        this.generatedAt = generatedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRepositoryName() { return repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
    public String getRepositoryPath() { return repositoryPath; }
    public void setRepositoryPath(String repositoryPath) { this.repositoryPath = repositoryPath; }
    public Integer getReadinessScore() { return readinessScore; }
    public void setReadinessScore(Integer readinessScore) { this.readinessScore = readinessScore; }
    public String getDeploymentDecision() { return deploymentDecision; }
    public void setDeploymentDecision(String deploymentDecision) { this.deploymentDecision = deploymentDecision; }
    public List<String> getStrengths() { return strengths; }
    public void setStrengths(List<String> strengths) { this.strengths = strengths; }
    public List<String> getWarnings() { return warnings; }
    public void setWarnings(List<String> warnings) { this.warnings = warnings; }
    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}
