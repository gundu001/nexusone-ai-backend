package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseOmniIntelligencePriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseOmniIntelligenceRequest(
 @NotBlank String title,
 @NotBlank String omnichannelAssessment,
 @NotBlank String crossDomainFusion,
 @NotBlank String multimodalReasoning,
 @NotBlank String intelligenceSynchronization,
 @NotBlank String contextUnification,
 @NotBlank String decisionOrchestration,
 @NotBlank String governanceAndTrust,
 @NotBlank String outcomeOptimization,
 @NotBlank String omniRecommendations,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double assessmentScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double fusionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double reasoningScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double synchronizationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double contextScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double orchestrationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double trustScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double optimizationScore,
 @NotNull AutonomousEnterpriseOmniIntelligencePriority priority,
 @NotBlank String createdBy) {}
