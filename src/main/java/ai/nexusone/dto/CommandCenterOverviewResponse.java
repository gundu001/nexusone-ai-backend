package ai.nexusone.dto;

import java.time.LocalDateTime;

public record CommandCenterOverviewResponse(
        long totalReleases,
        long healthyReleases,
        long activeReleases,
        long attentionRequiredReleases,
        long criticalAlerts,
        long pendingActions,
        double operationalHealthScore,
        String commandStatus,
        LocalDateTime generatedAt
) { }
