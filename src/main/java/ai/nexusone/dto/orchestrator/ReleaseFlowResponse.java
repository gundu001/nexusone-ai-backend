package ai.nexusone.dto.orchestrator;

import java.util.List;

public class ReleaseFlowResponse {
    private final Long flowId;
    private final String flowName;
    private final String description;
    private final int stepCount;
    private final boolean active;
    private final List<String> steps;

    public ReleaseFlowResponse(Long flowId, String flowName, String description, int stepCount, boolean active, List<String> steps) {
        this.flowId = flowId;
        this.flowName = flowName;
        this.description = description;
        this.stepCount = stepCount;
        this.active = active;
        this.steps = steps;
    }

    public Long getFlowId() { return flowId; }
    public String getFlowName() { return flowName; }
    public String getDescription() { return description; }
    public int getStepCount() { return stepCount; }
    public boolean isActive() { return active; }
    public List<String> getSteps() { return steps; }
}
