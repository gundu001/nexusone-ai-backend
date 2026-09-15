package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CopilotQueryRequest(
        @NotBlank String incidentId,
        @NotBlank String question
) {
}