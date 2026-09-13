package ai.nexusone.dto;

import java.time.LocalDateTime;

public record DeploymentOverviewResponse(
        long totalDeployments,
        long successfulDeployments,
        long failedDeployments,
        long inProgressDeployments,
        long abortedDeployments,
        double successRate,
        double failureRate,
        double averageDurationSeconds,
        LocalDateTime generatedAt
) { }
