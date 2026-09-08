package ai.nexusone.dto;

import ai.nexusone.entity.DeploymentDecision;
import ai.nexusone.entity.RiskLevel;

import java.time.LocalDateTime;
import java.util.List;

public class RiskAnalysisResponse {

    private Long id;

    private String applicationName;

    private String environment;

    private String repositoryName;

    private int riskScore;

    private RiskLevel riskLevel;

    private DeploymentDecision deploymentDecision;

    private String summary;

    private List<String> recommendations;

    private LocalDateTime analyzedAt;

    public RiskAnalysisResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(
            String applicationName) {
        this.applicationName = applicationName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(
            String environment) {
        this.environment = environment;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(
            String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(
            int riskScore) {
        this.riskScore = riskScore;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(
            RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public DeploymentDecision getDeploymentDecision() {
        return deploymentDecision;
    }

    public void setDeploymentDecision(
            DeploymentDecision deploymentDecision) {
        this.deploymentDecision = deploymentDecision;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(
            String summary) {
        this.summary = summary;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(
            List<String> recommendations) {
        this.recommendations = recommendations;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(
            LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
}