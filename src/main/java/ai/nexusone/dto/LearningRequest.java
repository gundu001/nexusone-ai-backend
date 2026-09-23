package ai.nexusone.dto;
import ai.nexusone.enums.OptimizationArea;
import jakarta.validation.constraints.*;
public record LearningRequest(
 @NotNull Long outcomeId, @NotNull Long executionId,
 @NotBlank String applicationName, @NotBlank String environment,
 @NotNull OptimizationArea optimizationArea,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double observedScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double targetScore,
 @NotBlank String evidence, @NotBlank String createdBy
) {}
