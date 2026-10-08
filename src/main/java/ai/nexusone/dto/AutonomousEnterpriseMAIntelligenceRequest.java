package ai.nexusone.dto;

import ai.nexusone.enums.AutonomousEnterpriseMAIntelligencePriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutonomousEnterpriseMAIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String maIntelligenceVision,
        @NotBlank String acquisitionStrategy,
        @NotBlank String mergerSynergyAnalysis,
        @NotBlank String targetCompanyAssessment,
        @NotBlank String financialDueDiligence,
        @NotBlank String operationalDueDiligence,
        @NotBlank String culturalIntegrationStrategy,
        @NotBlank String executiveMADecision,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double synergyScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double financialStrengthScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicFitScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double integrationReadinessScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double riskAssessmentScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double targetQualityScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double valueCreationScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double executionConfidenceScore,
        @NotNull AutonomousEnterpriseMAIntelligencePriority priority,
        @NotBlank String createdBy) {
}
