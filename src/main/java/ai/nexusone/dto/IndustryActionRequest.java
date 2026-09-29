package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record IndustryActionRequest(@NotBlank String actionBy) {
}
