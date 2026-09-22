package ai.nexusone.dto;

import ai.nexusone.domain.ActionType;
import jakarta.validation.constraints.*;

public record ExecutionRequest(
    @NotNull Long decisionId,
    @NotBlank String executionName,
    @NotBlank String applicationName,
    @NotBlank String environment,
    @NotNull ActionType actionType,
    @NotBlank String targetType,
    @NotBlank String targetName,
    @NotBlank String requestedBy,
    @NotBlank String approvalReference,
    @NotBlank @Size(max=120) String idempotencyKey,
    String parameters
) {}
