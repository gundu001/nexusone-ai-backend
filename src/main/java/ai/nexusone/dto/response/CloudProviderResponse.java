package ai.nexusone.dto.response;

public record CloudProviderResponse(
        String provider,
        String primaryRegion,
        int activeRegions,
        int deployments,
        double healthScore,
        double availability,
        double monthlyCost,
        String status) {
}
