package ai.nexusone.dto.response;

import java.time.Instant;
import java.util.List;

public record AutonomousRemediationCandidateResponse(
        String candidateId,
        String application,
        String environment,
        String issue,
        String recommendedAction,
        double confidence,
        String riskLevel,
        String status,
        boolean approvalRequired,
        Instant detectedAt,
        List<String> evidence
) {}
