package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record GeopoliticalActionRequest(@NotBlank String actionBy) {}
