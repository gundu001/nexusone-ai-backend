package ai.nexusone.dto;

import ai.nexusone.enums.AutonomousEnterpriseConsciousnessPriority;
import jakarta.validation.constraints.*;

public record AutonomousEnterpriseConsciousnessRequest(
        @NotBlank String title,
        @NotBlank String enterpriseAwareness,
        @NotBlank String contextUnderstanding,
        @NotBlank String decisionMemory,
        @NotBlank String reasoningIntelligence,
        @NotBlank String adaptiveLearning,
        @NotBlank String predictiveAwareness,
        @NotBlank String selfOptimization,
        @NotBlank String goalAlignment,
        @NotBlank String strategicConsciousness,
        @NotBlank String consciousnessRecommendations,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double enterpriseAwarenessScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double contextUnderstandingScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double decisionMemoryScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double reasoningIntelligenceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double adaptiveLearningScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double predictiveAwarenessScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double selfOptimizationScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double goalAlignmentScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicConsciousnessScore,
        @NotNull AutonomousEnterpriseConsciousnessPriority priority,
        @NotBlank String createdBy
) {}
