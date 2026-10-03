package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseMetaIntelligencePriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseMetaIntelligenceRequest(
 @NotBlank String title,
 @NotBlank String intelligenceAssessment,
 @NotBlank String intelligenceOrchestration,
 @NotBlank String intelligencePrioritization,
 @NotBlank String intelligenceGovernance,
 @NotBlank String conflictResolution,
 @NotBlank String intelligenceOptimization,
 @NotBlank String trustManagement,
 @NotBlank String performanceManagement,
 @NotBlank String metaRecommendations,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double assessmentScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double orchestrationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double prioritizationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double governanceScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double conflictResolutionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double optimizationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double trustScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double performanceScore,
 @NotNull AutonomousEnterpriseMetaIntelligencePriority priority,
 @NotBlank String createdBy) {}
