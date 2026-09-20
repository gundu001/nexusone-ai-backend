package ai.nexusone.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutonomousOperationRequest(
        @NotBlank String applicationName,
        @NotBlank String environment,
        @NotBlank String signalType,
        @NotNull @Min(0) @Max(100) Integer severityScore,
        @NotNull @Min(0) @Max(100) Integer confidenceScore,
        @NotNull @Min(0) Double currentCpuPercent,
        @NotNull @Min(0) Double currentMemoryPercent,
        @NotNull @Min(0) Double errorRatePercent,
        @NotNull @Min(0) Double responseTimeMs
) {}
