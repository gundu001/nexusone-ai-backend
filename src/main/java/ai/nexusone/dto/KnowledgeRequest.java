package ai.nexusone.dto;
import ai.nexusone.enums.KnowledgeCategory;
import jakarta.validation.constraints.*;
public record KnowledgeRequest(
 @NotBlank String title, @NotNull KnowledgeCategory category, @NotBlank String sourceModule,
 Long incidentId, Long decisionId, Long executionId, Long outcomeId, Long learningId,
 @NotBlank String businessContext, @NotBlank String problemStatement, @NotBlank String solutionSummary,
 @NotBlank String lessonsLearned, @NotBlank String bestPractices, @NotBlank String reusablePlaybook,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double confidenceScore, @NotBlank String createdBy
) {}
