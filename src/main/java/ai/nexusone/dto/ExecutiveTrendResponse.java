package ai.nexusone.dto;

import java.time.LocalDate;

public record ExecutiveTrendResponse(
        LocalDate date,
        long total,
        long successful,
        long failed,
        long active,
        double successRate
) { }
