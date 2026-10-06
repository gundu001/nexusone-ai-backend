package ai.nexusone.dto;

import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationPriority;
import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationStatus;

import java.time.LocalDateTime;

public record AutonomousEnterpriseCapitalAllocationResponse(
        Long id,
        String title,
        String capitalAllocationVision,
        String investmentPortfolio,
        String strategicPriorities,
        String fundingRecommendation,
        String riskAdjustedAllocation,
        String liquidityAssessment,
        String returnOptimizationAnalysis,
        String executiveCapitalDecision,
        Double strategicAlignmentScore,
        Double expectedReturnScore,
        Double riskAdjustedReturnScore,
        Double liquidityScore,
        Double portfolioBalanceScore,
        Double growthCapacityScore,
        Double resilienceScore,
        Double capitalEfficiencyScore,
        Double enterpriseCapitalAllocationScore,
        AutonomousEnterpriseCapitalAllocationPriority priority,
        AutonomousEnterpriseCapitalAllocationStatus status,
        String createdBy,
        String reviewedBy,
        LocalDateTime reviewedAt,
        String decidedBy,
        LocalDateTime decidedAt,
        String publishedBy,
        LocalDateTime publishedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
