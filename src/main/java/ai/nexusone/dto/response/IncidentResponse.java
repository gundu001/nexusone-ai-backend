package ai.nexusone.dto.response;

import ai.nexusone.enums.IncidentSeverity;
import ai.nexusone.enums.IncidentStatus;

import java.time.Instant;
import java.util.List;

public record IncidentResponse(
        String incidentId,
        String title,
        String application,
        String environment,
        IncidentSeverity severity,
        IncidentStatus status,
        Instant detectedAt,
        String deploymentId,
        List<String> signals
) {}