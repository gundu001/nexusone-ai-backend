package ai.nexusone.dto.devopscommand;

import java.time.OffsetDateTime;

public class OperationTimelineResponse {
    private final Long eventId;
    private final Long executionId;
    private final String action;
    private final String status;
    private final String details;
    private final OffsetDateTime timestamp;

    public OperationTimelineResponse(Long eventId, Long executionId, String action, String status, String details, OffsetDateTime timestamp) {
        this.eventId = eventId;
        this.executionId = executionId;
        this.action = action;
        this.status = status;
        this.details = details;
        this.timestamp = timestamp;
    }

    public Long getEventId() { return eventId; }
    public Long getExecutionId() { return executionId; }
    public String getAction() { return action; }
    public String getStatus() { return status; }
    public String getDetails() { return details; }
    public OffsetDateTime getTimestamp() { return timestamp; }
}
