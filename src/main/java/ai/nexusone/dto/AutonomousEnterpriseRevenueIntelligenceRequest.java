package ai.nexusone.dto;

import ai.nexusone.enums.*;
import jakarta.validation.constraints.*;

public record AutonomousEnterpriseRevenueIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String revenueIntelligenceVision,
        @NotBlank String revenueGrowthStrategy,
        @NotBlank String recurringRevenueStrategy,
        @NotBlank String customerRevenueStrategy,
        @NotBlank String pricingOptimizationStrategy,
        @NotBlank String salesEffectivenessStrategy,
        @NotBlank String revenueDiversificationStrategy,
        @NotBlank String executiveRevenueDecision,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double revenueGrowthScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double recurringRevenueScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double customerRevenueScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double pricingOptimizationScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double salesEffectivenessScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double revenueDiversificationScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double forecastAccuracyScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double executionConfidenceScore,
        @NotNull AutonomousEnterpriseRevenueIntelligencePriority priority,
        @NotBlank String createdBy) {
}
