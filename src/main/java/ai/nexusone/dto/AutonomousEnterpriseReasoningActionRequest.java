package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record AutonomousEnterpriseReasoningActionRequest(@NotBlank String actionBy) {}
