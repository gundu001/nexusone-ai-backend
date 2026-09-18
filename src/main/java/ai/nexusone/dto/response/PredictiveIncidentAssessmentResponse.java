package ai.nexusone.dto.response;

import java.time.Instant;
import java.util.List;

public record PredictiveIncidentAssessmentResponse(
        String application,
        String question,
        String predictedIncident,
        String summary,
        double probability,
        String riskLevel,
        String predictionWindow,
        List<String> evidence,
        List<String> contributingFactors,
        List<String> recommendedActions,
        List<String> preventionActions,
        Instant createdAt
) {}
