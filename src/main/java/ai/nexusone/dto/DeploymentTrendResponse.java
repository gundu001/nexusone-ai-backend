package ai.nexusone.dto;

import java.time.LocalDate;

public record DeploymentTrendResponse(
        LocalDate date,
        long total,
        long successful,
        long failed,
        long inProgress,
        long aborted,
        double successRate
) { }
