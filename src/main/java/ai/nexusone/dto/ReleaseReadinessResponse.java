package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ReleaseReadinessResponse(
        Long executionId,
        String repositoryName,
        String jobName,
        String status,
        double readinessScore,
        String readinessLevel,
        boolean readyForPromotion,
        List<String> passedChecks,
        List<String> blockingChecks,
        LocalDateTime evaluatedAt
) { }
