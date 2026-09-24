package ai.nexusone.dto;

public record CeoAnalyticsResponse(
        long total,
        long generated,
        long reviewed,
        long approved,
        long rejected,
        long executed,
        double averageEnterpriseHealth,
        double averageTransformationScore,
        double averageFinancialScore,
        double averageOperationalScore,
        double averageRiskExposure,
        double averageInnovationScore,
        double enterpriseCeoScore
) {
}
