package ai.nexusone.dto;

import ai.nexusone.enums.CeoPriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CeoIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String executiveSummary,
        @NotBlank String strategicPriority,
        @NotBlank String ceoRecommendation,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double enterpriseHealthScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double transformationScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double financialScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double operationalScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double riskExposure,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double innovationScore,
        @NotNull CeoPriority priority,
        @NotBlank String createdBy
) {
}
