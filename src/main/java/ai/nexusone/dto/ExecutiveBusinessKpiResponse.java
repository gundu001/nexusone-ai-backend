package ai.nexusone.dto;

import java.time.LocalDateTime;

public record ExecutiveBusinessKpiResponse(
        long totalDeployments,
        long completedDeployments,
        long successfulDeployments,
        long failedDeployments,
        long activeDeployments,
        long abortedDeployments,
        double deploymentSuccessRate,
        double deploymentFailureRate,
        double completionRate,
        double averageDurationSeconds,
        double releaseThroughputPerDay,
        String performanceRating,
        LocalDateTime generatedAt
) { }
