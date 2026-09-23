package ai.nexusone.dto;

import ai.nexusone.enums.InsightStatus;
import ai.nexusone.enums.InsightType;

import java.time.LocalDateTime;

public record CognitiveInsightResponse(
        Long id,
        String title,
        InsightType insightType,
        String sourceModules,
        Long memoryId,
        Long knowledgeId,
        Long incidentId,
        Long decisionId,
        Long executionId,
        Long outcomeId,
        Long learningId,
        String businessContext,
        String observation,
        String correlationSummary,
        String cognitiveReasoning,
        String prediction,
        String recommendation,
        Double confidenceScore,
        Double impactScore,
        Double intelligenceScore,
        Integer reviewCount,
        InsightStatus status,
        String createdBy,
        String reviewedBy,
        LocalDateTime reviewedAt,
        String approvedBy,
        LocalDateTime approvedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
