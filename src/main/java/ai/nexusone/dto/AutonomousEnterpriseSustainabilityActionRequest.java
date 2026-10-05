package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record AutonomousEnterpriseSustainabilityActionRequest(@NotBlank String actionBy) {}
