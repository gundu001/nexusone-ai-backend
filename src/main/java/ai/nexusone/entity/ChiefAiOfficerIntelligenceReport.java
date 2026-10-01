package ai.nexusone.entity;

import ai.nexusone.enums.ChiefAiOfficerPriority;
import ai.nexusone.enums.ChiefAiOfficerStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "chief_ai_officer_intelligence_reports",
        indexes = {
                @Index(name = "idx_caio_status", columnList = "status"),
                @Index(name = "idx_caio_priority", columnList = "priority"),
                @Index(name = "idx_caio_created", columnList = "created_at")
        }
)
public class ChiefAiOfficerIntelligenceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String aiStrategy;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String governanceOutlook;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String responsibleAiPlan;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String platformModernizationPlan;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String adoptionRoadmap;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String workforceTransformationPlan;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String dataReadinessAssessment;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String aiRiskAssessment;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String valueRealizationPlan;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String strategicRecommendations;

    @Column(nullable = false) private Double aiStrategyScore;
    @Column(nullable = false) private Double governanceScore;
    @Column(nullable = false) private Double responsibleAiScore;
    @Column(nullable = false) private Double platformMaturityScore;
    @Column(nullable = false) private Double adoptionScore;
    @Column(nullable = false) private Double workforceReadinessScore;
    @Column(nullable = false) private Double dataReadinessScore;
    @Column(nullable = false) private Double aiRiskManagementScore;
    @Column(nullable = false) private Double valueRealizationScore;
    @Column(nullable = false) private Double chiefAiOfficerIntelligenceScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChiefAiOfficerPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChiefAiOfficerStatus status = ChiefAiOfficerStatus.GENERATED;

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
        if (status == null) status = ChiefAiOfficerStatus.GENERATED;
    }

    @PreUpdate
    void update() { updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }
    public String getAiStrategy() { return aiStrategy; }
    public void setAiStrategy(String value) { aiStrategy = value; }
    public String getGovernanceOutlook() { return governanceOutlook; }
    public void setGovernanceOutlook(String value) { governanceOutlook = value; }
    public String getResponsibleAiPlan() { return responsibleAiPlan; }
    public void setResponsibleAiPlan(String value) { responsibleAiPlan = value; }
    public String getPlatformModernizationPlan() { return platformModernizationPlan; }
    public void setPlatformModernizationPlan(String value) { platformModernizationPlan = value; }
    public String getAdoptionRoadmap() { return adoptionRoadmap; }
    public void setAdoptionRoadmap(String value) { adoptionRoadmap = value; }
    public String getWorkforceTransformationPlan() { return workforceTransformationPlan; }
    public void setWorkforceTransformationPlan(String value) { workforceTransformationPlan = value; }
    public String getDataReadinessAssessment() { return dataReadinessAssessment; }
    public void setDataReadinessAssessment(String value) { dataReadinessAssessment = value; }
    public String getAiRiskAssessment() { return aiRiskAssessment; }
    public void setAiRiskAssessment(String value) { aiRiskAssessment = value; }
    public String getValueRealizationPlan() { return valueRealizationPlan; }
    public void setValueRealizationPlan(String value) { valueRealizationPlan = value; }
    public String getStrategicRecommendations() { return strategicRecommendations; }
    public void setStrategicRecommendations(String value) { strategicRecommendations = value; }
    public Double getAiStrategyScore() { return aiStrategyScore; }
    public void setAiStrategyScore(Double value) { aiStrategyScore = value; }
    public Double getGovernanceScore() { return governanceScore; }
    public void setGovernanceScore(Double value) { governanceScore = value; }
    public Double getResponsibleAiScore() { return responsibleAiScore; }
    public void setResponsibleAiScore(Double value) { responsibleAiScore = value; }
    public Double getPlatformMaturityScore() { return platformMaturityScore; }
    public void setPlatformMaturityScore(Double value) { platformMaturityScore = value; }
    public Double getAdoptionScore() { return adoptionScore; }
    public void setAdoptionScore(Double value) { adoptionScore = value; }
    public Double getWorkforceReadinessScore() { return workforceReadinessScore; }
    public void setWorkforceReadinessScore(Double value) { workforceReadinessScore = value; }
    public Double getDataReadinessScore() { return dataReadinessScore; }
    public void setDataReadinessScore(Double value) { dataReadinessScore = value; }
    public Double getAiRiskManagementScore() { return aiRiskManagementScore; }
    public void setAiRiskManagementScore(Double value) { aiRiskManagementScore = value; }
    public Double getValueRealizationScore() { return valueRealizationScore; }
    public void setValueRealizationScore(Double value) { valueRealizationScore = value; }
    public Double getChiefAiOfficerIntelligenceScore() { return chiefAiOfficerIntelligenceScore; }
    public void setChiefAiOfficerIntelligenceScore(Double value) { chiefAiOfficerIntelligenceScore = value; }
    public ChiefAiOfficerPriority getPriority() { return priority; }
    public void setPriority(ChiefAiOfficerPriority value) { priority = value; }
    public ChiefAiOfficerStatus getStatus() { return status; }
    public void setStatus(ChiefAiOfficerStatus value) { status = value; }
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
