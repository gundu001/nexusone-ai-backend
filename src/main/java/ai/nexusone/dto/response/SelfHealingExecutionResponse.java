package ai.nexusone.dto.response;

import java.time.Instant;

public record SelfHealingExecutionResponse(
        Long executionId,
        String application,
        String action,
        boolean dryRun,
        String status,
        String result,
        Instant executedAt
) {}
