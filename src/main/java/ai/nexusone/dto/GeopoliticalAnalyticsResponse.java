package ai.nexusone.dto;
public record GeopoliticalAnalyticsResponse(long total,long generated,long reviewed,long approved,long rejected,
 long published,double averageRegionalStability,double averagePoliticalStability,double averageSanctionsExposure,
 double averageEnergySecurity,double averageSupplyChainResilience,double averageRegulatorySovereignty,
 double averageDiplomaticRelations,double enterpriseGeopoliticalIntelligenceScore) {}
