package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseInvestmentStrategyPriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseInvestmentStrategyRequest(
 @NotBlank String title,
 @NotBlank String investmentStrategyVision,
 @NotBlank String strategicInvestmentRoadmap,
 @NotBlank String growthInvestmentStrategy,
 @NotBlank String innovationInvestmentStrategy,
 @NotBlank String technologyInvestmentStrategy,
 @NotBlank String marketExpansionStrategy,
 @NotBlank String riskDiversificationStrategy,
 @NotBlank String executiveInvestmentDecision,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicInvestmentScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double expectedGrowthScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double innovationPotentialScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double marketOpportunityScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double technologyReadinessScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double riskDiversificationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double investmentEfficiencyScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double executionConfidenceScore,
 @NotNull AutonomousEnterpriseInvestmentStrategyPriority priority,
 @NotBlank String createdBy) {}
