package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseTransformationPriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseTransformationRequest(
 @NotBlank String title,
 @NotBlank String transformationVision,
 @NotBlank String transformationRoadmap,
 @NotBlank String operatingModelTransformation,
 @NotBlank String workforceTransformation,
 @NotBlank String processTransformation,
 @NotBlank String technologyTransformation,
 @NotBlank String customerTransformation,
 @NotBlank String transformationRiskAssessment,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double visionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double roadmapScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double operatingModelScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double workforceScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double processScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double technologyScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double customerScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double executionScore,
 @NotNull AutonomousEnterpriseTransformationPriority priority,
 @NotBlank String createdBy) {}
