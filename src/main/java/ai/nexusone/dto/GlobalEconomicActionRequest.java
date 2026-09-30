package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record GlobalEconomicActionRequest(@NotBlank String actionBy) {
}
