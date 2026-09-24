package ai.nexusone.dto;

public record ExecutiveAdvisoryAnalyticsResponse(
        long total,
        long generated,
        long reviewed,
        long accepted,
        long rejected,
        long executed,
        double averageAlignment,
        double averageInvestmentValue,
        double averageRisk,
        double enterpriseAdvisoryScore) {
}
