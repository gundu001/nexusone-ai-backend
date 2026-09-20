package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "platform_engineering_assessments", indexes = {
        @Index(name = "idx_platform_status", columnList = "status"),
        @Index(name = "idx_platform_assessed_at", columnList = "assessedAt")
})
public class PlatformEngineeringAssessment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String platformName;
    @Column(nullable=false) private String environment;
    @Column(nullable=false) private String clusterName;
    private double cpuUtilizationPercent;
    private double memoryUtilizationPercent;
    private double storageUtilizationPercent;
    private double deploymentSuccessPercent;
    private int failedWorkloads;
    private double averageLatencyMs;
    private double automationCoveragePercent;
    private int platformScore;
    @Column(nullable=false) private String riskLevel;
    @Column(nullable=false) private String status;
    @Column(nullable=false) private String recommendedAction;
    @Column(length=2500) private String recommendation;
    @Column(length=2500) private String optimizationPlan;
    @Column(nullable=false) private LocalDateTime assessedAt;

    @PrePersist void prePersist(){ if(assessedAt==null) assessedAt=LocalDateTime.now(); }
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getPlatformName(){return platformName;} public void setPlatformName(String v){platformName=v;}
    public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
    public String getClusterName(){return clusterName;} public void setClusterName(String v){clusterName=v;}
    public double getCpuUtilizationPercent(){return cpuUtilizationPercent;} public void setCpuUtilizationPercent(double v){cpuUtilizationPercent=v;}
    public double getMemoryUtilizationPercent(){return memoryUtilizationPercent;} public void setMemoryUtilizationPercent(double v){memoryUtilizationPercent=v;}
    public double getStorageUtilizationPercent(){return storageUtilizationPercent;} public void setStorageUtilizationPercent(double v){storageUtilizationPercent=v;}
    public double getDeploymentSuccessPercent(){return deploymentSuccessPercent;} public void setDeploymentSuccessPercent(double v){deploymentSuccessPercent=v;}
    public int getFailedWorkloads(){return failedWorkloads;} public void setFailedWorkloads(int v){failedWorkloads=v;}
    public double getAverageLatencyMs(){return averageLatencyMs;} public void setAverageLatencyMs(double v){averageLatencyMs=v;}
    public double getAutomationCoveragePercent(){return automationCoveragePercent;} public void setAutomationCoveragePercent(double v){automationCoveragePercent=v;}
    public int getPlatformScore(){return platformScore;} public void setPlatformScore(int v){platformScore=v;}
    public String getRiskLevel(){return riskLevel;} public void setRiskLevel(String v){riskLevel=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getRecommendedAction(){return recommendedAction;} public void setRecommendedAction(String v){recommendedAction=v;}
    public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;}
    public String getOptimizationPlan(){return optimizationPlan;} public void setOptimizationPlan(String v){optimizationPlan=v;}
    public LocalDateTime getAssessedAt(){return assessedAt;} public void setAssessedAt(LocalDateTime v){assessedAt=v;}
}
