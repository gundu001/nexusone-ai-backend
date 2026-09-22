package ai.nexusone.dto;

import ai.nexusone.enums.OutcomeType;
import jakarta.validation.constraints.*;

public record OutcomeRequest(
    @NotNull Long executionId, Long decisionId,
    @NotBlank String outcomeName, @NotBlank String applicationName,
    @NotBlank String environment, @NotNull OutcomeType outcomeType,
    @NotNull @DecimalMin("0") @DecimalMax("100") Double availabilityScore,
    @NotNull @DecimalMin("0") @DecimalMax("100") Double performanceScore,
    @NotNull @DecimalMin("0") @DecimalMax("100") Double errorReductionScore,
    @NotNull @DecimalMin("0") @DecimalMax("100") Double costEfficiencyScore,
    @NotNull @DecimalMin("0") @DecimalMax("100") Double businessKpiScore,
    @NotBlank String measuredBy, String evidence
) {}
