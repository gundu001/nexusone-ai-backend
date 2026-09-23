package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record ApproveInsightRequest(
        @NotBlank String approvedBy
) {
}
