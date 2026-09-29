package ai.nexusone.dto;

import ai.nexusone.enums.IndustryPriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IndustryEcosystemIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String industryLandscape,
        @NotBlank String ecosystemAnalysis,
        @NotBlank String partnerIntelligence,
        @NotBlank String supplierIntelligence,
        @NotBlank String strategicRecommendations,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double industryGrowthScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double ecosystemStrengthScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double partnerHealthScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double supplierResilienceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double regulatoryPreparednessScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double innovationVelocityScore,
        @NotNull IndustryPriority priority,
        @NotBlank String createdBy) {
}
