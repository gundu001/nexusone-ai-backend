package ai.nexusone.dto;

import ai.nexusone.enums.*;
import jakarta.validation.constraints.*;

public record AutonomousEnterpriseProfitabilityIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String profitabilityIntelligenceVision,
        @NotBlank String marginOptimizationStrategy,
        @NotBlank String costEfficiencyStrategy,
        @NotBlank String productProfitabilityStrategy,
        @NotBlank String customerProfitabilityStrategy,
        @NotBlank String operatingLeverageStrategy,
        @NotBlank String cashFlowOptimizationStrategy,
        @NotBlank String executiveProfitabilityDecision,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double grossMarginScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double operatingMarginScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double costEfficiencyScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double productProfitabilityScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double customerProfitabilityScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double operatingLeverageScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double cashFlowStrengthScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double executionConfidenceScore,
        @NotNull AutonomousEnterpriseProfitabilityIntelligencePriority priority,
        @NotBlank String createdBy) {
}
