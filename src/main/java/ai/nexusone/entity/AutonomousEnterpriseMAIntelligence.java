package ai.nexusone.entity;

import ai.nexusone.enums.AutonomousEnterpriseMAIntelligencePriority;
import ai.nexusone.enums.AutonomousEnterpriseMAIntelligenceStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "autonomous_enterprise_ma_intelligence")
public class AutonomousEnterpriseMAIntelligence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String title;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String maIntelligenceVision;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String acquisitionStrategy;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String mergerSynergyAnalysis;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String targetCompanyAssessment;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String financialDueDiligence;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String operationalDueDiligence;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String culturalIntegrationStrategy;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String executiveMADecision;
    @Column(nullable = false) private Double synergyScore;
    @Column(nullable = false) private Double financialStrengthScore;
    @Column(nullable = false) private Double strategicFitScore;
    @Column(nullable = false) private Double integrationReadinessScore;
    @Column(nullable = false) private Double riskAssessmentScore;
    @Column(nullable = false) private Double targetQualityScore;
    @Column(nullable = false) private Double valueCreationScore;
    @Column(nullable = false) private Double executionConfidenceScore;
    @Column(nullable = false) private Double enterpriseMAIntelligenceScore;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 30) private AutonomousEnterpriseMAIntelligencePriority priority;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 30) private AutonomousEnterpriseMAIntelligenceStatus status;
    @Column(nullable = false) private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist void onCreate() { createdAt = LocalDateTime.now(); updatedAt = createdAt; if (status == null) status = AutonomousEnterpriseMAIntelligenceStatus.GENERATED; }
    @PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); }
    public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getValuationIntelligenceVision(){return maIntelligenceVision;} public void setValuationIntelligenceVision(String v){maIntelligenceVision=v;}
    public String getDiscountedCashFlowStrategy(){return acquisitionStrategy;} public void setDiscountedCashFlowStrategy(String v){acquisitionStrategy=v;}
    public String getComparableCompanyAnalysis(){return mergerSynergyAnalysis;} public void setComparableCompanyAnalysis(String v){mergerSynergyAnalysis=v;}
    public String getMarketValuationStrategy(){return targetCompanyAssessment;} public void setMarketValuationStrategy(String v){targetCompanyAssessment=v;}
    public String getAssetValuationStrategy(){return financialDueDiligence;} public void setAssetValuationStrategy(String v){financialDueDiligence=v;}
    public String getIntangibleAssetValuationStrategy(){return operationalDueDiligence;} public void setIntangibleAssetValuationStrategy(String v){operationalDueDiligence=v;}
    public String getShareholderValueStrategy(){return culturalIntegrationStrategy;} public void setShareholderValueStrategy(String v){culturalIntegrationStrategy=v;}
    public String getExecutiveValuationDecision(){return executiveMADecision;} public void setExecutiveValuationDecision(String v){executiveMADecision=v;}
    public Double getEnterpriseValueScore(){return synergyScore;} public void setEnterpriseValueScore(Double v){synergyScore=v;}
    public Double getEquityValueScore(){return financialStrengthScore;} public void setEquityValueScore(Double v){financialStrengthScore=v;}
    public Double getCashFlowValueScore(){return strategicFitScore;} public void setCashFlowValueScore(Double v){strategicFitScore=v;}
    public Double getMarketPositionScore(){return integrationReadinessScore;} public void setMarketPositionScore(Double v){integrationReadinessScore=v;}
    public Double getAssetQualityScore(){return riskAssessmentScore;} public void setAssetQualityScore(Double v){riskAssessmentScore=v;}
    public Double getIntangibleValueScore(){return targetQualityScore;} public void setIntangibleValueScore(Double v){targetQualityScore=v;}
    public Double getGrowthValueScore(){return valueCreationScore;} public void setGrowthValueScore(Double v){valueCreationScore=v;}
    public Double getInvestorConfidenceScore(){return executionConfidenceScore;} public void setInvestorConfidenceScore(Double v){executionConfidenceScore=v;}
    public Double getEnterpriseValuationIntelligenceScore(){return enterpriseMAIntelligenceScore;} public void setEnterpriseValuationIntelligenceScore(Double v){enterpriseMAIntelligenceScore=v;}
    public AutonomousEnterpriseMAIntelligencePriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseMAIntelligencePriority v){priority=v;}
    public AutonomousEnterpriseMAIntelligenceStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseMAIntelligenceStatus v){status=v;}
    public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
