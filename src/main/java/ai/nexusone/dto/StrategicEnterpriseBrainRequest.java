package ai.nexusone.dto;
import ai.nexusone.enums.StrategicEnterpriseBrainPriority;
import jakarta.validation.constraints.*;
public record StrategicEnterpriseBrainRequest(
        @NotBlank String title,
        @NotBlank String strategicVision,
        @NotBlank String enterpriseContext,
        @NotBlank String decisionIntelligence,
        @NotBlank String scenarioPlanning,
        @NotBlank String riskAnticipation,
        @NotBlank String executionAlignment,
        @NotBlank String learningAdaptation,
        @NotBlank String innovationIntelligence,
        @NotBlank String valueOrchestration,
        @NotBlank String strategicRecommendations,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicVisionScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double enterpriseContextScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double decisionIntelligenceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double scenarioPlanningScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double riskAnticipationScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double executionAlignmentScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double learningAdaptationScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double innovationIntelligenceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double valueOrchestrationScore,
        @NotNull StrategicEnterpriseBrainPriority priority,
        @NotBlank String createdBy
) {}
