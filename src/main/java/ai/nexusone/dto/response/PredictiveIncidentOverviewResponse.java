package ai.nexusone.dto.response;

public record PredictiveIncidentOverviewResponse(
        long monitoredApplications,
        long highRiskApplications,
        long activePredictions,
        long forecastsSaved
) {}
