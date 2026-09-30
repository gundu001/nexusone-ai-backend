package ai.nexusone.dto;

public record GlobalEconomicAnalyticsResponse(
        long total,
        long generated,
        long reviewed,
        long approved,
        long rejected,
        long published,
        double averageEconomicGrowth,
        double averageInflationStability,
        double averageInterestRateStability,
        double averageCurrencyStability,
        double averageTradeResilience,
        double averageLaborMarketStrength,
        double averageSupplyChainResilience,
        double enterpriseGlobalEconomicIntelligenceScore) {
}
