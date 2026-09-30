package ai.nexusone.dto;

public record EnterpriseRiskAnalyticsResponse(
        long total,
        long generated,
        long reviewed,
        long approved,
        long rejected,
        long published,
        double averageOperationalRisk,
        double averageFinancialRisk,
        double averageCyberSecurityRisk,
        double averageRegulatoryComplianceRisk,
        double averageStrategicRisk,
        double averageThirdPartyRisk,
        double averageBusinessContinuityRisk,
        double enterpriseRiskIntelligenceScore) {
}
