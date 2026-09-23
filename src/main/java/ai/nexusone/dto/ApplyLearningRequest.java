package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record ApplyLearningRequest(@NotBlank String appliedBy) {}
