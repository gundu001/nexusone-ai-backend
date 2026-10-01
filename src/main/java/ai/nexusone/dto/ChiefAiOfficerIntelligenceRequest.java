package ai.nexusone.dto;

import ai.nexusone.enums.ChiefAiOfficerPriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChiefAiOfficerIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String aiStrategy,
        @NotBlank String governanceOutlook,
        @NotBlank String responsibleAiPlan,
        @NotBlank String platformModernizationPlan,
        @NotBlank String adoptionRoadmap,
        @NotBlank String workforceTransformationPlan,
        @NotBlank String dataReadinessAssessment,
        @NotBlank String aiRiskAssessment,
        @NotBlank String valueRealizationPlan,
        @NotBlank String strategicRecommendations,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double aiStrategyScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double governanceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double responsibleAiScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double platformMaturityScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double adoptionScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double workforceReadinessScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double dataReadinessScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double aiRiskManagementScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double valueRealizationScore,
        @NotNull ChiefAiOfficerPriority priority,
        @NotBlank String createdBy
) {}
