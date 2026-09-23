package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record ValidateKnowledgeRequest(@NotBlank String validatedBy) {}
