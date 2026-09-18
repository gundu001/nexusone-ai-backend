package ai.nexusone.dto.response;

import java.time.Instant;
import java.util.List;

public record SelfHealingPlanResponse(
        String application,
        String question,
        String diagnosis,
        String selectedAction,
        String summary,
        double confidence,
        String riskLevel,
        List<String> evidence,
        List<String> executionSteps,
        List<String> validationChecks,
        List<String> rollbackSteps,
        Instant createdAt
) {}
