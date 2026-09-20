package ai.nexusone.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExecutiveIntelligenceRequest(
        @NotBlank String organizationName,
        @NotBlank String reportingPeriod,
        @NotNull @Min(0) @Max(100) Double sreScore,
        @NotNull @Min(0) @Max(100) Double platformScore,
        @NotNull @Min(0) @Max(100) Double governanceScore,
        @NotNull @Min(0) @Max(100) Double securityScore,
        @NotNull @Min(0) @Max(100) Double finOpsScore,
        @NotNull @Min(0) @Max(100) Double disasterRecoveryScore,
        @NotNull @Min(0) @Max(100) Double autonomousOperationsScore,
        @NotNull @Min(0) Integer openCriticalRisks,
        @NotNull @Min(0) Integer pendingExecutiveDecisions
) {}
