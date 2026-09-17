package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReleaseCopilotQueryRequest(@NotNull Long releaseId, @NotBlank String question) {}
