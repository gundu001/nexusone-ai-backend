package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseUniversalKnowledgePriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseUniversalKnowledgeRequest(
 @NotBlank String title,
 @NotBlank String knowledgeGraphStrategy,
 @NotBlank String crossFabricKnowledgeLinking,
 @NotBlank String organizationalMemory,
 @NotBlank String semanticDiscovery,
 @NotBlank String knowledgeGovernance,
 @NotBlank String knowledgeQuality,
 @NotBlank String reuseAndLearning,
 @NotBlank String universalKnowledgeRecommendations,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double graphScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double linkingScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double memoryScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double discoveryScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double governanceScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double qualityScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double reuseScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double learningScore,
 @NotNull AutonomousEnterpriseUniversalKnowledgePriority priority,
 @NotBlank String createdBy) {}
