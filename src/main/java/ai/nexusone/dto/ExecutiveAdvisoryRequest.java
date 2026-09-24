package ai.nexusone.dto;

import ai.nexusone.enums.ExecutivePriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExecutiveAdvisoryRequest(
        @NotBlank String title,
        @NotBlank String strategicObjective,
        @NotBlank String businessChallenge,
        @NotBlank String executiveRecommendation,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double investmentValue,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicAlignment,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double riskScore,
        @NotNull ExecutivePriority priority,
        @NotBlank String createdBy) {
}
