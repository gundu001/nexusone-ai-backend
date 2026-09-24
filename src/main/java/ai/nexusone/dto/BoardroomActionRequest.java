package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record BoardroomActionRequest(@NotBlank String actionBy) {}
