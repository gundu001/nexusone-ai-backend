package ai.nexusone.dto.response;

import java.time.Instant;
import java.util.List;

public record AutonomousRemediationPlanResponse(
        String application,
        String question,
        String diagnosis,
        String targetState,
        String selectedAction,
        double confidence,
        String riskLevel,
        String status,
        boolean approvalRequired,
        List<String> evidence,
        List<String> executionSteps,
        List<String> validationChecks,
        List<String> rollbackSteps,
        Instant createdAt
) {}
