package ai.nexusone.dto.devopscommand;

import java.time.OffsetDateTime;

public class CommandCenterOverviewResponse {
    private final int totalExecutions;
    private final int healthyExecutions;
    private final int activeWorkflows;
    private final int activeMissions;
    private final long successfulCommands;
    private final int automationSuccessRate;
    private final String platformStatus;
    private final OffsetDateTime generatedAt;

    public CommandCenterOverviewResponse(int totalExecutions, int healthyExecutions, int activeWorkflows, int activeMissions, long successfulCommands, int automationSuccessRate, String platformStatus, OffsetDateTime generatedAt) {
        this.totalExecutions = totalExecutions;
        this.healthyExecutions = healthyExecutions;
        this.activeWorkflows = activeWorkflows;
        this.activeMissions = activeMissions;
        this.successfulCommands = successfulCommands;
        this.automationSuccessRate = automationSuccessRate;
        this.platformStatus = platformStatus;
        this.generatedAt = generatedAt;
    }

    public int getTotalExecutions() { return totalExecutions; }
    public int getHealthyExecutions() { return healthyExecutions; }
    public int getActiveWorkflows() { return activeWorkflows; }
    public int getActiveMissions() { return activeMissions; }
    public long getSuccessfulCommands() { return successfulCommands; }
    public int getAutomationSuccessRate() { return automationSuccessRate; }
    public String getPlatformStatus() { return platformStatus; }
    public OffsetDateTime getGeneratedAt() { return generatedAt; }
}
