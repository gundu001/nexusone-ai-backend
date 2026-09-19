package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AutonomousRemediationRollbackRequest(
        @NotBlank String application,
        @NotBlank String requestedBy,
        @NotBlank String reason
) {}
