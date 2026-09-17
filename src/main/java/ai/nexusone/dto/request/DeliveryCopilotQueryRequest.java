package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DeliveryCopilotQueryRequest(
        String repositoryName,
        @NotBlank String question
) {}
