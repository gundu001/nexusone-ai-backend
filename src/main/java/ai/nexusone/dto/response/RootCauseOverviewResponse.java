package ai.nexusone.dto.response;

public record RootCauseOverviewResponse(
        long totalIncidents,
        long highConfidence,
        long recurringPatterns,
        long analysesSaved
) {}
