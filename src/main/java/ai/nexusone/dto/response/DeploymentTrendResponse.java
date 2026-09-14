package ai.nexusone.dto.response;

import java.util.List;

public record DeploymentTrendResponse(
        int successfulDeployments,
        int failedDeployments,
        double successRate,
        List<TrendPoint> trend) {

    public record TrendPoint(String period, int successful, int failed) {
    }
}
