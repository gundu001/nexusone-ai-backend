package ai.nexusone.dto;
public record AutonomousEnterpriseAutonomousInnovationAnalyticsResponse(
 long total,long generated,long reviewed,long approved,long rejected,long published,
 double averageInnovationPotential,double averageMarketOpportunity,double averageTechnologyFeasibility,
 double averageRevenuePotential,double averageBusinessModel,double averageCustomerValue,
 double averageRiskReadiness,double averageExecutionReadiness,double enterpriseAutonomousInnovationScore) {}
