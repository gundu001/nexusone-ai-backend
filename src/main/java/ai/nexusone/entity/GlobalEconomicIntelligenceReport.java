package ai.nexusone.entity;

import ai.nexusone.enums.GlobalEconomicPriority;
import ai.nexusone.enums.GlobalEconomicStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "global_economic_intelligence_reports", indexes = {
        @Index(name = "idx_global_economic_status", columnList = "status"),
        @Index(name = "idx_global_economic_priority", columnList = "priority"),
        @Index(name = "idx_global_economic_created", columnList = "created_at")
})
public class GlobalEconomicIntelligenceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String globalEconomicOutlook;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String inflationAnalysis;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String interestRateAnalysis;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String currencyVolatilityAnalysis;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String tradeMarketAnalysis;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String strategicRecommendations;

    @Column(nullable = false)
    private Double economicGrowthScore;

    @Column(nullable = false)
    private Double inflationStabilityScore;

    @Column(nullable = false)
    private Double interestRateStabilityScore;

    @Column(nullable = false)
    private Double currencyStabilityScore;

    @Column(nullable = false)
    private Double tradeResilienceScore;

    @Column(nullable = false)
    private Double laborMarketStrengthScore;

    @Column(nullable = false)
    private Double supplyChainResilienceScore;

    @Column(nullable = false)
    private Double globalEconomicIntelligenceScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GlobalEconomicPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GlobalEconomicStatus status = GlobalEconomicStatus.GENERATED;

    @Column(nullable = false)
    private String createdBy;

    private String reviewedBy;
    private String decidedBy;
    private String publishedBy;
    private LocalDateTime reviewedAt;
    private LocalDateTime decidedAt;
    private LocalDateTime publishedAt;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void create() {
        createdAt = updatedAt = LocalDateTime.now();
        if (status == null) status = GlobalEconomicStatus.GENERATED;
    }

    @PreUpdate
    void update() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }
    public String getGlobalEconomicOutlook() { return globalEconomicOutlook; }
    public void setGlobalEconomicOutlook(String value) { globalEconomicOutlook = value; }
    public String getInflationAnalysis() { return inflationAnalysis; }
    public void setInflationAnalysis(String value) { inflationAnalysis = value; }
    public String getInterestRateAnalysis() { return interestRateAnalysis; }
    public void setInterestRateAnalysis(String value) { interestRateAnalysis = value; }
    public String getCurrencyVolatilityAnalysis() { return currencyVolatilityAnalysis; }
    public void setCurrencyVolatilityAnalysis(String value) { currencyVolatilityAnalysis = value; }
    public String getTradeMarketAnalysis() { return tradeMarketAnalysis; }
    public void setTradeMarketAnalysis(String value) { tradeMarketAnalysis = value; }
    public String getStrategicRecommendations() { return strategicRecommendations; }
    public void setStrategicRecommendations(String value) { strategicRecommendations = value; }
    public Double getEconomicGrowthScore() { return economicGrowthScore; }
    public void setEconomicGrowthScore(Double value) { economicGrowthScore = value; }
    public Double getInflationStabilityScore() { return inflationStabilityScore; }
    public void setInflationStabilityScore(Double value) { inflationStabilityScore = value; }
    public Double getInterestRateStabilityScore() { return interestRateStabilityScore; }
    public void setInterestRateStabilityScore(Double value) { interestRateStabilityScore = value; }
    public Double getCurrencyStabilityScore() { return currencyStabilityScore; }
    public void setCurrencyStabilityScore(Double value) { currencyStabilityScore = value; }
    public Double getTradeResilienceScore() { return tradeResilienceScore; }
    public void setTradeResilienceScore(Double value) { tradeResilienceScore = value; }
    public Double getLaborMarketStrengthScore() { return laborMarketStrengthScore; }
    public void setLaborMarketStrengthScore(Double value) { laborMarketStrengthScore = value; }
    public Double getSupplyChainResilienceScore() { return supplyChainResilienceScore; }
    public void setSupplyChainResilienceScore(Double value) { supplyChainResilienceScore = value; }
    public Double getGlobalEconomicIntelligenceScore() { return globalEconomicIntelligenceScore; }
    public void setGlobalEconomicIntelligenceScore(Double value) { globalEconomicIntelligenceScore = value; }
    public GlobalEconomicPriority getPriority() { return priority; }
    public void setPriority(GlobalEconomicPriority value) { priority = value; }
    public GlobalEconomicStatus getStatus() { return status; }
    public void setStatus(GlobalEconomicStatus value) { status = value; }
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
