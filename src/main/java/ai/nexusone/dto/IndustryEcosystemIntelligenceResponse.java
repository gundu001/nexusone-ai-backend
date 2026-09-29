package ai.nexusone.dto;

import ai.nexusone.enums.IndustryEcosystemIntelligenceStatus;
import ai.nexusone.enums.IndustryPriority;
import java.time.LocalDateTime;

public record IndustryEcosystemIntelligenceResponse(
        Long id,
        String title,
        String industryLandscape,
        String ecosystemAnalysis,
        String partnerIntelligence,
        String supplierIntelligence,
        String strategicRecommendations,
        Double industryGrowthScore,
        Double ecosystemStrengthScore,
        Double partnerHealthScore,
        Double supplierResilienceScore,
        Double regulatoryPreparednessScore,
        Double innovationVelocityScore,
        Double industryIntelligenceScore,
        IndustryPriority priority,
        IndustryEcosystemIntelligenceStatus status,
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
