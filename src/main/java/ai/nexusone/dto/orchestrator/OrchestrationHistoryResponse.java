package ai.nexusone.dto.orchestrator;

import java.time.OffsetDateTime;

public class OrchestrationHistoryResponse {
    private final Long orchestrationId;
    private final Long executionId;
    private final String action;
    private final String status;
    private final String details;
    private final OffsetDateTime timestamp;

    public OrchestrationHistoryResponse(Long orchestrationId, Long executionId, String action, String status, String details, OffsetDateTime timestamp) {
        this.orchestrationId = orchestrationId;
        this.executionId = executionId;
        this.action = action;
        this.status = status;
        this.details = details;
        this.timestamp = timestamp;
    }

    public Long getOrchestrationId() { return orchestrationId; }
    public Long getExecutionId() { return executionId; }
    public String getAction() { return action; }
    public String getStatus() { return status; }
    public String getDetails() { return details; }
    public OffsetDateTime getTimestamp() { return timestamp; }
}
