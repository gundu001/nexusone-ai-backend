package ai.nexusone.entity;

import ai.nexusone.enums.ExecutiveAdvisoryStatus;
import ai.nexusone.enums.ExecutivePriority;
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
@Table(name = "executive_advisories", indexes = {
        @Index(name = "idx_advisory_status", columnList = "status"),
        @Index(name = "idx_advisory_priority", columnList = "priority"),
        @Index(name = "idx_advisory_created", columnList = "createdAt")
})
public class ExecutiveAdvisory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 3000)
    private String strategicObjective;

    @Column(nullable = false, length = 3000)
    private String businessChallenge;

    @Column(nullable = false, length = 3000)
    private String executiveRecommendation;

    @Column(nullable = false)
    private Double investmentValue;

    @Column(nullable = false)
    private Double strategicAlignment;

    @Column(nullable = false)
    private Double riskScore;

    @Column(nullable = false)
    private Double advisoryScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExecutivePriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExecutiveAdvisoryStatus status = ExecutiveAdvisoryStatus.GENERATED;

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
        if (status == null) status = ExecutiveAdvisoryStatus.GENERATED;
    }

    @PreUpdate
    void preUpdate() { updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }
    public String getStrategicObjective() { return strategicObjective; }
    public void setStrategicObjective(String value) { strategicObjective = value; }
    public String getBusinessChallenge() { return businessChallenge; }
    public void setBusinessChallenge(String value) { businessChallenge = value; }
    public String getExecutiveRecommendation() { return executiveRecommendation; }
    public void setExecutiveRecommendation(String value) { executiveRecommendation = value; }
    public Double getInvestmentValue() { return investmentValue; }
    public void setInvestmentValue(Double value) { investmentValue = value; }
    public Double getStrategicAlignment() { return strategicAlignment; }
    public void setStrategicAlignment(Double value) { strategicAlignment = value; }
    public Double getRiskScore() { return riskScore; }
    public void setRiskScore(Double value) { riskScore = value; }
    public Double getAdvisoryScore() { return advisoryScore; }
    public void setAdvisoryScore(Double value) { advisoryScore = value; }
    public ExecutivePriority getPriority() { return priority; }
    public void setPriority(ExecutivePriority value) { priority = value; }
    public ExecutiveAdvisoryStatus getStatus() { return status; }
    public void setStatus(ExecutiveAdvisoryStatus value) { status = value; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String value) { createdBy = value; }
    public String getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(String value) { reviewedBy = value; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime value) { reviewedAt = value; }
    public String getDecidedBy() { return decidedBy; }
    public void setDecidedBy(String value) { decidedBy = value; }
    public LocalDateTime getDecidedAt() { return decidedAt; }
    public void setDecidedAt(LocalDateTime value) { decidedAt = value; }
    public String getExecutedBy() { return executedBy; }
    public void setExecutedBy(String value) { executedBy = value; }
    public LocalDateTime getExecutedAt() { return executedAt; }
    public void setExecutedAt(LocalDateTime value) { executedAt = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
