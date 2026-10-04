package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseAutonomousInnovationPriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseAutonomousInnovationRequest(
 @NotBlank String title,
 @NotBlank String innovationOpportunity,
 @NotBlank String marketDisruptionStrategy,
 @NotBlank String newRevenueStrategy,
 @NotBlank String technologyInnovationStrategy,
 @NotBlank String businessModelInnovation,
 @NotBlank String customerExperienceInnovation,
 @NotBlank String innovationRiskAssessment,
 @NotBlank String autonomousInnovationRecommendations,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double innovationPotentialScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double marketOpportunityScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double technologyFeasibilityScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double revenuePotentialScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double businessModelScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double customerValueScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double riskReadinessScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double executionReadinessScore,
 @NotNull AutonomousEnterpriseAutonomousInnovationPriority priority,
 @NotBlank String createdBy) {}
