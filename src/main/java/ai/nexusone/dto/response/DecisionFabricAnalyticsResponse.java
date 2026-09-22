package ai.nexusone.dto.response;
public record DecisionFabricAnalyticsResponse(long totalDecisions,long autonomousDecisions,long governedDecisions,long approvedDecisions,long rejectedDecisions,long blockedDecisions,double averageConfidence,double averageRisk,double averagePolicyCompliance) {}
