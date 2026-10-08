package ai.nexusone.dto;

import ai.nexusone.enums.AutonomousEnterpriseValuationIntelligencePriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutonomousEnterpriseValuationIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String valuationIntelligenceVision,
        @NotBlank String discountedCashFlowStrategy,
        @NotBlank String comparableCompanyAnalysis,
        @NotBlank String marketValuationStrategy,
        @NotBlank String assetValuationStrategy,
        @NotBlank String intangibleAssetValuationStrategy,
        @NotBlank String shareholderValueStrategy,
        @NotBlank String executiveValuationDecision,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double enterpriseValueScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double equityValueScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double cashFlowValueScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double marketPositionScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double assetQualityScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double intangibleValueScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double growthValueScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double investorConfidenceScore,
        @NotNull AutonomousEnterpriseValuationIntelligencePriority priority,
        @NotBlank String createdBy) {
}
