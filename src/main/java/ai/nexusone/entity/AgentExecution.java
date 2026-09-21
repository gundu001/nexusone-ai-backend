package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agentic_enterprise_executions", indexes = {
        @Index(name = "idx_agent_execution_type", columnList = "agentType"),
        @Index(name = "idx_agent_execution_status", columnList = "executionStatus"),
        @Index(name = "idx_agent_execution_created", columnList = "createdAt")
})
public class AgentExecution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String agentType;
    @Column(nullable = false) private String applicationName;
    @Column(nullable = false) private String environment;
    @Column(nullable = false) private String proposedAction;
    @Column(nullable = false) private String riskLevel;
    private boolean approvalRequired;
    private double confidenceScore;
    private double targetHealthScore;
    private boolean automated;
    @Column(nullable = false) private String executionStatus;
    @Column(nullable = false) private String approvalStatus;
    @Column(nullable = false) private String recommendedDecision;
    @Column(length = 2500) private String executionResult;
    @Column(length = 2500) private String recommendation;
    @Column(length = 2500) private String verificationPlan;
    @Column(nullable = false) private LocalDateTime createdAt;

    @PrePersist
    void prePersist() { if (createdAt == null) createdAt = LocalDateTime.now(); }

    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getAgentType(){return agentType;} public void setAgentType(String v){agentType=v;}
    public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
    public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
    public String getProposedAction(){return proposedAction;} public void setProposedAction(String v){proposedAction=v;}
    public String getRiskLevel(){return riskLevel;} public void setRiskLevel(String v){riskLevel=v;}
    public boolean isApprovalRequired(){return approvalRequired;} public void setApprovalRequired(boolean v){approvalRequired=v;}
    public double getConfidenceScore(){return confidenceScore;} public void setConfidenceScore(double v){confidenceScore=v;}
    public double getTargetHealthScore(){return targetHealthScore;} public void setTargetHealthScore(double v){targetHealthScore=v;}
    public boolean isAutomated(){return automated;} public void setAutomated(boolean v){automated=v;}
    public String getExecutionStatus(){return executionStatus;} public void setExecutionStatus(String v){executionStatus=v;}
    public String getApprovalStatus(){return approvalStatus;} public void setApprovalStatus(String v){approvalStatus=v;}
    public String getRecommendedDecision(){return recommendedDecision;} public void setRecommendedDecision(String v){recommendedDecision=v;}
    public String getExecutionResult(){return executionResult;} public void setExecutionResult(String v){executionResult=v;}
    public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;}
    public String getVerificationPlan(){return verificationPlan;} public void setVerificationPlan(String v){verificationPlan=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
