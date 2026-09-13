package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SelfHealingRecommendationResponse {
    private Long executionId;
    private String repositoryName;
    private String jobName;
    private Integer buildNumber;
    private String executionStatus;
    private String deploymentResult;
    private String diagnosisCode;
    private String diagnosis;
    private int confidenceScore;
    private String riskLevel;
    private boolean healingRecommended;
    private boolean humanApprovalRequired;
    private String summary;
    private List<String> evidence;
    private List<SelfHealingAction> actions;
    private LocalDateTime generatedAt;

    public SelfHealingRecommendationResponse() {}

    public SelfHealingRecommendationResponse(Long executionId, String repositoryName,
            String jobName, Integer buildNumber, String executionStatus,
            String deploymentResult, String diagnosisCode, String diagnosis,
            int confidenceScore, String riskLevel, boolean healingRecommended,
            boolean humanApprovalRequired, String summary, List<String> evidence,
            List<SelfHealingAction> actions, LocalDateTime generatedAt) {
        this.executionId = executionId;
        this.repositoryName = repositoryName;
        this.jobName = jobName;
        this.buildNumber = buildNumber;
        this.executionStatus = executionStatus;
        this.deploymentResult = deploymentResult;
        this.diagnosisCode = diagnosisCode;
        this.diagnosis = diagnosis;
        this.confidenceScore = confidenceScore;
        this.riskLevel = riskLevel;
        this.healingRecommended = healingRecommended;
        this.humanApprovalRequired = humanApprovalRequired;
        this.summary = summary;
        this.evidence = evidence;
        this.actions = actions;
        this.generatedAt = generatedAt;
    }

    public Long getExecutionId() { return executionId; }
    public void setExecutionId(Long executionId) { this.executionId = executionId; }
    public String getRepositoryName() { return repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }
    public Integer getBuildNumber() { return buildNumber; }
    public void setBuildNumber(Integer buildNumber) { this.buildNumber = buildNumber; }
    public String getExecutionStatus() { return executionStatus; }
    public void setExecutionStatus(String executionStatus) { this.executionStatus = executionStatus; }
    public String getDeploymentResult() { return deploymentResult; }
    public void setDeploymentResult(String deploymentResult) { this.deploymentResult = deploymentResult; }
    public String getDiagnosisCode() { return diagnosisCode; }
    public void setDiagnosisCode(String diagnosisCode) { this.diagnosisCode = diagnosisCode; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public int getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(int confidenceScore) { this.confidenceScore = confidenceScore; }
    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
    public boolean isHealingRecommended() { return healingRecommended; }
    public void setHealingRecommended(boolean healingRecommended) { this.healingRecommended = healingRecommended; }
    public boolean isHumanApprovalRequired() { return humanApprovalRequired; }
    public void setHumanApprovalRequired(boolean humanApprovalRequired) { this.humanApprovalRequired = humanApprovalRequired; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public List<String> getEvidence() { return evidence; }
    public void setEvidence(List<String> evidence) { this.evidence = evidence; }
    public List<SelfHealingAction> getActions() { return actions; }
    public void setActions(List<SelfHealingAction> actions) { this.actions = actions; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}
