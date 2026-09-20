package ai.nexusone.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SreAnalysisRequest(
        @NotBlank String applicationName,
        @NotNull @Min(0) @Max(100) Double availabilityPercent,
        @NotNull @Min(0) Integer totalRequests,
        @NotNull @Min(0) Integer failedRequests,
        @NotNull @Min(0) Double mttrMinutes,
        @NotNull @Min(0) Double mtbfHours,
        @NotNull @Min(0) @Max(100) Double targetSloPercent
) {}
