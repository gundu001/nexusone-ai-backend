package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record ValidateMemoryRequest(@NotBlank String validatedBy) {}
