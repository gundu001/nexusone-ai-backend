package ai.nexusone.dto.response;

import java.time.Instant;
import java.util.List;

public record RootCauseAssessmentResponse(
        String incidentId,
        String question,
        String rootCause,
        String summary,
        double confidence,
        String pattern,
        List<String> evidence,
        List<String> contributingFactors,
        List<String> similarIncidents,
        List<String> recommendedActions,
        List<String> preventionActions,
        Instant createdAt
) {}
