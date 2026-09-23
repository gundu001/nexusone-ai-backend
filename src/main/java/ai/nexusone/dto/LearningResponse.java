package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record LearningResponse(
 Long id, Long outcomeId, Long executionId, String applicationName, String environment,
 OptimizationArea optimizationArea, Double observedScore, Double targetScore, Double improvementGap,
 Double confidenceScore, Integer priorityScore, LearningStatus status,
 String learningSummary, String optimizationRecommendation, String evidence,
 String createdBy, String appliedBy, LocalDateTime appliedAt, LocalDateTime createdAt, LocalDateTime updatedAt
) {}
