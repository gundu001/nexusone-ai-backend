package ai.nexusone.dto.response;

import java.time.LocalDateTime;

public record DeliveryCopilotOverviewResponse(
        long totalDeployments,
        long successfulDeployments,
        long failedDeployments,
        long activeDeployments,
        long abortedDeployments,
        double successRate,
        double failureRate,
        double averageDurationSeconds,
        String healthStatus,
        LocalDateTime generatedAt
) {}
