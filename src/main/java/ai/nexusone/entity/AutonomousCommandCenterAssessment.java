package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "autonomous_command_center_assessments", indexes = {
        @Index(name = "idx_acc_status", columnList = "status"),
        @Index(name = "idx_acc_risk_level", columnList = "riskLevel"),
        @Index(name = "idx_acc_created", columnList = "createdAt")
})
public class AutonomousCommandCenterAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String organizationName;
    @Column(nullable = false) private String environment;
    private double sreScore;
    private double platformScore;
    private double governanceSecurityScore;
    private double finOpsScore;
    private double disasterRecoveryScore;
    private double autonomousOperationsScore;
    private double executiveReadinessScore;
    private int openCriticalRisks;
    private int pendingGovernedActions;
    private int enterpriseHealthScore;
    private int enterpriseRiskScore;
    private int enterpriseReadinessScore;
    private int autonomyMaturityScore;
    @Column(nullable = false) private String riskLevel;
    @Column(nullable = false) private String status;
    @Column(nullable = false) private String recommendedDecision;
    @Column(length = 2500) private String enterpriseSummary;
    @Column(length = 2500) private String recommendation;
    @Column(length = 2500) private String governedActionPlan;
    @Column(nullable = false) private LocalDateTime createdAt;

    @PrePersist
    void prePersist() { if (createdAt == null) createdAt = LocalDateTime.now(); }

    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getOrganizationName(){return organizationName;} public void setOrganizationName(String v){organizationName=v;}
    public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
    public double getSreScore(){return sreScore;} public void setSreScore(double v){sreScore=v;}
    public double getPlatformScore(){return platformScore;} public void setPlatformScore(double v){platformScore=v;}
    public double getGovernanceSecurityScore(){return governanceSecurityScore;} public void setGovernanceSecurityScore(double v){governanceSecurityScore=v;}
    public double getFinOpsScore(){return finOpsScore;} public void setFinOpsScore(double v){finOpsScore=v;}
    public double getDisasterRecoveryScore(){return disasterRecoveryScore;} public void setDisasterRecoveryScore(double v){disasterRecoveryScore=v;}
    public double getAutonomousOperationsScore(){return autonomousOperationsScore;} public void setAutonomousOperationsScore(double v){autonomousOperationsScore=v;}
    public double getExecutiveReadinessScore(){return executiveReadinessScore;} public void setExecutiveReadinessScore(double v){executiveReadinessScore=v;}
    public int getOpenCriticalRisks(){return openCriticalRisks;} public void setOpenCriticalRisks(int v){openCriticalRisks=v;}
    public int getPendingGovernedActions(){return pendingGovernedActions;} public void setPendingGovernedActions(int v){pendingGovernedActions=v;}
    public int getEnterpriseHealthScore(){return enterpriseHealthScore;} public void setEnterpriseHealthScore(int v){enterpriseHealthScore=v;}
    public int getEnterpriseRiskScore(){return enterpriseRiskScore;} public void setEnterpriseRiskScore(int v){enterpriseRiskScore=v;}
    public int getEnterpriseReadinessScore(){return enterpriseReadinessScore;} public void setEnterpriseReadinessScore(int v){enterpriseReadinessScore=v;}
    public int getAutonomyMaturityScore(){return autonomyMaturityScore;} public void setAutonomyMaturityScore(int v){autonomyMaturityScore=v;}
    public String getRiskLevel(){return riskLevel;} public void setRiskLevel(String v){riskLevel=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getRecommendedDecision(){return recommendedDecision;} public void setRecommendedDecision(String v){recommendedDecision=v;}
    public String getEnterpriseSummary(){return enterpriseSummary;} public void setEnterpriseSummary(String v){enterpriseSummary=v;}
    public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;}
    public String getGovernedActionPlan(){return governedActionPlan;} public void setGovernedActionPlan(String v){governedActionPlan=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
