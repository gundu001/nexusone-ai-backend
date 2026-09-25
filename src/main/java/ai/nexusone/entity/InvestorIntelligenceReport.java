package ai.nexusone.entity;

import ai.nexusone.enums.InvestorIntelligenceStatus;
import ai.nexusone.enums.InvestorPriority;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "investor_intelligence_reports", indexes = {
        @Index(name = "idx_investor_status", columnList = "status"),
        @Index(name = "idx_investor_priority", columnList = "priority"),
        @Index(name = "idx_investor_created", columnList = "createdAt")
})
public class InvestorIntelligenceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 3000)
    private String investorNarrative;

    @Column(nullable = false, length = 3000)
    private String financialOutlook;

    @Column(nullable = false, length = 3000)
    private String growthStrategy;

    @Column(nullable = false, length = 3000)
    private String capitalAllocation;

    @Column(nullable = false, length = 3000)
    private String shareholderValueProposition;

    @Column(nullable = false)
    private Double revenueConfidenceScore;
    @Column(nullable = false)
    private Double profitabilityConfidenceScore;
    @Column(nullable = false)
    private Double growthPotentialScore;
    @Column(nullable = false)
    private Double capitalEfficiencyScore;
    @Column(nullable = false)
    private Double governanceConfidenceScore;
    @Column(nullable = false)
    private Double marketRiskExposure;
    @Column(nullable = false)
    private Double investorConfidenceScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvestorPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvestorIntelligenceStatus status = InvestorIntelligenceStatus.GENERATED;

    @Column(nullable = false)
    private String createdBy;

    private String reviewedBy;
    private String decidedBy;
    private String publishedBy;
    private LocalDateTime reviewedAt;
    private LocalDateTime decidedAt;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void create() {
        createdAt = updatedAt = LocalDateTime.now();
        if (status == null) status = InvestorIntelligenceStatus.GENERATED;
    }

    @PreUpdate
    void update() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }
    public String getInvestorNarrative() { return investorNarrative; }
    public void setInvestorNarrative(String value) { investorNarrative = value; }
    public String getFinancialOutlook() { return financialOutlook; }
    public void setFinancialOutlook(String value) { financialOutlook = value; }
    public String getGrowthStrategy() { return growthStrategy; }
    public void setGrowthStrategy(String value) { growthStrategy = value; }
    public String getCapitalAllocation() { return capitalAllocation; }
    public void setCapitalAllocation(String value) { capitalAllocation = value; }
    public String getShareholderValueProposition() { return shareholderValueProposition; }
    public void setShareholderValueProposition(String value) { shareholderValueProposition = value; }
    public Double getRevenueConfidenceScore() { return revenueConfidenceScore; }
    public void setRevenueConfidenceScore(Double value) { revenueConfidenceScore = value; }
    public Double getProfitabilityConfidenceScore() { return profitabilityConfidenceScore; }
    public void setProfitabilityConfidenceScore(Double value) { profitabilityConfidenceScore = value; }
    public Double getGrowthPotentialScore() { return growthPotentialScore; }
    public void setGrowthPotentialScore(Double value) { growthPotentialScore = value; }
    public Double getCapitalEfficiencyScore() { return capitalEfficiencyScore; }
    public void setCapitalEfficiencyScore(Double value) { capitalEfficiencyScore = value; }
    public Double getGovernanceConfidenceScore() { return governanceConfidenceScore; }
    public void setGovernanceConfidenceScore(Double value) { governanceConfidenceScore = value; }
    public Double getMarketRiskExposure() { return marketRiskExposure; }
    public void setMarketRiskExposure(Double value) { marketRiskExposure = value; }
    public Double getInvestorConfidenceScore() { return investorConfidenceScore; }
    public void setInvestorConfidenceScore(Double value) { investorConfidenceScore = value; }
    public InvestorPriority getPriority() { return priority; }
    public void setPriority(InvestorPriority value) { priority = value; }
    public InvestorIntelligenceStatus getStatus() { return status; }
    public void setStatus(InvestorIntelligenceStatus value) { status = value; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String value) { createdBy = value; }
    public String getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(String value) { reviewedBy = value; }
    public String getDecidedBy() { return decidedBy; }
    public void setDecidedBy(String value) { decidedBy = value; }
    public String getPublishedBy() { return publishedBy; }
    public void setPublishedBy(String value) { publishedBy = value; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime value) { reviewedAt = value; }
    public LocalDateTime getDecidedAt() { return decidedAt; }
    public void setDecidedAt(LocalDateTime value) { decidedAt = value; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime value) { publishedAt = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
