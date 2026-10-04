package ai.nexusone.dto;
public record AutonomousEnterpriseUniversalKnowledgeAnalyticsResponse(
 long total,long generated,long reviewed,long approved,long rejected,long published,
 double averageGraph,double averageLinking,double averageMemory,double averageDiscovery,
 double averageGovernance,double averageQuality,double averageReuse,double averageLearning,
 double enterpriseUniversalKnowledgeScore) {}
