package ai.nexusone.dto;

public record ExecutionAnalyticsResponse(
    long totalExecutions, long pendingExecutions, long runningExecutions,
    long successfulExecutions, long failedExecutions, long cancelledExecutions,
    long rolledBackExecutions, double successRate
) {}
