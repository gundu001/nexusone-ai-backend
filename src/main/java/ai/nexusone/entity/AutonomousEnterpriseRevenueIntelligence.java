package ai.nexusone.entity;

import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "autonomous_enterprise_revenue_intelligence")
public class AutonomousEnterpriseRevenueIntelligence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String title;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String revenueIntelligenceVision;
@Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String revenueGrowthStrategy;
@Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String recurringRevenueStrategy;
@Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String customerRevenueStrategy;
@Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String pricingOptimizationStrategy;
@Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String salesEffectivenessStrategy;
@Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String revenueDiversificationStrategy;
@Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String executiveRevenueDecision;
@Column(nullable = false) private Double revenueGrowthScore;
@Column(nullable = false) private Double recurringRevenueScore;
@Column(nullable = false) private Double customerRevenueScore;
@Column(nullable = false) private Double pricingOptimizationScore;
@Column(nullable = false) private Double salesEffectivenessScore;
@Column(nullable = false) private Double revenueDiversificationScore;
@Column(nullable = false) private Double forecastAccuracyScore;
@Column(nullable = false) private Double executionConfidenceScore;
    @Column(nullable = false) private Double enterpriseRevenueIntelligenceScore;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 30) private AutonomousEnterpriseRevenueIntelligencePriority priority;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 30) private AutonomousEnterpriseRevenueIntelligenceStatus status;
    @Column(nullable = false) private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist void onCreate() { createdAt = LocalDateTime.now(); updatedAt = createdAt; if (status == null) status = AutonomousEnterpriseRevenueIntelligenceStatus.GENERATED; }
    @PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }
    public String getRevenueIntelligenceVision() { return revenueIntelligenceVision; }
    public void setRevenueIntelligenceVision(String value) { this.revenueIntelligenceVision = value; }
    public String getRevenueGrowthStrategy() { return revenueGrowthStrategy; }
    public void setRevenueGrowthStrategy(String value) { this.revenueGrowthStrategy = value; }
    public String getRecurringRevenueStrategy() { return recurringRevenueStrategy; }
    public void setRecurringRevenueStrategy(String value) { this.recurringRevenueStrategy = value; }
    public String getCustomerRevenueStrategy() { return customerRevenueStrategy; }
    public void setCustomerRevenueStrategy(String value) { this.customerRevenueStrategy = value; }
    public String getPricingOptimizationStrategy() { return pricingOptimizationStrategy; }
    public void setPricingOptimizationStrategy(String value) { this.pricingOptimizationStrategy = value; }
    public String getSalesEffectivenessStrategy() { return salesEffectivenessStrategy; }
    public void setSalesEffectivenessStrategy(String value) { this.salesEffectivenessStrategy = value; }
    public String getRevenueDiversificationStrategy() { return revenueDiversificationStrategy; }
    public void setRevenueDiversificationStrategy(String value) { this.revenueDiversificationStrategy = value; }
    public String getExecutiveRevenueDecision() { return executiveRevenueDecision; }
    public void setExecutiveRevenueDecision(String value) { this.executiveRevenueDecision = value; }
    public Double getRevenueGrowthScore() { return revenueGrowthScore; }
    public void setRevenueGrowthScore(Double value) { this.revenueGrowthScore = value; }
    public Double getRecurringRevenueScore() { return recurringRevenueScore; }
    public void setRecurringRevenueScore(Double value) { this.recurringRevenueScore = value; }
    public Double getCustomerRevenueScore() { return customerRevenueScore; }
    public void setCustomerRevenueScore(Double value) { this.customerRevenueScore = value; }
    public Double getPricingOptimizationScore() { return pricingOptimizationScore; }
    public void setPricingOptimizationScore(Double value) { this.pricingOptimizationScore = value; }
    public Double getSalesEffectivenessScore() { return salesEffectivenessScore; }
    public void setSalesEffectivenessScore(Double value) { this.salesEffectivenessScore = value; }
    public Double getRevenueDiversificationScore() { return revenueDiversificationScore; }
    public void setRevenueDiversificationScore(Double value) { this.revenueDiversificationScore = value; }
    public Double getForecastAccuracyScore() { return forecastAccuracyScore; }
    public void setForecastAccuracyScore(Double value) { this.forecastAccuracyScore = value; }
    public Double getExecutionConfidenceScore() { return executionConfidenceScore; }
    public void setExecutionConfidenceScore(Double value) { this.executionConfidenceScore = value; }
    public Double getEnterpriseRevenueIntelligenceScore() { return enterpriseRevenueIntelligenceScore; }
    public void setEnterpriseRevenueIntelligenceScore(Double value) { this.enterpriseRevenueIntelligenceScore = value; }
    public AutonomousEnterpriseRevenueIntelligencePriority getPriority() { return priority; }
    public void setPriority(AutonomousEnterpriseRevenueIntelligencePriority value) { this.priority = value; }
    public AutonomousEnterpriseRevenueIntelligenceStatus getStatus() { return status; }
    public void setStatus(AutonomousEnterpriseRevenueIntelligenceStatus value) { this.status = value; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String value) { this.createdBy = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
