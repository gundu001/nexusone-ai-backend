package ai.nexusone.dto;

import java.time.LocalDateTime;

public record DeploymentCapacityResponse(
        long totalDeployments,
        long activeDeployments,
        long terminalDeployments,
        double activeLoadPercentage,
        double averageDailyThroughput,
        String capacityStatus,
        long recommendedConcurrentLimit,
        String recommendation,
        LocalDateTime generatedAt
) { }
