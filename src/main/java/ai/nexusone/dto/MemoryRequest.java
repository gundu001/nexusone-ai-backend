package ai.nexusone.dto;
import ai.nexusone.enums.MemoryType;
import jakarta.validation.constraints.*;
public record MemoryRequest(
 @NotBlank String title, @NotNull MemoryType memoryType, @NotBlank String sourceModule,
 Long knowledgeId, Long incidentId, Long decisionId, Long executionId, Long outcomeId, Long learningId,
 @NotBlank String context, @NotBlank String observation, @NotBlank String learnedPattern,
 @NotBlank String reasoningRule, @NotBlank String recommendedAction,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double confidenceScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double importanceScore,
 @NotBlank String createdBy) {}
