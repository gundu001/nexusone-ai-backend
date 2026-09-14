package ai.nexusone.dto.response;
public record ChangeRiskOverviewResponse(int totalChanges,int highRiskChanges,int mediumRiskChanges,int lowRiskChanges,double averageRiskScore,int requiredApprovals,double predictionAccuracy) {}
