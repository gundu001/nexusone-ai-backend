package ai.nexusone.entity;

import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "autonomous_enterprise_profitability_intelligence")
public class AutonomousEnterpriseProfitabilityIntelligence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String profitabilityIntelligenceVision;
@Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String marginOptimizationStrategy;
@Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String costEfficiencyStrategy;
@Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String productProfitabilityStrategy;
@Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String customerProfitabilityStrategy;
@Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String operatingLeverageStrategy;
@Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String cashFlowOptimizationStrategy;
@Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String executiveProfitabilityDecision;
@Column(nullable = false)
    private Double grossMarginScore;
@Column(nullable = false)
    private Double operatingMarginScore;
@Column(nullable = false)
    private Double costEfficiencyScore;
@Column(nullable = false)
    private Double productProfitabilityScore;
@Column(nullable = false)
    private Double customerProfitabilityScore;
@Column(nullable = false)
    private Double operatingLeverageScore;
@Column(nullable = false)
    private Double cashFlowStrengthScore;
@Column(nullable = false)
    private Double executionConfidenceScore;

    @Column(nullable = false)
    private Double enterpriseProfitabilityIntelligenceScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AutonomousEnterpriseProfitabilityIntelligencePriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AutonomousEnterpriseProfitabilityIntelligenceStatus status;

    @Column(nullable = false)
    private String createdBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        if (status == null) status = AutonomousEnterpriseProfitabilityIntelligenceStatus.GENERATED;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getProfitabilityIntelligenceVision() {
        return profitabilityIntelligenceVision;
    }

    public void setProfitabilityIntelligenceVision(String value) {
        this.profitabilityIntelligenceVision = value;
    }

    public String getMarginOptimizationStrategy() {
        return marginOptimizationStrategy;
    }

    public void setMarginOptimizationStrategy(String value) {
        this.marginOptimizationStrategy = value;
    }

    public String getCostEfficiencyStrategy() {
        return costEfficiencyStrategy;
    }

    public void setCostEfficiencyStrategy(String value) {
        this.costEfficiencyStrategy = value;
    }

    public String getProductProfitabilityStrategy() {
        return productProfitabilityStrategy;
    }

    public void setProductProfitabilityStrategy(String value) {
        this.productProfitabilityStrategy = value;
    }

    public String getCustomerProfitabilityStrategy() {
        return customerProfitabilityStrategy;
    }

    public void setCustomerProfitabilityStrategy(String value) {
        this.customerProfitabilityStrategy = value;
    }

    public String getOperatingLeverageStrategy() {
        return operatingLeverageStrategy;
    }

    public void setOperatingLeverageStrategy(String value) {
        this.operatingLeverageStrategy = value;
    }

    public String getCashFlowOptimizationStrategy() {
        return cashFlowOptimizationStrategy;
    }

    public void setCashFlowOptimizationStrategy(String value) {
        this.cashFlowOptimizationStrategy = value;
    }

    public String getExecutiveProfitabilityDecision() {
        return executiveProfitabilityDecision;
    }

    public void setExecutiveProfitabilityDecision(String value) {
        this.executiveProfitabilityDecision = value;
    }

    public Double getGrossMarginScore() {
        return grossMarginScore;
    }

    public void setGrossMarginScore(Double value) {
        this.grossMarginScore = value;
    }

    public Double getOperatingMarginScore() {
        return operatingMarginScore;
    }

    public void setOperatingMarginScore(Double value) {
        this.operatingMarginScore = value;
    }

    public Double getCostEfficiencyScore() {
        return costEfficiencyScore;
    }

    public void setCostEfficiencyScore(Double value) {
        this.costEfficiencyScore = value;
    }

    public Double getProductProfitabilityScore() {
        return productProfitabilityScore;
    }

    public void setProductProfitabilityScore(Double value) {
        this.productProfitabilityScore = value;
    }

    public Double getCustomerProfitabilityScore() {
        return customerProfitabilityScore;
    }

    public void setCustomerProfitabilityScore(Double value) {
        this.customerProfitabilityScore = value;
    }

    public Double getOperatingLeverageScore() {
        return operatingLeverageScore;
    }

    public void setOperatingLeverageScore(Double value) {
        this.operatingLeverageScore = value;
    }

    public Double getCashFlowStrengthScore() {
        return cashFlowStrengthScore;
    }

    public void setCashFlowStrengthScore(Double value) {
        this.cashFlowStrengthScore = value;
    }

    public Double getExecutionConfidenceScore() {
        return executionConfidenceScore;
    }

    public void setExecutionConfidenceScore(Double value) {
        this.executionConfidenceScore = value;
    }

    public Double getEnterpriseProfitabilityIntelligenceScore() { return enterpriseProfitabilityIntelligenceScore; }
    public void setEnterpriseProfitabilityIntelligenceScore(Double value) { this.enterpriseProfitabilityIntelligenceScore = value; }
    public AutonomousEnterpriseProfitabilityIntelligencePriority getPriority() { return priority; }
    public void setPriority(AutonomousEnterpriseProfitabilityIntelligencePriority value) { this.priority = value; }
    public AutonomousEnterpriseProfitabilityIntelligenceStatus getStatus() { return status; }
    public void setStatus(AutonomousEnterpriseProfitabilityIntelligenceStatus value) { this.status = value; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String value) { this.createdBy = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
