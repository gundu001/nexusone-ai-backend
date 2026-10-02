package ai.nexusone.entity;

import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "autonomous_enterprise_agi_coordination_reports", indexes = {
        @Index(name = "idx_agi_coord_status", columnList = "status"),
        @Index(name = "idx_agi_coord_priority", columnList = "priority"),
        @Index(name = "idx_agi_coord_created", columnList = "created_at")
})
public class AutonomousEnterpriseAgiCoordinationReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String agentCoordination;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String goalOrchestration;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String taskDelegation;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String sharedContext;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String reasoningAlignment;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String conflictResolution;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String humanOversight;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String safetyGovernance;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String outcomeSynchronization;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String coordinationRecommendations;
    @Column(nullable = false)
    private Double agentCoordinationScore;
    @Column(nullable = false)
    private Double goalOrchestrationScore;
    @Column(nullable = false)
    private Double taskDelegationScore;
    @Column(nullable = false)
    private Double sharedContextScore;
    @Column(nullable = false)
    private Double reasoningAlignmentScore;
    @Column(nullable = false)
    private Double conflictResolutionScore;
    @Column(nullable = false)
    private Double humanOversightScore;
    @Column(nullable = false)
    private Double safetyGovernanceScore;
    @Column(nullable = false)
    private Double outcomeSynchronizationScore;
    @Column(nullable = false)
    private Double autonomousEnterpriseAgiCoordinationScore;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private AutonomousEnterpriseAgiCoordinationPriority priority;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private AutonomousEnterpriseAgiCoordinationStatus status = AutonomousEnterpriseAgiCoordinationStatus.GENERATED;
    @Column(nullable = false)
    private String createdBy;
    private String reviewedBy;
    private String decidedBy;
    private String publishedBy;
    private LocalDateTime reviewedAt;
    private LocalDateTime decidedAt;
    private LocalDateTime publishedAt;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist void create() {
        createdAt = LocalDateTime.now(); updatedAt = createdAt;
        if (status == null) status = AutonomousEnterpriseAgiCoordinationStatus.GENERATED;
    }
    @PreUpdate void update() { updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }
    public String getAgentCoordination() { return agentCoordination; }
    public void setAgentCoordination(String value) { this.agentCoordination = value; }
    public Double getAgentCoordinationScore() { return agentCoordinationScore; }
    public void setAgentCoordinationScore(Double value) { this.agentCoordinationScore = value; }
    public String getGoalOrchestration() { return goalOrchestration; }
    public void setGoalOrchestration(String value) { this.goalOrchestration = value; }
    public Double getGoalOrchestrationScore() { return goalOrchestrationScore; }
    public void setGoalOrchestrationScore(Double value) { this.goalOrchestrationScore = value; }
    public String getTaskDelegation() { return taskDelegation; }
    public void setTaskDelegation(String value) { this.taskDelegation = value; }
    public Double getTaskDelegationScore() { return taskDelegationScore; }
    public void setTaskDelegationScore(Double value) { this.taskDelegationScore = value; }
    public String getSharedContext() { return sharedContext; }
    public void setSharedContext(String value) { this.sharedContext = value; }
    public Double getSharedContextScore() { return sharedContextScore; }
    public void setSharedContextScore(Double value) { this.sharedContextScore = value; }
    public String getReasoningAlignment() { return reasoningAlignment; }
    public void setReasoningAlignment(String value) { this.reasoningAlignment = value; }
    public Double getReasoningAlignmentScore() { return reasoningAlignmentScore; }
    public void setReasoningAlignmentScore(Double value) { this.reasoningAlignmentScore = value; }
    public String getConflictResolution() { return conflictResolution; }
    public void setConflictResolution(String value) { this.conflictResolution = value; }
    public Double getConflictResolutionScore() { return conflictResolutionScore; }
    public void setConflictResolutionScore(Double value) { this.conflictResolutionScore = value; }
    public String getHumanOversight() { return humanOversight; }
    public void setHumanOversight(String value) { this.humanOversight = value; }
    public Double getHumanOversightScore() { return humanOversightScore; }
    public void setHumanOversightScore(Double value) { this.humanOversightScore = value; }
    public String getSafetyGovernance() { return safetyGovernance; }
    public void setSafetyGovernance(String value) { this.safetyGovernance = value; }
    public Double getSafetyGovernanceScore() { return safetyGovernanceScore; }
    public void setSafetyGovernanceScore(Double value) { this.safetyGovernanceScore = value; }
    public String getOutcomeSynchronization() { return outcomeSynchronization; }
    public void setOutcomeSynchronization(String value) { this.outcomeSynchronization = value; }
    public Double getOutcomeSynchronizationScore() { return outcomeSynchronizationScore; }
    public void setOutcomeSynchronizationScore(Double value) { this.outcomeSynchronizationScore = value; }
    public String getCoordinationRecommendations() { return coordinationRecommendations; }
    public void setCoordinationRecommendations(String value) { coordinationRecommendations = value; }
    public Double getAutonomousEnterpriseAgiCoordinationScore() { return autonomousEnterpriseAgiCoordinationScore; }
    public void setAutonomousEnterpriseAgiCoordinationScore(Double value) { autonomousEnterpriseAgiCoordinationScore = value; }
    public AutonomousEnterpriseAgiCoordinationPriority getPriority() { return priority; }
    public void setPriority(AutonomousEnterpriseAgiCoordinationPriority value) { priority = value; }
    public AutonomousEnterpriseAgiCoordinationStatus getStatus() { return status; }
    public void setStatus(AutonomousEnterpriseAgiCoordinationStatus value) { status = value; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String value) { createdBy = value; }
    public String getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(String value) { reviewedBy = value; }
    public String getDecidedBy() { return decidedBy; }
    public void setDecidedBy(String value) { decidedBy = value; }
    public String getPublishedBy() { return publishedBy; }
    public void setPublishedBy(String value) { publishedBy = value; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime value) { reviewedAt = value; }
    public LocalDateTime getDecidedAt() { return decidedAt; }
    public void setDecidedAt(LocalDateTime value) { decidedAt = value; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime value) { publishedAt = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
