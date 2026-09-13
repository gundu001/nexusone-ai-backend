package ai.nexusone.dto;

import java.time.LocalDateTime;

public record AutonomousActionResponse(
        Long executionId,
        String actionCode,
        String actionTitle,
        String actionType,
        String priority,
        String status,
        boolean automated,
        boolean humanApprovalRequired,
        String rationale,
        String commandHint,
        LocalDateTime generatedAt
) { }
