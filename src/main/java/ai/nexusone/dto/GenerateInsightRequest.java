package ai.nexusone.dto;

import ai.nexusone.enums.InsightType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GenerateInsightRequest(
        @NotBlank String title,
        @NotNull InsightType insightType,
        @NotBlank String businessContext,
        @NotBlank String observation,
        List<String> signals,
        List<Long> supportingMemoryIds,
        List<String> sourceModules,
        @NotBlank String createdBy
) {
}
