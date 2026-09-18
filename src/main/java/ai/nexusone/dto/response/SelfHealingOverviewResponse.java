package ai.nexusone.dto.response;

public record SelfHealingOverviewResponse(
        long monitoredApplications,
        long healingCandidates,
        long highConfidencePlans,
        long executionsSaved
) {}
