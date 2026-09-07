package ai.nexusone.dto;

public class DashboardSummary {

    private Long totalRepositories;

    private Long totalScans;

    private Long totalRiskAnalyses;

    private Double averageRiskScore;

    private Long highRiskRepositories;

    private Long mediumRiskRepositories;

    private Long lowRiskRepositories;

    public DashboardSummary() {
    }

    public Long getTotalRepositories() {
        return totalRepositories;
    }

    public void setTotalRepositories(Long totalRepositories) {
        this.totalRepositories = totalRepositories;
    }

    public Long getTotalScans() {
        return totalScans;
    }

    public void setTotalScans(Long totalScans) {
        this.totalScans = totalScans;
    }

    public Long getTotalRiskAnalyses() {
        return totalRiskAnalyses;
    }

    public void setTotalRiskAnalyses(Long totalRiskAnalyses) {
        this.totalRiskAnalyses = totalRiskAnalyses;
    }

    public Double getAverageRiskScore() {
        return averageRiskScore;
    }

    public void setAverageRiskScore(Double averageRiskScore) {
        this.averageRiskScore = averageRiskScore;
    }

    public Long getHighRiskRepositories() {
        return highRiskRepositories;
    }

    public void setHighRiskRepositories(Long highRiskRepositories) {
        this.highRiskRepositories = highRiskRepositories;
    }

    public Long getMediumRiskRepositories() {
        return mediumRiskRepositories;
    }

    public void setMediumRiskRepositories(Long mediumRiskRepositories) {
        this.mediumRiskRepositories = mediumRiskRepositories;
    }

    public Long getLowRiskRepositories() {
        return lowRiskRepositories;
    }

    public void setLowRiskRepositories(Long lowRiskRepositories) {
        this.lowRiskRepositories = lowRiskRepositories;
    }
}