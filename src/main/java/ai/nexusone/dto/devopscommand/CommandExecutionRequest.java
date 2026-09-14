package ai.nexusone.dto.devopscommand;

public class CommandExecutionRequest {
    private String command;
    private Long executionId;

    public CommandExecutionRequest() {}

    public CommandExecutionRequest(String command, Long executionId) {
        this.command = command;
        this.executionId = executionId;
    }

    public String getCommand() { return command; }
    public void setCommand(String command) { this.command = command; }
    public Long getExecutionId() { return executionId; }
    public void setExecutionId(Long executionId) { this.executionId = executionId; }
}
