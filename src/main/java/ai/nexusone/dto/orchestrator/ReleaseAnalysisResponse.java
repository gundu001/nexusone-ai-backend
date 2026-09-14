package ai.nexusone.dto.orchestrator;

import java.util.List;
import java.time.OffsetDateTime;

public class ReleaseAnalysisResponse {
    private final Long executionId;
    private final int readinessScore;
    private final int complianceScore;
    private final int predictedSuccessRate;
    private final int failureProbability;
    private final int rollbackProbability;
    private final String riskLevel;
    private final boolean automationSuccessful;
    private final List<String> findings;
    private final OffsetDateTime analyzedAt;

    public ReleaseAnalysisResponse(Long executionId, int readinessScore, int complianceScore, int predictedSuccessRate, int failureProbability, int rollbackProbability, String riskLevel, boolean automationSuccessful, List<String> findings, OffsetDateTime analyzedAt) {
        this.executionId = executionId;
        this.readinessScore = readinessScore;
        this.complianceScore = complianceScore;
        this.predictedSuccessRate = predictedSuccessRate;
        this.failureProbability = failureProbability;
        this.rollbackProbability = rollbackProbability;
        this.riskLevel = riskLevel;
        this.automationSuccessful = automationSuccessful;
        this.findings = findings;
        this.analyzedAt = analyzedAt;
    }

    public Long getExecutionId() { return executionId; }
    public int getReadinessScore() { return readinessScore; }
    public int getComplianceScore() { return complianceScore; }
    public int getPredictedSuccessRate() { return predictedSuccessRate; }
    public int getFailureProbability() { return failureProbability; }
    public int getRollbackProbability() { return rollbackProbability; }
    public String getRiskLevel() { return riskLevel; }
    public boolean isAutomationSuccessful() { return automationSuccessful; }
    public List<String> getFindings() { return findings; }
    public OffsetDateTime getAnalyzedAt() { return analyzedAt; }
}
