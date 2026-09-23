package ai.nexusone.dto;

import ai.nexusone.enums.InsightType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CognitiveInsightRequest(
        @NotBlank String title,
        @NotNull InsightType insightType,
        @NotBlank String sourceModules,
        Long memoryId,
        Long knowledgeId,
        Long incidentId,
        Long decisionId,
        Long executionId,
        Long outcomeId,
        Long learningId,
        @NotBlank String businessContext,
        @NotBlank String observation,
        @NotBlank String correlationSummary,
        @NotBlank String cognitiveReasoning,
        @NotBlank String prediction,
        @NotBlank String recommendation,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double confidenceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double impactScore,
        @NotBlank String createdBy
) {
}
