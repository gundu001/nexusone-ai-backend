package ai.nexusone.dto.orchestrator;

import java.time.OffsetDateTime;

public class OrchestrationDecisionResponse {
    private final Long orchestrationId;
    private final Long executionId;
    private final String decision;
    private final int confidenceScore;
    private final String releaseRecommendation;
    private final int predictedSuccessRate;
    private final boolean automationExecuted;
    private final OffsetDateTime decidedAt;
    private final String message;

    public OrchestrationDecisionResponse(Long orchestrationId, Long executionId, String decision, int confidenceScore, String releaseRecommendation, int predictedSuccessRate, boolean automationExecuted, OffsetDateTime decidedAt, String message) {
        this.orchestrationId = orchestrationId;
        this.executionId = executionId;
        this.decision = decision;
        this.confidenceScore = confidenceScore;
        this.releaseRecommendation = releaseRecommendation;
        this.predictedSuccessRate = predictedSuccessRate;
        this.automationExecuted = automationExecuted;
        this.decidedAt = decidedAt;
        this.message = message;
    }

    public Long getOrchestrationId() { return orchestrationId; }
    public Long getExecutionId() { return executionId; }
    public String getDecision() { return decision; }
    public int getConfidenceScore() { return confidenceScore; }
    public String getReleaseRecommendation() { return releaseRecommendation; }
    public int getPredictedSuccessRate() { return predictedSuccessRate; }
    public boolean isAutomationExecuted() { return automationExecuted; }
    public OffsetDateTime getDecidedAt() { return decidedAt; }
    public String getMessage() { return message; }
}
