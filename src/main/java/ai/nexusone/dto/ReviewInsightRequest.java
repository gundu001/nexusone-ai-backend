package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record ReviewInsightRequest(
        @NotBlank String reviewedBy
) {
}
