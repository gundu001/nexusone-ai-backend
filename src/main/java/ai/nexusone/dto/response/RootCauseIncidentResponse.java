package ai.nexusone.dto.response;

import java.time.Instant;
import java.util.List;

public record RootCauseIncidentResponse(
        String incidentId,
        String title,
        String application,
        String environment,
        String severity,
        String status,
        Instant detectedAt,
        String deploymentId,
        List<String> signals
) {}
