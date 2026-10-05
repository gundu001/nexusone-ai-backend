package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record AutonomousEnterpriseResilienceActionRequest(@NotBlank String actionBy) {}
