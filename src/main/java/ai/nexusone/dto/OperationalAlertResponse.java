package ai.nexusone.dto;

import java.time.LocalDateTime;

public record OperationalAlertResponse(
        String alertCode,
        Long executionId,
        String severity,
        String category,
        String title,
        String description,
        String recommendedAction,
        boolean acknowledgementRequired,
        LocalDateTime generatedAt
) { }
