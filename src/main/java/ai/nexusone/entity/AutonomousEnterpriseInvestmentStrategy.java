package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "autonomous_enterprise_investment_strategy")
public class AutonomousEnterpriseInvestmentStrategy {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(nullable = false)
 private String title;
 @Lob
 @Column(columnDefinition = "LONGTEXT", nullable = false)
 private String investmentStrategyVision;
 @Lob
 @Column(columnDefinition = "LONGTEXT", nullable = false)
 private String strategicInvestmentRoadmap;
 @Lob
 @Column(columnDefinition = "LONGTEXT", nullable = false)
 private String growthInvestmentStrategy;
 @Lob
 @Column(columnDefinition = "LONGTEXT", nullable = false)
 private String innovationInvestmentStrategy;
 @Lob
 @Column(columnDefinition = "LONGTEXT", nullable = false)
 private String technologyInvestmentStrategy;
 @Lob
 @Column(columnDefinition = "LONGTEXT", nullable = false)
 private String marketExpansionStrategy;
 @Lob
 @Column(columnDefinition = "LONGTEXT", nullable = false)
 private String riskDiversificationStrategy;
 @Lob
 @Column(columnDefinition = "LONGTEXT", nullable = false)
 private String executiveInvestmentDecision;
 @Column(nullable = false)
 private Double strategicInvestmentScore;
 @Column(nullable = false)
 private Double expectedGrowthScore;
 @Column(nullable = false)
 private Double innovationPotentialScore;
 @Column(nullable = false)
 private Double marketOpportunityScore;
 @Column(nullable = false)
 private Double technologyReadinessScore;
 @Column(nullable = false)
 private Double riskDiversificationScore;
 @Column(nullable = false)
 private Double investmentEfficiencyScore;
 @Column(nullable = false)
 private Double executionConfidenceScore;
 @Column(nullable = false)
 private Double enterpriseInvestmentStrategyScore;
 @Enumerated(EnumType.STRING)
 @Column(nullable = false, length = 30)
 private AutonomousEnterpriseInvestmentStrategyPriority priority;
 @Enumerated(EnumType.STRING)
 @Column(nullable = false, length = 30)
 private AutonomousEnterpriseInvestmentStrategyStatus status;
 @Column(nullable = false)
 private String createdBy;
 private LocalDateTime createdAt;
 private LocalDateTime updatedAt;
 @PrePersist
 void onCreate() { createdAt=LocalDateTime.now(); updatedAt=createdAt; if(status==null) status=AutonomousEnterpriseInvestmentStrategyStatus.GENERATED; }
 @PreUpdate
 void onUpdate() { updatedAt=LocalDateTime.now(); }
 public Long getId() { return id; }
 public String getTitle() { return title; }
 public void setTitle(String value) { this.title=value; }
 public String getInvestmentStrategyVision() { return investmentStrategyVision; }
 public void setInvestmentStrategyVision(String value) { this.investmentStrategyVision=value; }
 public String getStrategicInvestmentRoadmap() { return strategicInvestmentRoadmap; }
 public void setStrategicInvestmentRoadmap(String value) { this.strategicInvestmentRoadmap=value; }
 public String getGrowthInvestmentStrategy() { return growthInvestmentStrategy; }
 public void setGrowthInvestmentStrategy(String value) { this.growthInvestmentStrategy=value; }
 public String getInnovationInvestmentStrategy() { return innovationInvestmentStrategy; }
 public void setInnovationInvestmentStrategy(String value) { this.innovationInvestmentStrategy=value; }
 public String getTechnologyInvestmentStrategy() { return technologyInvestmentStrategy; }
 public void setTechnologyInvestmentStrategy(String value) { this.technologyInvestmentStrategy=value; }
 public String getMarketExpansionStrategy() { return marketExpansionStrategy; }
 public void setMarketExpansionStrategy(String value) { this.marketExpansionStrategy=value; }
 public String getRiskDiversificationStrategy() { return riskDiversificationStrategy; }
 public void setRiskDiversificationStrategy(String value) { this.riskDiversificationStrategy=value; }
 public String getExecutiveInvestmentDecision() { return executiveInvestmentDecision; }
 public void setExecutiveInvestmentDecision(String value) { this.executiveInvestmentDecision=value; }
 public Double getStrategicInvestmentScore() { return strategicInvestmentScore; }
 public void setStrategicInvestmentScore(Double value) { this.strategicInvestmentScore=value; }
 public Double getExpectedGrowthScore() { return expectedGrowthScore; }
 public void setExpectedGrowthScore(Double value) { this.expectedGrowthScore=value; }
 public Double getInnovationPotentialScore() { return innovationPotentialScore; }
 public void setInnovationPotentialScore(Double value) { this.innovationPotentialScore=value; }
 public Double getMarketOpportunityScore() { return marketOpportunityScore; }
 public void setMarketOpportunityScore(Double value) { this.marketOpportunityScore=value; }
 public Double getTechnologyReadinessScore() { return technologyReadinessScore; }
 public void setTechnologyReadinessScore(Double value) { this.technologyReadinessScore=value; }
 public Double getRiskDiversificationScore() { return riskDiversificationScore; }
 public void setRiskDiversificationScore(Double value) { this.riskDiversificationScore=value; }
 public Double getInvestmentEfficiencyScore() { return investmentEfficiencyScore; }
 public void setInvestmentEfficiencyScore(Double value) { this.investmentEfficiencyScore=value; }
 public Double getExecutionConfidenceScore() { return executionConfidenceScore; }
 public void setExecutionConfidenceScore(Double value) { this.executionConfidenceScore=value; }
 public Double getEnterpriseInvestmentStrategyScore() { return enterpriseInvestmentStrategyScore; }
 public void setEnterpriseInvestmentStrategyScore(Double value) { this.enterpriseInvestmentStrategyScore=value; }
 public AutonomousEnterpriseInvestmentStrategyPriority getPriority() { return priority; }
 public void setPriority(AutonomousEnterpriseInvestmentStrategyPriority value) { this.priority=value; }
 public AutonomousEnterpriseInvestmentStrategyStatus getStatus() { return status; }
 public void setStatus(AutonomousEnterpriseInvestmentStrategyStatus value) { this.status=value; }
 public String getCreatedBy() { return createdBy; }
 public void setCreatedBy(String value) { this.createdBy=value; }
 public LocalDateTime getCreatedAt() { return createdAt; }
 public LocalDateTime getUpdatedAt() { return updatedAt; }
}