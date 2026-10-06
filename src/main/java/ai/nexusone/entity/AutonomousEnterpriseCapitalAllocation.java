package ai.nexusone.entity;

import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationPriority;
import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "autonomous_enterprise_capital_allocation")
public class AutonomousEnterpriseCapitalAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String capitalAllocationVision;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String investmentPortfolio;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String strategicPriorities;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String fundingRecommendation;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String riskAdjustedAllocation;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String liquidityAssessment;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String returnOptimizationAnalysis;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String executiveCapitalDecision;

    private Double strategicAlignmentScore;
    private Double expectedReturnScore;
    private Double riskAdjustedReturnScore;
    private Double liquidityScore;
    private Double portfolioBalanceScore;
    private Double growthCapacityScore;
    private Double resilienceScore;
    private Double capitalEfficiencyScore;
    private Double enterpriseCapitalAllocationScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AutonomousEnterpriseCapitalAllocationPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AutonomousEnterpriseCapitalAllocationStatus status;

    @Column(nullable = false, length = 150)
    private String createdBy;

    private String reviewedBy;
    private String decidedBy;
    private String publishedBy;
    private LocalDateTime reviewedAt;
    private LocalDateTime decidedAt;
    private LocalDateTime publishedAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        if (status == null) {
            status = AutonomousEnterpriseCapitalAllocationStatus.GENERATED;
        }
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCapitalAllocationVision() { return capitalAllocationVision; }
    public void setCapitalAllocationVision(String value) { this.capitalAllocationVision = value; }
    public String getInvestmentPortfolio() { return investmentPortfolio; }
    public void setInvestmentPortfolio(String value) { this.investmentPortfolio = value; }
    public String getStrategicPriorities() { return strategicPriorities; }
    public void setStrategicPriorities(String value) { this.strategicPriorities = value; }
    public String getFundingRecommendation() { return fundingRecommendation; }
    public void setFundingRecommendation(String value) { this.fundingRecommendation = value; }
    public String getRiskAdjustedAllocation() { return riskAdjustedAllocation; }
    public void setRiskAdjustedAllocation(String value) { this.riskAdjustedAllocation = value; }
    public String getLiquidityAssessment() { return liquidityAssessment; }
    public void setLiquidityAssessment(String value) { this.liquidityAssessment = value; }
    public String getReturnOptimizationAnalysis() { return returnOptimizationAnalysis; }
    public void setReturnOptimizationAnalysis(String value) { this.returnOptimizationAnalysis = value; }
    public String getExecutiveCapitalDecision() { return executiveCapitalDecision; }
    public void setExecutiveCapitalDecision(String value) { this.executiveCapitalDecision = value; }
    public Double getStrategicAlignmentScore() { return strategicAlignmentScore; }
    public void setStrategicAlignmentScore(Double value) { this.strategicAlignmentScore = value; }
    public Double getExpectedReturnScore() { return expectedReturnScore; }
    public void setExpectedReturnScore(Double value) { this.expectedReturnScore = value; }
    public Double getRiskAdjustedReturnScore() { return riskAdjustedReturnScore; }
    public void setRiskAdjustedReturnScore(Double value) { this.riskAdjustedReturnScore = value; }
    public Double getLiquidityScore() { return liquidityScore; }
    public void setLiquidityScore(Double value) { this.liquidityScore = value; }
    public Double getPortfolioBalanceScore() { return portfolioBalanceScore; }
    public void setPortfolioBalanceScore(Double value) { this.portfolioBalanceScore = value; }
    public Double getGrowthCapacityScore() { return growthCapacityScore; }
    public void setGrowthCapacityScore(Double value) { this.growthCapacityScore = value; }
    public Double getResilienceScore() { return resilienceScore; }
    public void setResilienceScore(Double value) { this.resilienceScore = value; }
    public Double getCapitalEfficiencyScore() { return capitalEfficiencyScore; }
    public void setCapitalEfficiencyScore(Double value) { this.capitalEfficiencyScore = value; }
    public Double getEnterpriseCapitalAllocationScore() { return enterpriseCapitalAllocationScore; }
    public void setEnterpriseCapitalAllocationScore(Double value) { this.enterpriseCapitalAllocationScore = value; }
    public AutonomousEnterpriseCapitalAllocationPriority getPriority() { return priority; }
    public void setPriority(AutonomousEnterpriseCapitalAllocationPriority priority) { this.priority = priority; }
    public AutonomousEnterpriseCapitalAllocationStatus getStatus() { return status; }
    public void setStatus(AutonomousEnterpriseCapitalAllocationStatus status) { this.status = status; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public String getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(String reviewedBy) { this.reviewedBy = reviewedBy; }
    public String getDecidedBy() { return decidedBy; }
    public void setDecidedBy(String decidedBy) { this.decidedBy = decidedBy; }
    public String getPublishedBy() { return publishedBy; }
    public void setPublishedBy(String publishedBy) { this.publishedBy = publishedBy; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime reviewedAt) { this.reviewedAt = reviewedAt; }
    public LocalDateTime getDecidedAt() { return decidedAt; }
    public void setDecidedAt(LocalDateTime decidedAt) { this.decidedAt = decidedAt; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime publishedAt) { this.publishedAt = publishedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
