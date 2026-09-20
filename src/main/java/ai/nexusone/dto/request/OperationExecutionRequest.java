package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record OperationExecutionRequest(
        @NotBlank String executedBy,
        boolean dryRun
) {}
