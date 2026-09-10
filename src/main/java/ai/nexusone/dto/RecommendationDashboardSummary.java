package ai.nexusone.dto;

public class RecommendationDashboardSummary {

    private Long safeToDeploy;

    private Long deployWithCaution;

    private Long manualReviewRequired;

    public RecommendationDashboardSummary() {
    }

    public Long getSafeToDeploy() {
        return safeToDeploy;
    }

    public void setSafeToDeploy(
            Long safeToDeploy
    ) {
        this.safeToDeploy = safeToDeploy;
    }

    public Long getDeployWithCaution() {
        return deployWithCaution;
    }

    public void setDeployWithCaution(
            Long deployWithCaution
    ) {
        this.deployWithCaution =
                deployWithCaution;
    }

    public Long getManualReviewRequired() {
        return manualReviewRequired;
    }

    public void setManualReviewRequired(
            Long manualReviewRequired
    ) {
        this.manualReviewRequired =
                manualReviewRequired;
    }
}