package ai.nexusone.entity;

import ai.nexusone.enums.EnterpriseRiskPriority;
import ai.nexusone.enums.EnterpriseRiskStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "enterprise_risk_intelligence_reports", indexes = {
        @Index(name = "idx_enterprise_risk_status", columnList = "status"),
        @Index(name = "idx_enterprise_risk_priority", columnList = "priority"),
        @Index(name = "idx_enterprise_risk_created", columnList = "created_at")
})
public class EnterpriseRiskIntelligenceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String enterpriseRiskOutlook;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String operationalRiskAnalysis;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String financialRiskAnalysis;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String cyberSecurityRiskAnalysis;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String regulatoryComplianceRiskAnalysis;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String strategicRiskAnalysis;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String thirdPartyRiskAnalysis;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String businessContinuityAnalysis;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String riskMitigationRecommendations;

    @Column(nullable = false)
    private Double operationalRiskScore;
    @Column(nullable = false)
    private Double financialRiskScore;
    @Column(nullable = false)
    private Double cyberSecurityRiskScore;
    @Column(nullable = false)
    private Double regulatoryComplianceRiskScore;
    @Column(nullable = false)
    private Double strategicRiskScore;
    @Column(nullable = false)
    private Double thirdPartyRiskScore;
    @Column(nullable = false)
    private Double businessContinuityRiskScore;
    @Column(nullable = false)
    private Double enterpriseRiskIntelligenceScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnterpriseRiskPriority priority;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnterpriseRiskStatus status = EnterpriseRiskStatus.GENERATED;

    @Column(nullable = false)
    private String createdBy;
    private String reviewedBy;
    private String decidedBy;
    private String publishedBy;
    private LocalDateTime reviewedAt;
    private LocalDateTime decidedAt;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void create() {
        createdAt = updatedAt = LocalDateTime.now();
        if (status == null) status = EnterpriseRiskStatus.GENERATED;
    }

    @PreUpdate
    void update() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }
    public String getEnterpriseRiskOutlook() { return enterpriseRiskOutlook; }
    public void setEnterpriseRiskOutlook(String value) { enterpriseRiskOutlook = value; }
    public String getOperationalRiskAnalysis() { return operationalRiskAnalysis; }
    public void setOperationalRiskAnalysis(String value) { operationalRiskAnalysis = value; }
    public String getFinancialRiskAnalysis() { return financialRiskAnalysis; }
    public void setFinancialRiskAnalysis(String value) { financialRiskAnalysis = value; }
    public String getCyberSecurityRiskAnalysis() { return cyberSecurityRiskAnalysis; }
    public void setCyberSecurityRiskAnalysis(String value) { cyberSecurityRiskAnalysis = value; }
    public String getRegulatoryComplianceRiskAnalysis() { return regulatoryComplianceRiskAnalysis; }
    public void setRegulatoryComplianceRiskAnalysis(String value) { regulatoryComplianceRiskAnalysis = value; }
    public String getStrategicRiskAnalysis() { return strategicRiskAnalysis; }
    public void setStrategicRiskAnalysis(String value) { strategicRiskAnalysis = value; }
    public String getThirdPartyRiskAnalysis() { return thirdPartyRiskAnalysis; }
    public void setThirdPartyRiskAnalysis(String value) { thirdPartyRiskAnalysis = value; }
    public String getBusinessContinuityAnalysis() { return businessContinuityAnalysis; }
    public void setBusinessContinuityAnalysis(String value) { businessContinuityAnalysis = value; }
    public String getRiskMitigationRecommendations() { return riskMitigationRecommendations; }
    public void setRiskMitigationRecommendations(String value) { riskMitigationRecommendations = value; }
    public Double getOperationalRiskScore() { return operationalRiskScore; }
    public void setOperationalRiskScore(Double value) { operationalRiskScore = value; }
    public Double getFinancialRiskScore() { return financialRiskScore; }
    public void setFinancialRiskScore(Double value) { financialRiskScore = value; }
    public Double getCyberSecurityRiskScore() { return cyberSecurityRiskScore; }
    public void setCyberSecurityRiskScore(Double value) { cyberSecurityRiskScore = value; }
    public Double getRegulatoryComplianceRiskScore() { return regulatoryComplianceRiskScore; }
    public void setRegulatoryComplianceRiskScore(Double value) { regulatoryComplianceRiskScore = value; }
    public Double getStrategicRiskScore() { return strategicRiskScore; }
    public void setStrategicRiskScore(Double value) { strategicRiskScore = value; }
    public Double getThirdPartyRiskScore() { return thirdPartyRiskScore; }
    public void setThirdPartyRiskScore(Double value) { thirdPartyRiskScore = value; }
    public Double getBusinessContinuityRiskScore() { return businessContinuityRiskScore; }
    public void setBusinessContinuityRiskScore(Double value) { businessContinuityRiskScore = value; }
    public Double getEnterpriseRiskIntelligenceScore() { return enterpriseRiskIntelligenceScore; }
    public void setEnterpriseRiskIntelligenceScore(Double value) { enterpriseRiskIntelligenceScore = value; }
    public EnterpriseRiskPriority getPriority() { return priority; }
    public void setPriority(EnterpriseRiskPriority value) { priority = value; }
    public EnterpriseRiskStatus getStatus() { return status; }
    public void setStatus(EnterpriseRiskStatus value) { status = value; }
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
