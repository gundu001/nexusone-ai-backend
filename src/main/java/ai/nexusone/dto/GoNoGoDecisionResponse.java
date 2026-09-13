package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record GoNoGoDecisionResponse(
        Long executionId,
        String decision,
        double confidenceScore,
        String riskLevel,
        boolean humanApprovalRequired,
        String rationale,
        List<String> conditions,
        LocalDateTime decidedAt
) { }
