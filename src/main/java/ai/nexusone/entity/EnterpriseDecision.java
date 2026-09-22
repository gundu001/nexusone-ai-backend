package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "enterprise_decision", indexes = {
        @Index(name = "idx_decision_created_at", columnList = "createdAt"),
        @Index(name = "idx_decision_status", columnList = "status"),
        @Index(name = "idx_decision_environment", columnList = "environment")
})
public class EnterpriseDecision {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 160) private String decisionName;
    @Column(nullable = false, length = 160) private String applicationName;
    @Column(nullable = false, length = 80) private String environment;
    @Column(nullable = false, length = 80) private String decisionType;
    @Column(nullable = false, length = 120) private String sourceAgent;
    @Column(nullable = false) private Double confidenceScore;
    @Column(nullable = false) private Integer riskScore;
    @Column(nullable = false) private Integer policyComplianceScore;
    @Column(nullable = false) private Boolean approvalRequired;
    @Column(nullable = false, length = 40) private String status;
    @Column(nullable = false, length = 80) private String recommendedDecision;
    @Column(nullable = false, length = 1500) private String rationale;
    @Column(nullable = false, length = 1500) private String actionPlan;
    @Column(length = 160) private String approvedBy;
    @Column(length = 1000) private String approvalComment;
    private LocalDateTime decidedAt;
    private LocalDateTime approvedAt;
    @Column(nullable = false, updatable = false) private LocalDateTime createdAt;
    @Column(nullable = false) private LocalDateTime updatedAt;

    @PrePersist void beforeInsert() { var now=LocalDateTime.now(); if(createdAt==null) createdAt=now; if(updatedAt==null) updatedAt=now; }
    @PreUpdate void beforeUpdate() { updatedAt=LocalDateTime.now(); }
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getDecisionName(){return decisionName;} public void setDecisionName(String v){decisionName=v;}
    public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
    public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
    public String getDecisionType(){return decisionType;} public void setDecisionType(String v){decisionType=v;}
    public String getSourceAgent(){return sourceAgent;} public void setSourceAgent(String v){sourceAgent=v;}
    public Double getConfidenceScore(){return confidenceScore;} public void setConfidenceScore(Double v){confidenceScore=v;}
    public Integer getRiskScore(){return riskScore;} public void setRiskScore(Integer v){riskScore=v;}
    public Integer getPolicyComplianceScore(){return policyComplianceScore;} public void setPolicyComplianceScore(Integer v){policyComplianceScore=v;}
    public Boolean getApprovalRequired(){return approvalRequired;} public void setApprovalRequired(Boolean v){approvalRequired=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getRecommendedDecision(){return recommendedDecision;} public void setRecommendedDecision(String v){recommendedDecision=v;}
    public String getRationale(){return rationale;} public void setRationale(String v){rationale=v;}
    public String getActionPlan(){return actionPlan;} public void setActionPlan(String v){actionPlan=v;}
    public String getApprovedBy(){return approvedBy;} public void setApprovedBy(String v){approvedBy=v;}
    public String getApprovalComment(){return approvalComment;} public void setApprovalComment(String v){approvalComment=v;}
    public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
    public LocalDateTime getApprovedAt(){return approvedAt;} public void setApprovedAt(LocalDateTime v){approvedAt=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){updatedAt=v;}
}
