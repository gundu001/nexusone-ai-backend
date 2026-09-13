package ai.nexusone.dto;

public class SelfHealingAction {
    private String actionCode;
    private String title;
    private String description;
    private String commandHint;
    private int priority;
    private boolean automated;
    private boolean approvalRequired;

    public SelfHealingAction() {}

    public SelfHealingAction(String actionCode, String title, String description,
                             String commandHint, int priority, boolean automated,
                             boolean approvalRequired) {
        this.actionCode = actionCode;
        this.title = title;
        this.description = description;
        this.commandHint = commandHint;
        this.priority = priority;
        this.automated = automated;
        this.approvalRequired = approvalRequired;
    }

    public String getActionCode() { return actionCode; }
    public void setActionCode(String actionCode) { this.actionCode = actionCode; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCommandHint() { return commandHint; }
    public void setCommandHint(String commandHint) { this.commandHint = commandHint; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public boolean isAutomated() { return automated; }
    public void setAutomated(boolean automated) { this.automated = automated; }
    public boolean isApprovalRequired() { return approvalRequired; }
    public void setApprovalRequired(boolean approvalRequired) { this.approvalRequired = approvalRequired; }
}
