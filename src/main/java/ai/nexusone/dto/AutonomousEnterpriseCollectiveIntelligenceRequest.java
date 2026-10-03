package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseCollectiveIntelligencePriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseCollectiveIntelligenceRequest(
 @NotBlank String title,
 @NotBlank String humanExpertise,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double humanExpertiseScore,
 @NotBlank String agentCollaboration,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double agentCollaborationScore,
 @NotBlank String crossTeamKnowledge,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double crossTeamKnowledgeScore,
 @NotBlank String consensusQuality,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double consensusQualityScore,
 @NotBlank String diversityOfThought,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double diversityOfThoughtScore,
 @NotBlank String sharedLearning,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double sharedLearningScore,
 @NotBlank String decisionAlignment,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double decisionAlignmentScore,
 @NotBlank String governanceAlignment,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double governanceAlignmentScore,
 @NotBlank String outcomeImpact,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double outcomeImpactScore,
 @NotBlank String collectiveRecommendations,
 @NotNull AutonomousEnterpriseCollectiveIntelligencePriority priority,
 @NotBlank String createdBy) {}
