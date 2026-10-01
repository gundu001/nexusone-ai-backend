package ai.nexusone.dto;
public record FuturePredictionAnalyticsResponse(
 long total,long generated,long reviewed,long approved,long rejected,long published,
 double averageTechnologyPrediction,double averageMarketPrediction,double averageCustomerPrediction,
 double averageEconomicPrediction,double averageSupplyChainPrediction,double averageWorkforcePrediction,
 double averageCyberThreatPrediction,double averageRegulatoryPrediction,double averageIndustryDisruptionPrediction,
 double enterpriseFuturePredictionIntelligenceScore) {}
