package ai.nexusone.dto;

import ai.nexusone.enums.InvestorPriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InvestorIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String investorNarrative,
        @NotBlank String financialOutlook,
        @NotBlank String growthStrategy,
        @NotBlank String capitalAllocation,
        @NotBlank String shareholderValueProposition,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double revenueConfidenceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double profitabilityConfidenceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double growthPotentialScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double capitalEfficiencyScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double governanceConfidenceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double marketRiskExposure,
        @NotNull InvestorPriority priority,
        @NotBlank String createdBy) {
}
