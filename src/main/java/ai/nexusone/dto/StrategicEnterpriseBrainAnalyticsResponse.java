package ai.nexusone.dto;
public record StrategicEnterpriseBrainAnalyticsResponse(
        long total, long generated, long reviewed, long approved, long rejected, long published,
        double averageStrategicVision,
        double averageEnterpriseContext,
        double averageDecisionIntelligence,
        double averageScenarioPlanning,
        double averageRiskAnticipation,
        double averageExecutionAlignment,
        double averageLearningAdaptation,
        double averageInnovationIntelligence,
        double averageValueOrchestration,
        double enterpriseStrategicBrainScore
) {}
