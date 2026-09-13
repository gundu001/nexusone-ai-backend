package ai.nexusone.dto;

import java.time.LocalDateTime;

public record PredictiveOverviewResponse(
        long analyzedDeployments,
        double predictedSuccessProbability,
        double predictedFailureProbability,
        String forecastRiskLevel,
        double confidenceScore,
        long activeDeployments,
        long completedDeployments,
        String forecastStatus,
        LocalDateTime generatedAt
) { }
