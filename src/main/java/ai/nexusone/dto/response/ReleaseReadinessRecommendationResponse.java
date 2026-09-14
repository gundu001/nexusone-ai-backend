package ai.nexusone.dto.response;

public record ReleaseReadinessRecommendationResponse(
        Long recommendationId,
        Long releaseId,
        String severity,
        String application,
        String category,
        String recommendation,
        String suggestedAction) {
}
