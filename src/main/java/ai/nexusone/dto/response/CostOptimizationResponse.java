package ai.nexusone.dto.response;

public record CostOptimizationResponse(
        long optimizationId,
        String provider,
        double monthlyCost,
        double potentialSavings,
        double savingsPercentage,
        String optimization,
        String priority) {
}
