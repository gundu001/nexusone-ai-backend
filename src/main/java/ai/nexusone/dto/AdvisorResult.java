package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AdvisorResult {
    private Long id;
    private String repositoryName;
    private Integer riskScore;
    private String severity;
    private Integer confidenceScore;
    private String deploymentDecision;
    private List<String> recommendations;
    private List<String> strengths;
    private List<String> risks;
    private LocalDateTime generatedAt;

    public AdvisorResult() {}

    public AdvisorResult(Long id, String repositoryName, Integer riskScore, String severity,
                         Integer confidenceScore, String deploymentDecision,
                         List<String> recommendations, List<String> strengths,
                         List<String> risks, LocalDateTime generatedAt) {
        this.id = id; this.repositoryName = repositoryName; this.riskScore = riskScore;
        this.severity = severity; this.confidenceScore = confidenceScore;
        this.deploymentDecision = deploymentDecision; this.recommendations = recommendations;
        this.strengths = strengths; this.risks = risks; this.generatedAt = generatedAt;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRepositoryName() { return repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
    public Integer getRiskScore() { return riskScore; }
    public void setRiskScore(Integer riskScore) { this.riskScore = riskScore; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public Integer getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(Integer confidenceScore) { this.confidenceScore = confidenceScore; }
    public String getDeploymentDecision() { return deploymentDecision; }
    public void setDeploymentDecision(String deploymentDecision) { this.deploymentDecision = deploymentDecision; }
    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }
    public List<String> getStrengths() { return strengths; }
    public void setStrengths(List<String> strengths) { this.strengths = strengths; }
    public List<String> getRisks() { return risks; }
    public void setRisks(List<String> risks) { this.risks = risks; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}
