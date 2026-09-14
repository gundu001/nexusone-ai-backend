package ai.nexusone.dto.response;

public record KubernetesRecommendationResponse(
        long recommendationId,
        String severity,
        String category,
        String resourceName,
        String recommendation,
        String suggestedAction) {
}
