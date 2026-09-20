package ai.nexusone.dto.response;

public record FinOpsOverviewResponse(
        long totalAssessments,
        double totalMonthlySpend,
        double totalMonthlyBudget,
        double forecastedMonthlySpend,
        double totalSavingsOpportunity,
        double averageOptimizationScore,
        long budgetRiskAssessments,
        long criticalCostAssessments,
        String currency,
        String overallStatus
) {}
