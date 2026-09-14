package ai.nexusone.dto.response;

public record ReleaseReadinessOverviewResponse(
        int totalReleases,
        int readyReleases,
        int conditionalReleases,
        int blockedReleases,
        double averageReadinessScore,
        int passedGates,
        int failedGates,
        double releaseSuccessRate) {
}
