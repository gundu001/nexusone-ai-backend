package ai.nexusone.dto.response;

public record DeploymentStrategyResponse(
        Long strategyId,
        String application,
        String environment,
        String recommendedProvider,
        String recommendedRegion,
        String strategy,
        Double confidenceScore,
        String rationale
) {
}