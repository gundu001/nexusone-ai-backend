package ai.nexusone.dto;

import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyPriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutonomousEnterpriseBusinessStrategyRequest(
        @NotBlank String title,
        @NotBlank String businessStrategyVision,
        @NotBlank String strategicObjectives,
        @NotBlank String competitivePositioningStrategy,
        @NotBlank String customerValueStrategy,
        @NotBlank String operatingModelStrategy,
        @NotBlank String digitalBusinessStrategy,
        @NotBlank String growthExecutionRoadmap,
        @NotBlank String executiveStrategyDecision,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicAlignmentScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double marketPositionScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double customerValueScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double operatingModelScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double digitalReadinessScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double growthPotentialScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double riskResilienceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double executionConfidenceScore,
        @NotNull AutonomousEnterpriseBusinessStrategyPriority priority,
        @NotBlank String createdBy) {
}
