package ai.nexusone.dto;

import ai.nexusone.enums.OutcomeStatus;
import ai.nexusone.enums.OutcomeType;
import java.time.LocalDateTime;

public record OutcomeResponse(
    Long id, Long executionId, Long decisionId, String outcomeName,
    String applicationName, String environment, OutcomeType outcomeType,
    Double availabilityScore, Double performanceScore, Double errorReductionScore,
    Double costEfficiencyScore, Double businessKpiScore, Double overallScore,
    OutcomeStatus status, String summary, String recommendation, String evidence,
    String measuredBy, LocalDateTime measuredAt, LocalDateTime createdAt, LocalDateTime updatedAt
) {}
