package ai.nexusone.dto;

public record OutcomeAnalyticsResponse(
    long totalOutcomes, long successfulOutcomes, long partialOutcomes, long failedOutcomes,
    double averageOutcomeScore, double averageAvailability, double averagePerformance,
    double averageCostEfficiency, double successRate
) {}
