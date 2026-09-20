package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "autonomous_operations", indexes = {
        @Index(name = "idx_autonomous_operation_status", columnList = "status"),
        @Index(name = "idx_autonomous_operation_created", columnList = "createdAt")
})
public class AutonomousOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String applicationName;
    @Column(nullable=false) private String environment;
    @Column(nullable=false) private String signalType;
    private int severityScore;
    private int confidenceScore;
    private double currentCpuPercent;
    private double currentMemoryPercent;
    private double errorRatePercent;
    private double responseTimeMs;
    @Column(nullable=false) private String recommendedAction;
    @Column(nullable=false) private String executionMode;
    @Column(nullable=false) private String riskLevel;
    @Column(nullable=false) private String status;
    @Column(length=2000) private String reasoning;
    @Column(length=2000) private String executionPlan;
    @Column(length=2000) private String rollbackPlan;
    private String approvedBy;
    private String executedBy;
    private String approvalComments;
    private boolean dryRun;
    private LocalDateTime approvedAt;
    private LocalDateTime executedAt;
    @Column(nullable=false) private LocalDateTime createdAt;

    @PrePersist void prePersist(){ if(createdAt==null) createdAt=LocalDateTime.now(); }
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
    public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
    public String getSignalType(){return signalType;} public void setSignalType(String v){signalType=v;}
    public int getSeverityScore(){return severityScore;} public void setSeverityScore(int v){severityScore=v;}
    public int getConfidenceScore(){return confidenceScore;} public void setConfidenceScore(int v){confidenceScore=v;}
    public double getCurrentCpuPercent(){return currentCpuPercent;} public void setCurrentCpuPercent(double v){currentCpuPercent=v;}
    public double getCurrentMemoryPercent(){return currentMemoryPercent;} public void setCurrentMemoryPercent(double v){currentMemoryPercent=v;}
    public double getErrorRatePercent(){return errorRatePercent;} public void setErrorRatePercent(double v){errorRatePercent=v;}
    public double getResponseTimeMs(){return responseTimeMs;} public void setResponseTimeMs(double v){responseTimeMs=v;}
    public String getRecommendedAction(){return recommendedAction;} public void setRecommendedAction(String v){recommendedAction=v;}
    public String getExecutionMode(){return executionMode;} public void setExecutionMode(String v){executionMode=v;}
    public String getRiskLevel(){return riskLevel;} public void setRiskLevel(String v){riskLevel=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getReasoning(){return reasoning;} public void setReasoning(String v){reasoning=v;}
    public String getExecutionPlan(){return executionPlan;} public void setExecutionPlan(String v){executionPlan=v;}
    public String getRollbackPlan(){return rollbackPlan;} public void setRollbackPlan(String v){rollbackPlan=v;}
    public String getApprovedBy(){return approvedBy;} public void setApprovedBy(String v){approvedBy=v;}
    public String getExecutedBy(){return executedBy;} public void setExecutedBy(String v){executedBy=v;}
    public String getApprovalComments(){return approvalComments;} public void setApprovalComments(String v){approvalComments=v;}
    public boolean isDryRun(){return dryRun;} public void setDryRun(boolean v){dryRun=v;}
    public LocalDateTime getApprovedAt(){return approvedAt;} public void setApprovedAt(LocalDateTime v){approvedAt=v;}
    public LocalDateTime getExecutedAt(){return executedAt;} public void setExecutedAt(LocalDateTime v){executedAt=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
