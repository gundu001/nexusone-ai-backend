package ai.nexusone.dto;
public record ChairmanAnalyticsResponse(long total,long generated,long reviewed,long approved,
 long rejected,long executed,double averageRevenueGrowth,double averageProfitability,
 double averageInnovation,double averageTransformation,double averageGovernance,
 double averageRiskExposure,double enterpriseChairmanScore) {}
