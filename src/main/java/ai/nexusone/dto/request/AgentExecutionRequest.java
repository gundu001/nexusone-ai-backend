package ai.nexusone.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgentExecutionRequest(
        @NotBlank String agentType,
        @NotBlank String applicationName,
        @NotBlank String environment,
        @NotBlank String proposedAction,
        @NotBlank String riskLevel,
        @NotNull Boolean approvalRequired,
        @NotNull @Min(0) @Max(100) Double confidenceScore,
        @NotNull @Min(0) @Max(100) Double targetHealthScore
) {}
