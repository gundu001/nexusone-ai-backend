package ai.nexusone.dto.response;
public record DecisionFabricOverviewResponse(long totalDecisions,long approvedDecisions,long pendingApprovals,long blockedDecisions,double averageConfidence,double averagePolicyCompliance,String overallStatus) {}
