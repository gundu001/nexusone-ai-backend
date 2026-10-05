package ai.nexusone.dto;
public record AutonomousEnterpriseSustainabilityAnalyticsResponse(
 long total,long generated,long reviewed,long approved,long rejected,long published,
 double averageVision,double averageEsg,double averageCarbonReduction,double averageEnergyEfficiency,
 double averageCircularEconomy,double averageSupplyChain,double averageClimateRisk,
 double averageCompliance,double enterpriseSustainabilityScore) {}
