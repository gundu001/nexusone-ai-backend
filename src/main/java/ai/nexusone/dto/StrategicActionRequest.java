package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record StrategicActionRequest(@NotBlank String actionBy) {}
