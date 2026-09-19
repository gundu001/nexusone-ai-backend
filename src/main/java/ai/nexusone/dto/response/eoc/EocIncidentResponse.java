package ai.nexusone.dto.response.eoc;
import java.time.Instant;
public record EocIncidentResponse(String incidentId, String application, String environment, String severity, String source, String summary, String status, Instant detectedAt) {}
