package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record AutonomousEnterpriseConsciousnessActionRequest(
        @NotBlank String actionBy
) {}
