package ai.nexusone.entity;

import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyPriority;
import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyStatus;
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
@Table(name = "autonomous_enterprise_business_strategy")
public class AutonomousEnterpriseBusinessStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String businessStrategyVision;

    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String strategicObjectives;

    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String competitivePositioningStrategy;

    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String customerValueStrategy;

    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String operatingModelStrategy;

    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String digitalBusinessStrategy;

    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String growthExecutionRoadmap;

    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String executiveStrategyDecision;

    @Column(nullable = false) private Double strategicAlignmentScore;
    @Column(nullable = false) private Double marketPositionScore;
    @Column(nullable = false) private Double customerValueScore;
    @Column(nullable = false) private Double operatingModelScore;
    @Column(nullable = false) private Double digitalReadinessScore;
    @Column(nullable = false) private Double growthPotentialScore;
    @Column(nullable = false) private Double riskResilienceScore;
    @Column(nullable = false) private Double executionConfidenceScore;
    @Column(nullable = false) private Double enterpriseBusinessStrategyScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AutonomousEnterpriseBusinessStrategyPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AutonomousEnterpriseBusinessStrategyStatus status;

    @Column(nullable = false)
    private String createdBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        if (status == null) status = AutonomousEnterpriseBusinessStrategyStatus.GENERATED;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBusinessStrategyVision() { return businessStrategyVision; }
    public void setBusinessStrategyVision(String value) { this.businessStrategyVision = value; }
    public String getStrategicObjectives() { return strategicObjectives; }
    public void setStrategicObjectives(String value) { this.strategicObjectives = value; }
    public String getCompetitivePositioningStrategy() { return competitivePositioningStrategy; }
    public void setCompetitivePositioningStrategy(String value) { this.competitivePositioningStrategy = value; }
    public String getCustomerValueStrategy() { return customerValueStrategy; }
    public void setCustomerValueStrategy(String value) { this.customerValueStrategy = value; }
    public String getOperatingModelStrategy() { return operatingModelStrategy; }
    public void setOperatingModelStrategy(String value) { this.operatingModelStrategy = value; }
    public String getDigitalBusinessStrategy() { return digitalBusinessStrategy; }
    public void setDigitalBusinessStrategy(String value) { this.digitalBusinessStrategy = value; }
    public String getGrowthExecutionRoadmap() { return growthExecutionRoadmap; }
    public void setGrowthExecutionRoadmap(String value) { this.growthExecutionRoadmap = value; }
    public String getExecutiveStrategyDecision() { return executiveStrategyDecision; }
    public void setExecutiveStrategyDecision(String value) { this.executiveStrategyDecision = value; }
    public Double getStrategicAlignmentScore() { return strategicAlignmentScore; }
    public void setStrategicAlignmentScore(Double value) { this.strategicAlignmentScore = value; }
    public Double getMarketPositionScore() { return marketPositionScore; }
    public void setMarketPositionScore(Double value) { this.marketPositionScore = value; }
    public Double getCustomerValueScore() { return customerValueScore; }
    public void setCustomerValueScore(Double value) { this.customerValueScore = value; }
    public Double getOperatingModelScore() { return operatingModelScore; }
    public void setOperatingModelScore(Double value) { this.operatingModelScore = value; }
    public Double getDigitalReadinessScore() { return digitalReadinessScore; }
    public void setDigitalReadinessScore(Double value) { this.digitalReadinessScore = value; }
    public Double getGrowthPotentialScore() { return growthPotentialScore; }
    public void setGrowthPotentialScore(Double value) { this.growthPotentialScore = value; }
    public Double getRiskResilienceScore() { return riskResilienceScore; }
    public void setRiskResilienceScore(Double value) { this.riskResilienceScore = value; }
    public Double getExecutionConfidenceScore() { return executionConfidenceScore; }
    public void setExecutionConfidenceScore(Double value) { this.executionConfidenceScore = value; }
    public Double getEnterpriseBusinessStrategyScore() { return enterpriseBusinessStrategyScore; }
    public void setEnterpriseBusinessStrategyScore(Double value) { this.enterpriseBusinessStrategyScore = value; }
    public AutonomousEnterpriseBusinessStrategyPriority getPriority() { return priority; }
    public void setPriority(AutonomousEnterpriseBusinessStrategyPriority value) { this.priority = value; }
    public AutonomousEnterpriseBusinessStrategyStatus getStatus() { return status; }
    public void setStatus(AutonomousEnterpriseBusinessStrategyStatus value) { this.status = value; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String value) { this.createdBy = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
