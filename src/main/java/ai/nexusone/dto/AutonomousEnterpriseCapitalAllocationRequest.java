package ai.nexusone.dto;

import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationPriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutonomousEnterpriseCapitalAllocationRequest(
        @NotBlank String title,
        @NotBlank String capitalAllocationVision,
        @NotBlank String investmentPortfolio,
        @NotBlank String strategicPriorities,
        @NotBlank String fundingRecommendation,
        @NotBlank String riskAdjustedAllocation,
        @NotBlank String liquidityAssessment,
        @NotBlank String returnOptimizationAnalysis,
        @NotBlank String executiveCapitalDecision,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicAlignmentScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double expectedReturnScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double riskAdjustedReturnScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double liquidityScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double portfolioBalanceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double growthCapacityScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double resilienceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double capitalEfficiencyScore,
        @NotNull AutonomousEnterpriseCapitalAllocationPriority priority,
        @NotBlank String createdBy
) {
}
