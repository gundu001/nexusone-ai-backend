package ai.nexusone.dto;

public class DeploymentMetricsResponse {

    private long totalDeployments;

    private long successfulDeployments;

    private long failedDeployments;

    private double successRate;

    public DeploymentMetricsResponse() {
    }

    public DeploymentMetricsResponse(
            long totalDeployments,
            long successfulDeployments,
            long failedDeployments,
            double successRate) {

        this.totalDeployments = totalDeployments;
        this.successfulDeployments = successfulDeployments;
        this.failedDeployments = failedDeployments;
        this.successRate = successRate;
    }

    public long getTotalDeployments() {
        return totalDeployments;
    }

    public void setTotalDeployments(
            long totalDeployments) {
        this.totalDeployments = totalDeployments;
    }

    public long getSuccessfulDeployments() {
        return successfulDeployments;
    }

    public void setSuccessfulDeployments(
            long successfulDeployments) {
        this.successfulDeployments =
                successfulDeployments;
    }

    public long getFailedDeployments() {
        return failedDeployments;
    }

    public void setFailedDeployments(
            long failedDeployments) {
        this.failedDeployments =
                failedDeployments;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(
            double successRate) {
        this.successRate = successRate;
    }
}