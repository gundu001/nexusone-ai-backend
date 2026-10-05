package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseSustainabilityPriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseSustainabilityRequest(
 @NotBlank String title,
 @NotBlank String sustainabilityVision,
 @NotBlank String esgStrategy,
 @NotBlank String carbonReductionPlan,
 @NotBlank String energyEfficiencyProgram,
 @NotBlank String circularEconomyStrategy,
 @NotBlank String sustainableSupplyChain,
 @NotBlank String climateRiskAssessment,
 @NotBlank String regulatoryCompliancePlan,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double visionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double esgScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double carbonReductionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double energyEfficiencyScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double circularEconomyScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double supplyChainScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double climateRiskScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double complianceScore,
 @NotNull AutonomousEnterpriseSustainabilityPriority priority,
 @NotBlank String createdBy) {}
