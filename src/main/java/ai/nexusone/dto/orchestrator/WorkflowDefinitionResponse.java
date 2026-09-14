package ai.nexusone.dto.orchestrator;



public class WorkflowDefinitionResponse {
    private final Long workflowId;
    private final String name;
    private final String trigger;
    private final String status;
    private final Long flowId;

    public WorkflowDefinitionResponse(Long workflowId, String name, String trigger, String status, Long flowId) {
        this.workflowId = workflowId;
        this.name = name;
        this.trigger = trigger;
        this.status = status;
        this.flowId = flowId;
    }

    public Long getWorkflowId() { return workflowId; }
    public String getName() { return name; }
    public String getTrigger() { return trigger; }
    public String getStatus() { return status; }
    public Long getFlowId() { return flowId; }
}
