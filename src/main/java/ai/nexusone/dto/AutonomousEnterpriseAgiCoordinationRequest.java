package ai.nexusone.dto;

import ai.nexusone.enums.AutonomousEnterpriseAgiCoordinationPriority;
import jakarta.validation.constraints.*;

public record AutonomousEnterpriseAgiCoordinationRequest(
        @NotBlank String title,
        @NotBlank String agentCoordination,
        @NotBlank String goalOrchestration,
        @NotBlank String taskDelegation,
        @NotBlank String sharedContext,
        @NotBlank String reasoningAlignment,
        @NotBlank String conflictResolution,
        @NotBlank String humanOversight,
        @NotBlank String safetyGovernance,
        @NotBlank String outcomeSynchronization,
        @NotBlank String coordinationRecommendations,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double agentCoordinationScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double goalOrchestrationScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double taskDelegationScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double sharedContextScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double reasoningAlignmentScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double conflictResolutionScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double humanOversightScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double safetyGovernanceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double outcomeSynchronizationScore,
        @NotNull AutonomousEnterpriseAgiCoordinationPriority priority,
        @NotBlank String createdBy
) {}
