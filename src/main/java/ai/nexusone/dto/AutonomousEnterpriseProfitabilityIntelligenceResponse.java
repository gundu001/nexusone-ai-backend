package ai.nexusone.dto;

import ai.nexusone.enums.*;
import java.time.LocalDateTime;

public record AutonomousEnterpriseProfitabilityIntelligenceResponse(
        Long id,
        String title,
        String profitabilityIntelligenceVision,
        String marginOptimizationStrategy,
        String costEfficiencyStrategy,
        String productProfitabilityStrategy,
        String customerProfitabilityStrategy,
        String operatingLeverageStrategy,
        String cashFlowOptimizationStrategy,
        String executiveProfitabilityDecision,
        Double grossMarginScore,
        Double operatingMarginScore,
        Double costEfficiencyScore,
        Double productProfitabilityScore,
        Double customerProfitabilityScore,
        Double operatingLeverageScore,
        Double cashFlowStrengthScore,
        Double executionConfidenceScore,
        Double enterpriseProfitabilityIntelligenceScore,
        AutonomousEnterpriseProfitabilityIntelligencePriority priority,
        AutonomousEnterpriseProfitabilityIntelligenceStatus status,
        String createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
