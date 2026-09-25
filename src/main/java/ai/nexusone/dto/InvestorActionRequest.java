package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record InvestorActionRequest(@NotBlank String actionBy) {
}
