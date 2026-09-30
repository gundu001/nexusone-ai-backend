package ai.nexusone.dto;

import ai.nexusone.enums.GlobalEconomicPriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GlobalEconomicIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String globalEconomicOutlook,
        @NotBlank String inflationAnalysis,
        @NotBlank String interestRateAnalysis,
        @NotBlank String currencyVolatilityAnalysis,
        @NotBlank String tradeMarketAnalysis,
        @NotBlank String strategicRecommendations,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double economicGrowthScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double inflationStabilityScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double interestRateStabilityScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double currencyStabilityScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double tradeResilienceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double laborMarketStrengthScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double supplyChainResilienceScore,
        @NotNull GlobalEconomicPriority priority,
        @NotBlank String createdBy) {
}
