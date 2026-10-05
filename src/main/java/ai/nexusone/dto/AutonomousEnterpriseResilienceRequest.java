package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseResiliencePriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseResilienceRequest(
 @NotBlank String title,
 @NotBlank String resilienceVision,
 @NotBlank String businessContinuityPlan,
 @NotBlank String disasterRecoveryStrategy,
 @NotBlank String cyberResilienceFramework,
 @NotBlank String infrastructureResilienceModel,
 @NotBlank String workforceResilienceProgram,
 @NotBlank String thirdPartyResilienceAssessment,
 @NotBlank String supplyChainResilienceStrategy,
 @NotBlank String operationalResilienceBlueprint,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double visionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double continuityScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double recoveryScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double cyberScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double infrastructureScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double workforceScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double thirdPartyScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double supplyChainScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double operationalScore,
 @NotNull AutonomousEnterpriseResiliencePriority priority,
 @NotBlank String createdBy) {}
