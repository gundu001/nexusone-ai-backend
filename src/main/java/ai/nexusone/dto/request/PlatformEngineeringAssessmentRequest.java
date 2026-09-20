package ai.nexusone.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlatformEngineeringAssessmentRequest(
        @NotBlank String platformName,
        @NotBlank String environment,
        @NotBlank String clusterName,
        @NotNull @Min(0) @Max(100) Double cpuUtilizationPercent,
        @NotNull @Min(0) @Max(100) Double memoryUtilizationPercent,
        @NotNull @Min(0) @Max(100) Double storageUtilizationPercent,
        @NotNull @Min(0) @Max(100) Double deploymentSuccessPercent,
        @NotNull @Min(0) Integer failedWorkloads,
        @NotNull @Min(0) Double averageLatencyMs,
        @NotNull @Min(0) @Max(100) Double automationCoveragePercent
) {}
