package ai.nexusone.dto;

import ai.nexusone.enums.GlobalEconomicPriority;
import ai.nexusone.enums.GlobalEconomicStatus;
import java.time.LocalDateTime;

public record GlobalEconomicIntelligenceResponse(
        Long id,
        String title,
        String globalEconomicOutlook,
        String inflationAnalysis,
        String interestRateAnalysis,
        String currencyVolatilityAnalysis,
        String tradeMarketAnalysis,
        String strategicRecommendations,
        Double economicGrowthScore,
        Double inflationStabilityScore,
        Double interestRateStabilityScore,
        Double currencyStabilityScore,
        Double tradeResilienceScore,
        Double laborMarketStrengthScore,
        Double supplyChainResilienceScore,
        Double globalEconomicIntelligenceScore,
        GlobalEconomicPriority priority,
        GlobalEconomicStatus status,
        String createdBy,
        String reviewedBy,
        LocalDateTime reviewedAt,
        String decidedBy,
        LocalDateTime decidedAt,
        String publishedBy,
        LocalDateTime publishedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
