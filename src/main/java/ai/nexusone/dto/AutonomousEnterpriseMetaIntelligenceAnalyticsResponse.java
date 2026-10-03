package ai.nexusone.dto;
public record AutonomousEnterpriseMetaIntelligenceAnalyticsResponse(
 long total,long generated,long reviewed,long approved,long rejected,long published,
 double averageAssessment,double averageOrchestration,double averagePrioritization,
 double averageGovernance,double averageConflictResolution,double averageOptimization,
 double averageTrust,double averagePerformance,double enterpriseMetaIntelligenceScore) {}
