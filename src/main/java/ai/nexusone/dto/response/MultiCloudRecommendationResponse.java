package ai.nexusone.dto.response;

public record MultiCloudRecommendationResponse(
        long recommendationId,
        String severity,
        String provider,
        String category,
        String recommendation,
        String suggestedAction) {
}
