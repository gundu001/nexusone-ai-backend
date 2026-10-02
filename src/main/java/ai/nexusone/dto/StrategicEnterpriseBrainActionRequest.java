package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record StrategicEnterpriseBrainActionRequest(@NotBlank String actionBy) {}
