package ai.nexusone.dto;
public record MarketAnalyticsResponse(long total, long generated, long reviewed, long approved,
 long rejected, long published, double averageMarketAttractiveness,
 double averageCompetitivePosition, double averageDifferentiation,
 double averageInnovationStrength, double averageExecutionReadiness,
 double averageMarketThreatExposure, double enterpriseMarketCompetitiveConfidenceScore) {}
