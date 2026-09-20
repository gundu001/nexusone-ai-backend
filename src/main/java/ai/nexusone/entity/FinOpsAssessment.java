package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="finops_assessments", indexes={
 @Index(name="idx_finops_status",columnList="budgetStatus"),
 @Index(name="idx_finops_assessed",columnList="assessedAt")
})
public class FinOpsAssessment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String applicationName;
 @Column(nullable=false) private String environment;
 @Column(nullable=false) private String cloudProvider;
 @Column(nullable=false) private String currency;
 private double monthlySpend;
 private double monthlyBudget;
 private double previousMonthSpend;
 private int idleResources;
 private int rightsizingCandidates;
 private double storageWastePercent;
 private double reservedCapacityCoveragePercent;
 private double taggedResourceCoveragePercent;
 private double budgetUtilizationPercent;
 private double spendChangePercent;
 private double forecastedMonthlySpend;
 private double savingsOpportunity;
 private int costEfficiencyScore;
 private int optimizationScore;
 @Column(nullable=false) private String budgetStatus;
 @Column(nullable=false) private String riskLevel;
 @Column(nullable=false) private String recommendedAction;
 @Column(length=2500) private String recommendation;
 @Column(length=2500) private String optimizationPlan;
 @Column(nullable=false) private LocalDateTime assessedAt;
 @PrePersist void prePersist(){if(assessedAt==null)assessedAt=LocalDateTime.now();}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
 public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
 public String getCloudProvider(){return cloudProvider;} public void setCloudProvider(String v){cloudProvider=v;}
 public String getCurrency(){return currency;} public void setCurrency(String v){currency=v;}
 public double getMonthlySpend(){return monthlySpend;} public void setMonthlySpend(double v){monthlySpend=v;}
 public double getMonthlyBudget(){return monthlyBudget;} public void setMonthlyBudget(double v){monthlyBudget=v;}
 public double getPreviousMonthSpend(){return previousMonthSpend;} public void setPreviousMonthSpend(double v){previousMonthSpend=v;}
 public int getIdleResources(){return idleResources;} public void setIdleResources(int v){idleResources=v;}
 public int getRightsizingCandidates(){return rightsizingCandidates;} public void setRightsizingCandidates(int v){rightsizingCandidates=v;}
 public double getStorageWastePercent(){return storageWastePercent;} public void setStorageWastePercent(double v){storageWastePercent=v;}
 public double getReservedCapacityCoveragePercent(){return reservedCapacityCoveragePercent;} public void setReservedCapacityCoveragePercent(double v){reservedCapacityCoveragePercent=v;}
 public double getTaggedResourceCoveragePercent(){return taggedResourceCoveragePercent;} public void setTaggedResourceCoveragePercent(double v){taggedResourceCoveragePercent=v;}
 public double getBudgetUtilizationPercent(){return budgetUtilizationPercent;} public void setBudgetUtilizationPercent(double v){budgetUtilizationPercent=v;}
 public double getSpendChangePercent(){return spendChangePercent;} public void setSpendChangePercent(double v){spendChangePercent=v;}
 public double getForecastedMonthlySpend(){return forecastedMonthlySpend;} public void setForecastedMonthlySpend(double v){forecastedMonthlySpend=v;}
 public double getSavingsOpportunity(){return savingsOpportunity;} public void setSavingsOpportunity(double v){savingsOpportunity=v;}
 public int getCostEfficiencyScore(){return costEfficiencyScore;} public void setCostEfficiencyScore(int v){costEfficiencyScore=v;}
 public int getOptimizationScore(){return optimizationScore;} public void setOptimizationScore(int v){optimizationScore=v;}
 public String getBudgetStatus(){return budgetStatus;} public void setBudgetStatus(String v){budgetStatus=v;}
 public String getRiskLevel(){return riskLevel;} public void setRiskLevel(String v){riskLevel=v;}
 public String getRecommendedAction(){return recommendedAction;} public void setRecommendedAction(String v){recommendedAction=v;}
 public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;}
 public String getOptimizationPlan(){return optimizationPlan;} public void setOptimizationPlan(String v){optimizationPlan=v;}
 public LocalDateTime getAssessedAt(){return assessedAt;} public void setAssessedAt(LocalDateTime v){assessedAt=v;}
}
