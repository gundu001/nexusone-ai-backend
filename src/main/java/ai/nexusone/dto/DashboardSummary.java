package ai.nexusone.dto;

public class DashboardSummary {

    private long totalRepositories;

    private long totalScans;

    private long totalRiskAnalyses;

    private double averageRiskScore;

    private long highRiskRepositories;

    private long mediumRiskRepositories;

    private long lowRiskRepositories;

    private long safeToDeploy;

    private long deployWithCaution;

    private long manualReviewRequired;

    public DashboardSummary() {
    }

    public long getTotalRepositories() {
        return totalRepositories;
    }

    public void setTotalRepositories(
            long totalRepositories) {

        this.totalRepositories = totalRepositories;
    }

    public long getTotalScans() {
        return totalScans;
    }

    public void setTotalScans(
            long totalScans) {

        this.totalScans = totalScans;
    }

    public long getTotalRiskAnalyses() {
        return totalRiskAnalyses;
    }

    public void setTotalRiskAnalyses(
            long totalRiskAnalyses) {

        this.totalRiskAnalyses = totalRiskAnalyses;
    }

    public double getAverageRiskScore() {
        return averageRiskScore;
    }

    public void setAverageRiskScore(
            double averageRiskScore) {

        this.averageRiskScore = averageRiskScore;
    }

    public long getHighRiskRepositories() {
        return highRiskRepositories;
    }

    public void setHighRiskRepositories(
            long highRiskRepositories) {

        this.highRiskRepositories =
                highRiskRepositories;
    }

    public long getMediumRiskRepositories() {
        return mediumRiskRepositories;
    }

    public void setMediumRiskRepositories(
            long mediumRiskRepositories) {

        this.mediumRiskRepositories =
                mediumRiskRepositories;
    }

    public long getLowRiskRepositories() {
        return lowRiskRepositories;
    }

    public void setLowRiskRepositories(
            long lowRiskRepositories) {

        this.lowRiskRepositories =
                lowRiskRepositories;
    }

    public long getSafeToDeploy() {
        return safeToDeploy;
    }

    public void setSafeToDeploy(
            long safeToDeploy) {

        this.safeToDeploy = safeToDeploy;
    }

    public long getDeployWithCaution() {
        return deployWithCaution;
    }

    public void setDeployWithCaution(
            long deployWithCaution) {

        this.deployWithCaution =
                deployWithCaution;
    }

    public long getManualReviewRequired() {
        return manualReviewRequired;
    }

    public void setManualReviewRequired(
            long manualReviewRequired) {

        this.manualReviewRequired =
                manualReviewRequired;
    }
}