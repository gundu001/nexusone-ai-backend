package ai.nexusone.dto.response;

public record MultiCloudOverviewResponse(
        int totalCloudProviders,
        int activeRegions,
        int activeDeployments,
        double overallHealthScore,
        double estimatedMonthlySpend,
        double potentialMonthlySavings,
        String recommendedProvider) {
}
