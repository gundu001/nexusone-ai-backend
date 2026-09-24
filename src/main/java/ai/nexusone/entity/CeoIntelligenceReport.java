package ai.nexusone.entity;

import ai.nexusone.enums.CeoIntelligenceStatus;
import ai.nexusone.enums.CeoPriority;
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
        name = "ceo_intelligence_reports",
        indexes = {
                @Index(name = "idx_ceo_status", columnList = "status"),
                @Index(name = "idx_ceo_priority", columnList = "priority"),
                @Index(name = "idx_ceo_created", columnList = "createdAt")
        }
)
public class CeoIntelligenceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 3000)
    private String executiveSummary;

    @Column(nullable = false, length = 3000)
    private String strategicPriority;

    @Column(nullable = false, length = 3000)
    private String ceoRecommendation;

    @Column(nullable = false)
    private Double enterpriseHealthScore;

    @Column(nullable = false)
    private Double transformationScore;

    @Column(nullable = false)
    private Double financialScore;

    @Column(nullable = false)
    private Double operationalScore;

    @Column(nullable = false)
    private Double riskExposure;

    @Column(nullable = false)
    private Double innovationScore;

    @Column(nullable = false)
    private Double overallCeoScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CeoPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CeoIntelligenceStatus status = CeoIntelligenceStatus.GENERATED;

    @Column(nullable = false)
    private String createdBy;

    private String reviewedBy;
    private LocalDateTime reviewedAt;
    private String decidedBy;
    private LocalDateTime decidedAt;
    private String executedBy;
    private LocalDateTime executedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
        if (status == null) {
            status = CeoIntelligenceStatus.GENERATED;
        }
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getExecutiveSummary() { return executiveSummary; }
    public void setExecutiveSummary(String executiveSummary) { this.executiveSummary = executiveSummary; }
    public String getStrategicPriority() { return strategicPriority; }
    public void setStrategicPriority(String strategicPriority) { this.strategicPriority = strategicPriority; }
    public String getCeoRecommendation() { return ceoRecommendation; }
    public void setCeoRecommendation(String ceoRecommendation) { this.ceoRecommendation = ceoRecommendation; }
    public Double getEnterpriseHealthScore() { return enterpriseHealthScore; }
    public void setEnterpriseHealthScore(Double enterpriseHealthScore) { this.enterpriseHealthScore = enterpriseHealthScore; }
    public Double getTransformationScore() { return transformationScore; }
    public void setTransformationScore(Double transformationScore) { this.transformationScore = transformationScore; }
    public Double getFinancialScore() { return financialScore; }
    public void setFinancialScore(Double financialScore) { this.financialScore = financialScore; }
    public Double getOperationalScore() { return operationalScore; }
    public void setOperationalScore(Double operationalScore) { this.operationalScore = operationalScore; }
    public Double getRiskExposure() { return riskExposure; }
    public void setRiskExposure(Double riskExposure) { this.riskExposure = riskExposure; }
    public Double getInnovationScore() { return innovationScore; }
    public void setInnovationScore(Double innovationScore) { this.innovationScore = innovationScore; }
    public Double getOverallCeoScore() { return overallCeoScore; }
    public void setOverallCeoScore(Double overallCeoScore) { this.overallCeoScore = overallCeoScore; }
    public CeoPriority getPriority() { return priority; }
    public void setPriority(CeoPriority priority) { this.priority = priority; }
    public CeoIntelligenceStatus getStatus() { return status; }
    public void setStatus(CeoIntelligenceStatus status) { this.status = status; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public String getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(String reviewedBy) { this.reviewedBy = reviewedBy; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime reviewedAt) { this.reviewedAt = reviewedAt; }
    public String getDecidedBy() { return decidedBy; }
    public void setDecidedBy(String decidedBy) { this.decidedBy = decidedBy; }
    public LocalDateTime getDecidedAt() { return decidedAt; }
    public void setDecidedAt(LocalDateTime decidedAt) { this.decidedAt = decidedAt; }
    public String getExecutedBy() { return executedBy; }
    public void setExecutedBy(String executedBy) { this.executedBy = executedBy; }
    public LocalDateTime getExecutedAt() { return executedAt; }
    public void setExecutedAt(LocalDateTime executedAt) { this.executedAt = executedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
