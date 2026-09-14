package ai.nexusone.dto.orchestrator;

import java.time.OffsetDateTime;

public class WorkflowStartResponse {
    private final Long orchestrationId;
    private final Long executionId;
    private final Long workflowId;
    private final String workflowName;
    private final String status;
    private final String currentStep;
    private final int currentStepNumber;
    private final int totalSteps;
    private final OffsetDateTime startedAt;
    private final String message;

    public WorkflowStartResponse(Long orchestrationId, Long executionId, Long workflowId, String workflowName, String status, String currentStep, int currentStepNumber, int totalSteps, OffsetDateTime startedAt, String message) {
        this.orchestrationId = orchestrationId;
        this.executionId = executionId;
        this.workflowId = workflowId;
        this.workflowName = workflowName;
        this.status = status;
        this.currentStep = currentStep;
        this.currentStepNumber = currentStepNumber;
        this.totalSteps = totalSteps;
        this.startedAt = startedAt;
        this.message = message;
    }

    public Long getOrchestrationId() { return orchestrationId; }
    public Long getExecutionId() { return executionId; }
    public Long getWorkflowId() { return workflowId; }
    public String getWorkflowName() { return workflowName; }
    public String getStatus() { return status; }
    public String getCurrentStep() { return currentStep; }
    public int getCurrentStepNumber() { return currentStepNumber; }
    public int getTotalSteps() { return totalSteps; }
    public OffsetDateTime getStartedAt() { return startedAt; }
    public String getMessage() { return message; }
}
