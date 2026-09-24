package ai.nexusone.dto;
public record StrategicAnalyticsResponse(long total,long draft,long analyzed,long reviewed,long approved,long archived,double averageAlignment,double averageValue,double averageRisk,double enterpriseStrategyScore) {}
