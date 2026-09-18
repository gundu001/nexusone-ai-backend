package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChangeRiskCopilotQueryRequest(
        @NotNull Long changeId,
        @NotBlank String question
) {}
