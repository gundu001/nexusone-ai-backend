package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseReasoningPriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseReasoningRequest(@NotBlank String title,
 @NotBlank String causalReasoning,
 @NotBlank String multiStepReasoning,
 @NotBlank String strategicReasoning,
 @NotBlank String operationalReasoning,
 @NotBlank String predictiveReasoning,
 @NotBlank String decisionJustification,
 @NotBlank String adaptiveReasoning,
 @NotBlank String goalDrivenReasoning,
 @NotBlank String enterpriseKnowledgeReasoning,
 @NotBlank String reasoningRecommendations,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double causalReasoningScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double multiStepReasoningScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicReasoningScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double operationalReasoningScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double predictiveReasoningScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double decisionJustificationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double adaptiveReasoningScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double goalDrivenReasoningScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double enterpriseKnowledgeReasoningScore,
 @NotNull AutonomousEnterpriseReasoningPriority priority,@NotBlank String createdBy) {}
