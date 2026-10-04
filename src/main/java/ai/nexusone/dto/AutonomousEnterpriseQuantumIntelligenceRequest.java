package ai.nexusone.dto;
import ai.nexusone.enums.AutonomousEnterpriseQuantumIntelligencePriority;
import jakarta.validation.constraints.*;
public record AutonomousEnterpriseQuantumIntelligenceRequest(
 @NotBlank String title,
 @NotBlank String quantumOptimizationStrategy,
 @NotBlank String quantumSimulationModel,
 @NotBlank String hybridQuantumClassicalOrchestration,
 @NotBlank String quantumRiskModeling,
 @NotBlank String quantumSecurityReadiness,
 @NotBlank String quantumDataStrategy,
 @NotBlank String enterpriseUseCases,
 @NotBlank String quantumIntelligenceRecommendations,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double optimizationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double simulationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double orchestrationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double riskModelingScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double securityReadinessScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double dataReadinessScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double useCaseValueScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double adoptionReadinessScore,
 @NotNull AutonomousEnterpriseQuantumIntelligencePriority priority,
 @NotBlank String createdBy) {}
