package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record ExecutiveDecisionRequest(@NotBlank String actionBy) {
}
