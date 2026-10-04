package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record AutonomousEnterpriseTransformationActionRequest(@NotBlank String actionBy) {}
