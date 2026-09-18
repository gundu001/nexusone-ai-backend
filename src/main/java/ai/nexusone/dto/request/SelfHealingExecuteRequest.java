package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SelfHealingExecuteRequest(
        @NotBlank String application,
        @NotBlank String action,
        boolean dryRun
) {}
