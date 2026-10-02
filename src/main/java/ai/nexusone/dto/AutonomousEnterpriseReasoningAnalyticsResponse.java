package ai.nexusone.dto;
public record AutonomousEnterpriseReasoningAnalyticsResponse(long total,long generated,long reviewed,long approved,long rejected,long published,
 double averageCausalReasoning,
 double averageMultiStepReasoning,
 double averageStrategicReasoning,
 double averageOperationalReasoning,
 double averagePredictiveReasoning,
 double averageDecisionJustification,
 double averageAdaptiveReasoning,
 double averageGoalDrivenReasoning,
 double averageEnterpriseKnowledgeReasoning,
 double enterpriseReasoningScore) {}
