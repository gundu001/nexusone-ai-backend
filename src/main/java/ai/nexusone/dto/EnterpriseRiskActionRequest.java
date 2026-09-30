package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record EnterpriseRiskActionRequest(@NotBlank String actionBy) {
}
