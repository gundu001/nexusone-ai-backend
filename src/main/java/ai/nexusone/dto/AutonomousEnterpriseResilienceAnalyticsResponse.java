package ai.nexusone.dto;
public record AutonomousEnterpriseResilienceAnalyticsResponse(
 long total,long generated,long reviewed,long approved,long rejected,long published,
 double averageVision,double averageContinuity,double averageRecovery,double averageCyber,
 double averageInfrastructure,double averageWorkforce,double averageThirdParty,
 double averageSupplyChain,double averageOperational,double enterpriseResilienceScore) {}
