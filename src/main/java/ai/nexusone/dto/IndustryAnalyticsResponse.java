package ai.nexusone.dto;

public record IndustryAnalyticsResponse(
        long total,
        long generated,
        long reviewed,
        long approved,
        long rejected,
        long published,
        double averageIndustryGrowth,
        double averageEcosystemStrength,
        double averagePartnerHealth,
        double averageSupplierResilience,
        double averageRegulatoryPreparedness,
        double averageInnovationVelocity,
        double enterpriseIndustryIntelligenceScore) {
}
