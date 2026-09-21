package ai.nexusone.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutonomousEnterpriseRequest(
        @NotBlank String organizationName,
        @NotBlank String environment,
        @NotNull @Min(0) @Max(100) Double sreScore,
        @NotNull @Min(0) @Max(100) Double platformScore,
        @NotNull @Min(0) @Max(100) Double governanceSecurityScore,
        @NotNull @Min(0) @Max(100) Double finOpsScore,
        @NotNull @Min(0) @Max(100) Double disasterRecoveryScore,
        @NotNull @Min(0) @Max(100) Double autonomousOperationsScore,
        @NotNull @Min(0) @Max(100) Double executiveReadinessScore,
        @NotNull @Min(0) Integer openCriticalRisks,
        @NotNull @Min(0) Integer pendingGovernedActions
) {}
