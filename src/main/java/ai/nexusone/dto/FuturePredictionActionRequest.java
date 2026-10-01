package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record FuturePredictionActionRequest(@NotBlank String actionBy) {}
