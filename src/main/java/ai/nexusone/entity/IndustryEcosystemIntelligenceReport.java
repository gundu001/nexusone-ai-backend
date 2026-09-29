package ai.nexusone.entity;

import ai.nexusone.enums.IndustryEcosystemIntelligenceStatus;
import ai.nexusone.enums.IndustryPriority;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "industry_ecosystem_intelligence_reports", indexes = {
        @Index(name = "idx_industry_status", columnList = "status"),
        @Index(name = "idx_industry_priority", columnList = "priority"),
        @Index(name = "idx_industry_created", columnList = "createdAt")
})
public class IndustryEcosystemIntelligenceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 3000)
    private String industryLandscape;

    @Column(nullable = false, length = 3000)
    private String ecosystemAnalysis;

    @Column(nullable = false, length = 3000)
    private String partnerIntelligence;

    @Column(nullable = false, length = 3000)
    private String supplierIntelligence;

    @Column(nullable = false, length = 3000)
    private String strategicRecommendations;

    @Column(nullable = false)
    private Double industryGrowthScore;
    @Column(nullable = false)
    private Double ecosystemStrengthScore;
    @Column(nullable = false)
    private Double partnerHealthScore;
    @Column(nullable = false)
    private Double supplierResilienceScore;
    @Column(nullable = false)
    private Double regulatoryPreparednessScore;
    @Column(nullable = false)
    private Double innovationVelocityScore;
    @Column(nullable = false)
    private Double industryIntelligenceScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IndustryPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IndustryEcosystemIntelligenceStatus status = IndustryEcosystemIntelligenceStatus.GENERATED;

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
        if (status == null) status = IndustryEcosystemIntelligenceStatus.GENERATED;
    }

    @PreUpdate
    void update() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }
    public String getIndustryNarrative() { return industryLandscape; }
    public void setIndustryNarrative(String value) { industryLandscape = value; }
    public String getFinancialOutlook() { return ecosystemAnalysis; }
    public void setFinancialOutlook(String value) { ecosystemAnalysis = value; }
    public String getGrowthStrategy() { return partnerIntelligence; }
    public void setGrowthStrategy(String value) { partnerIntelligence = value; }
    public String getCapitalAllocation() { return supplierIntelligence; }
    public void setCapitalAllocation(String value) { supplierIntelligence = value; }
    public String getEcosystemValueProposition() { return strategicRecommendations; }
    public void setEcosystemValueProposition(String value) { strategicRecommendations = value; }
    public Double getRevenueConfidenceScore() { return industryGrowthScore; }
    public void setRevenueConfidenceScore(Double value) { industryGrowthScore = value; }
    public Double getProfitabilityConfidenceScore() { return ecosystemStrengthScore; }
    public void setProfitabilityConfidenceScore(Double value) { ecosystemStrengthScore = value; }
    public Double getGrowthPotentialScore() { return partnerHealthScore; }
    public void setGrowthPotentialScore(Double value) { partnerHealthScore = value; }
    public Double getCapitalEfficiencyScore() { return supplierResilienceScore; }
    public void setCapitalEfficiencyScore(Double value) { supplierResilienceScore = value; }
    public Double getGovernanceConfidenceScore() { return regulatoryPreparednessScore; }
    public void setGovernanceConfidenceScore(Double value) { regulatoryPreparednessScore = value; }
    public Double getMarketRiskExposure() { return innovationVelocityScore; }
    public void setMarketRiskExposure(Double value) { innovationVelocityScore = value; }
    public Double getIndustryConfidenceScore() { return industryIntelligenceScore; }
    public void setIndustryConfidenceScore(Double value) { industryIntelligenceScore = value; }
    public IndustryPriority getPriority() { return priority; }
    public void setPriority(IndustryPriority value) { priority = value; }
    public IndustryEcosystemIntelligenceStatus getStatus() { return status; }
    public void setStatus(IndustryEcosystemIntelligenceStatus value) { status = value; }
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
