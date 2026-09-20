package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="executive_intelligence_assessments", indexes={
 @Index(name="idx_exec_status",columnList="status"),
 @Index(name="idx_exec_generated",columnList="generatedAt")
})
public class ExecutiveIntelligenceAssessment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String organizationName;
 @Column(nullable=false) private String reportingPeriod;
 private double sreScore;
 private double platformScore;
 private double governanceScore;
 private double securityScore;
 private double finOpsScore;
 private double disasterRecoveryScore;
 private double autonomousOperationsScore;
 private int openCriticalRisks;
 private int pendingExecutiveDecisions;
 private int enterpriseHealthScore;
 private int businessReadinessScore;
 private int operationalExcellenceScore;
 private int technologyMaturityScore;
 @Column(nullable=false) private String riskLevel;
 @Column(nullable=false) private String status;
 @Column(nullable=false) private String recommendedDecision;
 @Column(length=2500) private String executiveSummary;
 @Column(length=2500) private String recommendation;
 @Column(nullable=false) private LocalDateTime generatedAt;
 @PrePersist void prePersist(){if(generatedAt==null)generatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getOrganizationName(){return organizationName;} public void setOrganizationName(String v){organizationName=v;}
 public String getReportingPeriod(){return reportingPeriod;} public void setReportingPeriod(String v){reportingPeriod=v;}
 public double getSreScore(){return sreScore;} public void setSreScore(double v){sreScore=v;}
 public double getPlatformScore(){return platformScore;} public void setPlatformScore(double v){platformScore=v;}
 public double getGovernanceScore(){return governanceScore;} public void setGovernanceScore(double v){governanceScore=v;}
 public double getSecurityScore(){return securityScore;} public void setSecurityScore(double v){securityScore=v;}
 public double getFinOpsScore(){return finOpsScore;} public void setFinOpsScore(double v){finOpsScore=v;}
 public double getDisasterRecoveryScore(){return disasterRecoveryScore;} public void setDisasterRecoveryScore(double v){disasterRecoveryScore=v;}
 public double getAutonomousOperationsScore(){return autonomousOperationsScore;} public void setAutonomousOperationsScore(double v){autonomousOperationsScore=v;}
 public int getOpenCriticalRisks(){return openCriticalRisks;} public void setOpenCriticalRisks(int v){openCriticalRisks=v;}
 public int getPendingExecutiveDecisions(){return pendingExecutiveDecisions;} public void setPendingExecutiveDecisions(int v){pendingExecutiveDecisions=v;}
 public int getEnterpriseHealthScore(){return enterpriseHealthScore;} public void setEnterpriseHealthScore(int v){enterpriseHealthScore=v;}
 public int getBusinessReadinessScore(){return businessReadinessScore;} public void setBusinessReadinessScore(int v){businessReadinessScore=v;}
 public int getOperationalExcellenceScore(){return operationalExcellenceScore;} public void setOperationalExcellenceScore(int v){operationalExcellenceScore=v;}
 public int getTechnologyMaturityScore(){return technologyMaturityScore;} public void setTechnologyMaturityScore(int v){technologyMaturityScore=v;}
 public String getRiskLevel(){return riskLevel;} public void setRiskLevel(String v){riskLevel=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public String getRecommendedDecision(){return recommendedDecision;} public void setRecommendedDecision(String v){recommendedDecision=v;}
 public String getExecutiveSummary(){return executiveSummary;} public void setExecutiveSummary(String v){executiveSummary=v;}
 public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;}
 public LocalDateTime getGeneratedAt(){return generatedAt;} public void setGeneratedAt(LocalDateTime v){generatedAt=v;}
}
