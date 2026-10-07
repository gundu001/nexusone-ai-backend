package ai.nexusone.dto;

import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyPriority;
import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyStatus;

import java.time.LocalDateTime;

public record AutonomousEnterpriseBusinessStrategyResponse(
        Long id,
        String title,
        String businessStrategyVision,
        String strategicObjectives,
        String competitivePositioningStrategy,
        String customerValueStrategy,
        String operatingModelStrategy,
        String digitalBusinessStrategy,
        String growthExecutionRoadmap,
        String executiveStrategyDecision,
        Double strategicAlignmentScore,
        Double marketPositionScore,
        Double customerValueScore,
        Double operatingModelScore,
        Double digitalReadinessScore,
        Double growthPotentialScore,
        Double riskResilienceScore,
        Double executionConfidenceScore,
        Double enterpriseBusinessStrategyScore,
        AutonomousEnterpriseBusinessStrategyPriority priority,
        AutonomousEnterpriseBusinessStrategyStatus status,
        String createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
