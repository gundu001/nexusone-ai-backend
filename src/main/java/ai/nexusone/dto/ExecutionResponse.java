package ai.nexusone.dto;

import ai.nexusone.domain.*;
import java.time.LocalDateTime;

public record ExecutionResponse(
    Long id, Long decisionId, String executionName, String applicationName,
    String environment, ActionType actionType, String targetType, String targetName,
    String requestedBy, String approvalReference, String idempotencyKey, String parameters,
    ExecutionStatus status, Integer progressPercentage, String resultMessage,
    String errorMessage, Long parentExecutionId, LocalDateTime startedAt,
    LocalDateTime completedAt, LocalDateTime createdAt, LocalDateTime updatedAt
) {}
