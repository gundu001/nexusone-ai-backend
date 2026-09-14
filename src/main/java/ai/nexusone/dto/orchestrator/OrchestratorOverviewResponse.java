package ai.nexusone.dto.orchestrator;



public class OrchestratorOverviewResponse {
    private final Long executionId;
    private final String orchestratorName;
    private final int readinessScore;
    private final int complianceScore;
    private final int predictedSuccessRate;
    private final String riskLevel;
    private final String recommendedDecision;
    private final int orchestrationCount;

    public OrchestratorOverviewResponse(Long executionId, String orchestratorName, int readinessScore, int complianceScore, int predictedSuccessRate, String riskLevel, String recommendedDecision, int orchestrationCount) {
        this.executionId = executionId;
        this.orchestratorName = orchestratorName;
        this.readinessScore = readinessScore;
        this.complianceScore = complianceScore;
        this.predictedSuccessRate = predictedSuccessRate;
        this.riskLevel = riskLevel;
        this.recommendedDecision = recommendedDecision;
        this.orchestrationCount = orchestrationCount;
    }

    public Long getExecutionId() { return executionId; }
    public String getOrchestratorName() { return orchestratorName; }
    public int getReadinessScore() { return readinessScore; }
    public int getComplianceScore() { return complianceScore; }
    public int getPredictedSuccessRate() { return predictedSuccessRate; }
    public String getRiskLevel() { return riskLevel; }
    public String getRecommendedDecision() { return recommendedDecision; }
    public int getOrchestrationCount() { return orchestrationCount; }
}
