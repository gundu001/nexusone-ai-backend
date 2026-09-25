package ai.nexusone.dto;

import ai.nexusone.enums.InvestorIntelligenceStatus;
import ai.nexusone.enums.InvestorPriority;
import java.time.LocalDateTime;

public record InvestorIntelligenceResponse(
        Long id,
        String title,
        String investorNarrative,
        String financialOutlook,
        String growthStrategy,
        String capitalAllocation,
        String shareholderValueProposition,
        Double revenueConfidenceScore,
        Double profitabilityConfidenceScore,
        Double growthPotentialScore,
        Double capitalEfficiencyScore,
        Double governanceConfidenceScore,
        Double marketRiskExposure,
        Double investorConfidenceScore,
        InvestorPriority priority,
        InvestorIntelligenceStatus status,
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
