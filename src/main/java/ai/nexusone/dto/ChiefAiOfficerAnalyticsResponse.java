package ai.nexusone.dto;

public record ChiefAiOfficerAnalyticsResponse(
        long total,
        long generated,
        long reviewed,
        long approved,
        long rejected,
        long published,
        double averageAiStrategy,
        double averageGovernance,
        double averageResponsibleAi,
        double averagePlatformMaturity,
        double averageAdoption,
        double averageWorkforceReadiness,
        double averageDataReadiness,
        double averageAiRiskManagement,
        double averageValueRealization,
        double enterpriseChiefAiOfficerIntelligenceScore
) {}
