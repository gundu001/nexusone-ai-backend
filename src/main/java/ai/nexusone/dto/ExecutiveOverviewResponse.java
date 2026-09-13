package ai.nexusone.dto;

import java.time.LocalDateTime;

public record ExecutiveOverviewResponse(
        long totalDeployments,
        long successfulDeployments,
        long failedDeployments,
        long activeDeployments,
        double successRate,
        double failureRate,
        double averageDurationSeconds,
        double platformHealthScore,
        double releaseGovernanceScore,
        double selfHealingEffectivenessScore,
        String overallStatus,
        LocalDateTime generatedAt
) { }
