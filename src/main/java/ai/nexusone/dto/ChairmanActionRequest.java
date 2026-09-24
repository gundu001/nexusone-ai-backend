package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record ChairmanActionRequest(@NotBlank String actionBy) {}
