package ai.nexusone.dto.response;
public record ChangeRiskRecommendationResponse(Long recommendationId,Long changeId,String severity,String application,String category,String recommendation,String suggestedAction) {}
