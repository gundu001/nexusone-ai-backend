package ai.nexusone.dto.devopscommand;

import java.time.OffsetDateTime;

public class CommandExecutionResponse {
    private final Long commandId;
    private final Long executionId;
    private final String command;
    private final String status;
    private final String message;
    private final OffsetDateTime executedAt;

    public CommandExecutionResponse(Long commandId, Long executionId, String command, String status, String message, OffsetDateTime executedAt) {
        this.commandId = commandId;
        this.executionId = executionId;
        this.command = command;
        this.status = status;
        this.message = message;
        this.executedAt = executedAt;
    }

    public Long getCommandId() { return commandId; }
    public Long getExecutionId() { return executionId; }
    public String getCommand() { return command; }
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public OffsetDateTime getExecutedAt() { return executedAt; }
}
