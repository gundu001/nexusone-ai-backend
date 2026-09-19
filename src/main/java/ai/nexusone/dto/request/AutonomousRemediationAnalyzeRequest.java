package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AutonomousRemediationAnalyzeRequest(
        @NotBlank String application,
        @NotBlank String question
) {}
