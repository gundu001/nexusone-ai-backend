package ai.nexusone.dto;

import ai.nexusone.enums.*;
import java.time.LocalDateTime;

public record AutonomousEnterpriseRevenueIntelligenceResponse(
        Long id,
        String title,
        String revenueIntelligenceVision,
        String revenueGrowthStrategy,
        String recurringRevenueStrategy,
        String customerRevenueStrategy,
        String pricingOptimizationStrategy,
        String salesEffectivenessStrategy,
        String revenueDiversificationStrategy,
        String executiveRevenueDecision,
        Double revenueGrowthScore,
        Double recurringRevenueScore,
        Double customerRevenueScore,
        Double pricingOptimizationScore,
        Double salesEffectivenessScore,
        Double revenueDiversificationScore,
        Double forecastAccuracyScore,
        Double executionConfidenceScore,
        Double enterpriseRevenueIntelligenceScore,
        AutonomousEnterpriseRevenueIntelligencePriority priority,
        AutonomousEnterpriseRevenueIntelligenceStatus status,
        String createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
