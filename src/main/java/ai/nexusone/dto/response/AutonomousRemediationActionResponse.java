package ai.nexusone.dto.response;

import java.time.Instant;

public record AutonomousRemediationActionResponse(
        Long historyId,
        String application,
        String actionType,
        String status,
        boolean dryRun,
        String approvedBy,
        String requestedBy,
        String result,
        Instant createdAt
) {}
