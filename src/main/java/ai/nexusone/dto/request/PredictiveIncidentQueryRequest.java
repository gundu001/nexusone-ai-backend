package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PredictiveIncidentQueryRequest(
        @NotBlank String application,
        @NotBlank String question
) {}
