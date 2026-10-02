package ai.nexusone.entity;

import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "autonomous_enterprise_consciousness_reports", indexes = {
        @Index(name = "idx_aec_status", columnList = "status"),
        @Index(name = "idx_aec_priority", columnList = "priority"),
        @Index(name = "idx_aec_created", columnList = "created_at")
})
public class AutonomousEnterpriseConsciousnessReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String enterpriseAwareness;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contextUnderstanding;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String decisionMemory;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String reasoningIntelligence;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String adaptiveLearning;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String predictiveAwareness;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String selfOptimization;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String goalAlignment;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String strategicConsciousness;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String consciousnessRecommendations;
    @Column(nullable = false)
    private Double enterpriseAwarenessScore;
    @Column(nullable = false)
    private Double contextUnderstandingScore;
    @Column(nullable = false)
    private Double decisionMemoryScore;
    @Column(nullable = false)
    private Double reasoningIntelligenceScore;
    @Column(nullable = false)
    private Double adaptiveLearningScore;
    @Column(nullable = false)
    private Double predictiveAwarenessScore;
    @Column(nullable = false)
    private Double selfOptimizationScore;
    @Column(nullable = false)
    private Double goalAlignmentScore;
    @Column(nullable = false)
    private Double strategicConsciousnessScore;
    @Column(nullable = false)
    private Double autonomousEnterpriseConsciousnessScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AutonomousEnterpriseConsciousnessPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AutonomousEnterpriseConsciousnessStatus status = AutonomousEnterpriseConsciousnessStatus.GENERATED;

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

    @PrePersist
    void create() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        if (status == null) status = AutonomousEnterpriseConsciousnessStatus.GENERATED;
    }

    @PreUpdate
    void update() { updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }
    public String getEnterpriseAwareness() { return enterpriseAwareness; }
    public void setEnterpriseAwareness(String value) { this.enterpriseAwareness = value; }
    public Double getEnterpriseAwarenessScore() { return enterpriseAwarenessScore; }
    public void setEnterpriseAwarenessScore(Double value) { this.enterpriseAwarenessScore = value; }
    public String getContextUnderstanding() { return contextUnderstanding; }
    public void setContextUnderstanding(String value) { this.contextUnderstanding = value; }
    public Double getContextUnderstandingScore() { return contextUnderstandingScore; }
    public void setContextUnderstandingScore(Double value) { this.contextUnderstandingScore = value; }
    public String getDecisionMemory() { return decisionMemory; }
    public void setDecisionMemory(String value) { this.decisionMemory = value; }
    public Double getDecisionMemoryScore() { return decisionMemoryScore; }
    public void setDecisionMemoryScore(Double value) { this.decisionMemoryScore = value; }
    public String getReasoningIntelligence() { return reasoningIntelligence; }
    public void setReasoningIntelligence(String value) { this.reasoningIntelligence = value; }
    public Double getReasoningIntelligenceScore() { return reasoningIntelligenceScore; }
    public void setReasoningIntelligenceScore(Double value) { this.reasoningIntelligenceScore = value; }
    public String getAdaptiveLearning() { return adaptiveLearning; }
    public void setAdaptiveLearning(String value) { this.adaptiveLearning = value; }
    public Double getAdaptiveLearningScore() { return adaptiveLearningScore; }
    public void setAdaptiveLearningScore(Double value) { this.adaptiveLearningScore = value; }
    public String getPredictiveAwareness() { return predictiveAwareness; }
    public void setPredictiveAwareness(String value) { this.predictiveAwareness = value; }
    public Double getPredictiveAwarenessScore() { return predictiveAwarenessScore; }
    public void setPredictiveAwarenessScore(Double value) { this.predictiveAwarenessScore = value; }
    public String getSelfOptimization() { return selfOptimization; }
    public void setSelfOptimization(String value) { this.selfOptimization = value; }
    public Double getSelfOptimizationScore() { return selfOptimizationScore; }
    public void setSelfOptimizationScore(Double value) { this.selfOptimizationScore = value; }
    public String getGoalAlignment() { return goalAlignment; }
    public void setGoalAlignment(String value) { this.goalAlignment = value; }
    public Double getGoalAlignmentScore() { return goalAlignmentScore; }
    public void setGoalAlignmentScore(Double value) { this.goalAlignmentScore = value; }
    public String getStrategicConsciousness() { return strategicConsciousness; }
    public void setStrategicConsciousness(String value) { this.strategicConsciousness = value; }
    public Double getStrategicConsciousnessScore() { return strategicConsciousnessScore; }
    public void setStrategicConsciousnessScore(Double value) { this.strategicConsciousnessScore = value; }
    public String getConsciousnessRecommendations() { return consciousnessRecommendations; }
    public void setConsciousnessRecommendations(String value) { this.consciousnessRecommendations = value; }
    public Double getAutonomousEnterpriseConsciousnessScore() { return autonomousEnterpriseConsciousnessScore; }
    public void setAutonomousEnterpriseConsciousnessScore(Double value) { this.autonomousEnterpriseConsciousnessScore = value; }
    public AutonomousEnterpriseConsciousnessPriority getPriority() { return priority; }
    public void setPriority(AutonomousEnterpriseConsciousnessPriority value) { this.priority = value; }
    public AutonomousEnterpriseConsciousnessStatus getStatus() { return status; }
    public void setStatus(AutonomousEnterpriseConsciousnessStatus value) { this.status = value; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String value) { this.createdBy = value; }
    public String getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(String value) { this.reviewedBy = value; }
    public String getDecidedBy() { return decidedBy; }
    public void setDecidedBy(String value) { this.decidedBy = value; }
    public String getPublishedBy() { return publishedBy; }
    public void setPublishedBy(String value) { this.publishedBy = value; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime value) { this.reviewedAt = value; }
    public LocalDateTime getDecidedAt() { return decidedAt; }
    public void setDecidedAt(LocalDateTime value) { this.decidedAt = value; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime value) { this.publishedAt = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
