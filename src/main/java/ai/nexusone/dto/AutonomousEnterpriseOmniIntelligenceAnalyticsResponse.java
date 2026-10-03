package ai.nexusone.dto;
public record AutonomousEnterpriseOmniIntelligenceAnalyticsResponse(
 long total,long generated,long reviewed,long approved,long rejected,long published,
 double averageAssessment,double averageFusion,double averageReasoning,double averageSynchronization,
 double averageContext,double averageOrchestration,double averageTrust,double averageOptimization,
 double enterpriseOmniIntelligenceScore) {}
