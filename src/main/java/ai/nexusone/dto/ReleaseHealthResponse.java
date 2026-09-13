package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ReleaseHealthResponse(
        Long executionId,
        String repositoryName,
        String jobName,
        String executionStatus,
        double healthScore,
        String healthLevel,
        boolean promotionEligible,
        List<String> healthSignals,
        LocalDateTime evaluatedAt
) { }
