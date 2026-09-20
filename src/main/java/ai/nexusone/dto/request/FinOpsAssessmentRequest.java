package ai.nexusone.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FinOpsAssessmentRequest(
        @NotBlank String applicationName,
        @NotBlank String environment,
        @NotBlank String cloudProvider,
        @NotBlank String currency,
        @NotNull @Min(0) Double monthlySpend,
        @NotNull @Min(0) Double monthlyBudget,
        @NotNull @Min(0) Double previousMonthSpend,
        @NotNull @Min(0) Integer idleResources,
        @NotNull @Min(0) Integer rightsizingCandidates,
        @NotNull @Min(0) @Max(100) Double storageWastePercent,
        @NotNull @Min(0) @Max(100) Double reservedCapacityCoveragePercent,
        @NotNull @Min(0) @Max(100) Double taggedResourceCoveragePercent
) {}
