package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SelfHealingQueryRequest(
        @NotBlank String application,
        @NotBlank String question
) {}
