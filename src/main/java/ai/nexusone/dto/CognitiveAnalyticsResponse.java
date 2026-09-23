package ai.nexusone.dto;

public record CognitiveAnalyticsResponse(
        long totalInsights,
        long activeInsights,
        long reviewedInsights,
        long approvedInsights,
        long archivedInsights,
        long predictionInsights,
        long correlationInsights,
        double averageConfidence,
        double averageImpact,
        double enterpriseIntelligenceScore
) {
}
