package ai.nexusone.entity;

import ai.nexusone.enums.InsightStatus;
import ai.nexusone.enums.InsightType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "cognitive_insights",
        indexes = {
                @Index(name = "idx_cognitive_type", columnList = "insight_type"),
                @Index(name = "idx_cognitive_status", columnList = "status"),
                @Index(name = "idx_cognitive_memory", columnList = "memory_id"),
                @Index(name = "idx_cognitive_created", columnList = "created_at")
        }
)
public class CognitiveInsight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "insight_type", nullable = false)
    private InsightType insightType;

    @Lob
    @Column(name = "source_modules", nullable = false, columnDefinition = "TEXT")
    private String sourceModules;

    @Column(name = "memory_id")
    private Long memoryId;

    @Column(name = "knowledge_id")
    private Long knowledgeId;

    @Column(name = "incident_id")
    private Long incidentId;

    @Column(name = "decision_id")
    private Long decisionId;

    @Column(name = "execution_id")
    private Long executionId;

    @Column(name = "outcome_id")
    private Long outcomeId;

    @Column(name = "learning_id")
    private Long learningId;

    @Lob
    @Column(name = "business_context", nullable = false, columnDefinition = "TEXT")
    private String businessContext;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String observation;

    @Lob
    @Column(name = "correlation_summary", nullable = false, columnDefinition = "LONGTEXT")
    private String correlationSummary;

    @Lob
    @Column(name = "cognitive_reasoning", nullable = false, columnDefinition = "LONGTEXT")
    private String cognitiveReasoning;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String prediction;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String recommendation;

    @Column(name = "confidence_score", nullable = false)
    private Double confidenceScore;

    @Column(name = "impact_score", nullable = false)
    private Double impactScore;

    @Column(name = "intelligence_score", nullable = false)
    private Double intelligenceScore;

    @Column(name = "review_count", nullable = false)
    private Integer reviewCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InsightStatus status = InsightStatus.ACTIVE;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "reviewed_by")
    private String reviewedBy;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
        if (reviewCount == null) reviewCount = 0;
        if (status == null) status = InsightStatus.ACTIVE;
        if (intelligenceScore == null) intelligenceScore = 0.0;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public InsightType getInsightType() { return insightType; }
    public void setInsightType(InsightType insightType) { this.insightType = insightType; }
    public String getSourceModules() { return sourceModules; }
    public void setSourceModules(String sourceModules) { this.sourceModules = sourceModules; }
    public Long getMemoryId() { return memoryId; }
    public void setMemoryId(Long memoryId) { this.memoryId = memoryId; }
    public Long getKnowledgeId() { return knowledgeId; }
    public void setKnowledgeId(Long knowledgeId) { this.knowledgeId = knowledgeId; }
    public Long getIncidentId() { return incidentId; }
    public void setIncidentId(Long incidentId) { this.incidentId = incidentId; }
    public Long getDecisionId() { return decisionId; }
    public void setDecisionId(Long decisionId) { this.decisionId = decisionId; }
    public Long getExecutionId() { return executionId; }
    public void setExecutionId(Long executionId) { this.executionId = executionId; }
    public Long getOutcomeId() { return outcomeId; }
    public void setOutcomeId(Long outcomeId) { this.outcomeId = outcomeId; }
    public Long getLearningId() { return learningId; }
    public void setLearningId(Long learningId) { this.learningId = learningId; }
    public String getBusinessContext() { return businessContext; }
    public void setBusinessContext(String businessContext) { this.businessContext = businessContext; }
    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }
    public String getCorrelationSummary() { return correlationSummary; }
    public void setCorrelationSummary(String correlationSummary) { this.correlationSummary = correlationSummary; }
    public String getCognitiveReasoning() { return cognitiveReasoning; }
    public void setCognitiveReasoning(String cognitiveReasoning) { this.cognitiveReasoning = cognitiveReasoning; }
    public String getPrediction() { return prediction; }
    public void setPrediction(String prediction) { this.prediction = prediction; }
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
    public Double getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(Double confidenceScore) { this.confidenceScore = confidenceScore; }
    public Double getImpactScore() { return impactScore; }
    public void setImpactScore(Double impactScore) { this.impactScore = impactScore; }
    public Double getIntelligenceScore() { return intelligenceScore; }
    public void setIntelligenceScore(Double intelligenceScore) { this.intelligenceScore = intelligenceScore; }
    public Integer getReviewCount() { return reviewCount; }
    public void setReviewCount(Integer reviewCount) { this.reviewCount = reviewCount; }
    public InsightStatus getStatus() { return status; }
    public void setStatus(InsightStatus status) { this.status = status; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public String getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(String reviewedBy) { this.reviewedBy = reviewedBy; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime reviewedAt) { this.reviewedAt = reviewedAt; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public LocalDateTime getApprovedAt() { return approvedAt; }
    public void setApprovedAt(LocalDateTime approvedAt) { this.approvedAt = approvedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
