package ai.nexusone.dto.response.eoc;
public record EocRecommendationResponse(String recommendationId, String application, String category, String recommendation, String priority, double confidence, String status) {}
