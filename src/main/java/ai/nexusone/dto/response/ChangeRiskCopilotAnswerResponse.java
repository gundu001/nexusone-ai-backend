package ai.nexusone.dto.response;

import java.time.Instant;
import java.util.List;

public record ChangeRiskCopilotAnswerResponse(
        Long changeId,
        String question,
        String answer,
        double confidence,
        String riskLevel,
        boolean approvalRequired,
        List<String> evidence,
        List<String> actions,
        Instant createdAt
) {}
