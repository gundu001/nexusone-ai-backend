package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RootCauseQueryRequest(
        @NotBlank String incidentId,
        @NotBlank String question
) {}
