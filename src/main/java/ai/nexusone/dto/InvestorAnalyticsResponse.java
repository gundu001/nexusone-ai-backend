package ai.nexusone.dto;

public record InvestorAnalyticsResponse(
        long total,
        long generated,
        long reviewed,
        long approved,
        long rejected,
        long published,
        double averageRevenueConfidence,
        double averageProfitabilityConfidence,
        double averageGrowthPotential,
        double averageCapitalEfficiency,
        double averageGovernanceConfidence,
        double averageMarketRiskExposure,
        double enterpriseInvestorConfidenceScore) {
}
