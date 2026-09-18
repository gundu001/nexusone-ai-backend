package ai.nexusone.dto.response;

import java.time.Instant;
import java.util.List;

public record PredictiveIncidentResponse(
        String predictionId,
        String application,
        String environment,
        String predictedIncident,
        double riskScore,
        String riskLevel,
        String predictionWindow,
        String status,
        Instant observedAt,
        List<String> signals
) {}
