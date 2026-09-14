package ai.nexusone.dto.response;

import java.util.List;

public record ReleaseReadinessTrendResponse(
        double currentAverageScore,
        double previousAverageScore,
        double improvementPercentage,
        List<TrendPoint> trend) {

    public record TrendPoint(
            String period,
            double averageReadinessScore,
            int readyReleases,
            int blockedReleases,
            double successRate) {
    }
}
