package ai.nexusone.dto.response;
import java.util.List;
public record ChangeRiskTrendResponse(double currentAverageRisk,double previousAverageRisk,double riskReductionPercentage,List<TrendPoint> trend) { public record TrendPoint(String period,double averageRisk,int highRiskChanges,int successfulDeployments,int failedDeployments) {} }
