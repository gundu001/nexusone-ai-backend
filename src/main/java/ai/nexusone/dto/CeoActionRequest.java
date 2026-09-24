package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record CeoActionRequest(
        @NotBlank String actionBy
) {
}
