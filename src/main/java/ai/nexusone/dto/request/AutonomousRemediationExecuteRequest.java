package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AutonomousRemediationExecuteRequest(
        @NotBlank String application,
        boolean dryRun
) {}
