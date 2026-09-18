package ai.nexusone.dto.response;

import java.time.Instant;
import java.util.List;

public record SelfHealingCandidateResponse(
        String candidateId,
        String application,
        String environment,
        String symptom,
        String recommendedAction,
        double confidence,
        String riskLevel,
        String status,
        Instant detectedAt,
        List<String> signals
) {}
