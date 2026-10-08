package ai.nexusone.dto;

import ai.nexusone.enums.AutonomousEnterpriseValuationIntelligencePriority;
import ai.nexusone.enums.AutonomousEnterpriseValuationIntelligenceStatus;
import java.time.LocalDateTime;

public record AutonomousEnterpriseValuationIntelligenceResponse(
        Long id,
        String title,
        String valuationIntelligenceVision,
        String discountedCashFlowStrategy,
        String comparableCompanyAnalysis,
        String marketValuationStrategy,
        String assetValuationStrategy,
        String intangibleAssetValuationStrategy,
        String shareholderValueStrategy,
        String executiveValuationDecision,
        Double enterpriseValueScore,
        Double equityValueScore,
        Double cashFlowValueScore,
        Double marketPositionScore,
        Double assetQualityScore,
        Double intangibleValueScore,
        Double growthValueScore,
        Double investorConfidenceScore,
        Double enterpriseValuationIntelligenceScore,
        AutonomousEnterpriseValuationIntelligencePriority priority,
        AutonomousEnterpriseValuationIntelligenceStatus status,
        String createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
