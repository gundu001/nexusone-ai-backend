package ai.nexusone.entity;

import ai.nexusone.enums.AutonomousEnterpriseValuationIntelligencePriority;
import ai.nexusone.enums.AutonomousEnterpriseValuationIntelligenceStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "autonomous_enterprise_valuation_intelligence")
public class AutonomousEnterpriseValuationIntelligence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String title;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String valuationIntelligenceVision;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String discountedCashFlowStrategy;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String comparableCompanyAnalysis;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String marketValuationStrategy;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String assetValuationStrategy;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String intangibleAssetValuationStrategy;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String shareholderValueStrategy;
    @Lob @Column(columnDefinition = "LONGTEXT", nullable = false) private String executiveValuationDecision;
    @Column(nullable = false) private Double enterpriseValueScore;
    @Column(nullable = false) private Double equityValueScore;
    @Column(nullable = false) private Double cashFlowValueScore;
    @Column(nullable = false) private Double marketPositionScore;
    @Column(nullable = false) private Double assetQualityScore;
    @Column(nullable = false) private Double intangibleValueScore;
    @Column(nullable = false) private Double growthValueScore;
    @Column(nullable = false) private Double investorConfidenceScore;
    @Column(nullable = false) private Double enterpriseValuationIntelligenceScore;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 30) private AutonomousEnterpriseValuationIntelligencePriority priority;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 30) private AutonomousEnterpriseValuationIntelligenceStatus status;
    @Column(nullable = false) private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist void onCreate() { createdAt = LocalDateTime.now(); updatedAt = createdAt; if (status == null) status = AutonomousEnterpriseValuationIntelligenceStatus.GENERATED; }
    @PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); }
    public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getValuationIntelligenceVision(){return valuationIntelligenceVision;} public void setValuationIntelligenceVision(String v){valuationIntelligenceVision=v;}
    public String getDiscountedCashFlowStrategy(){return discountedCashFlowStrategy;} public void setDiscountedCashFlowStrategy(String v){discountedCashFlowStrategy=v;}
    public String getComparableCompanyAnalysis(){return comparableCompanyAnalysis;} public void setComparableCompanyAnalysis(String v){comparableCompanyAnalysis=v;}
    public String getMarketValuationStrategy(){return marketValuationStrategy;} public void setMarketValuationStrategy(String v){marketValuationStrategy=v;}
    public String getAssetValuationStrategy(){return assetValuationStrategy;} public void setAssetValuationStrategy(String v){assetValuationStrategy=v;}
    public String getIntangibleAssetValuationStrategy(){return intangibleAssetValuationStrategy;} public void setIntangibleAssetValuationStrategy(String v){intangibleAssetValuationStrategy=v;}
    public String getShareholderValueStrategy(){return shareholderValueStrategy;} public void setShareholderValueStrategy(String v){shareholderValueStrategy=v;}
    public String getExecutiveValuationDecision(){return executiveValuationDecision;} public void setExecutiveValuationDecision(String v){executiveValuationDecision=v;}
    public Double getEnterpriseValueScore(){return enterpriseValueScore;} public void setEnterpriseValueScore(Double v){enterpriseValueScore=v;}
    public Double getEquityValueScore(){return equityValueScore;} public void setEquityValueScore(Double v){equityValueScore=v;}
    public Double getCashFlowValueScore(){return cashFlowValueScore;} public void setCashFlowValueScore(Double v){cashFlowValueScore=v;}
    public Double getMarketPositionScore(){return marketPositionScore;} public void setMarketPositionScore(Double v){marketPositionScore=v;}
    public Double getAssetQualityScore(){return assetQualityScore;} public void setAssetQualityScore(Double v){assetQualityScore=v;}
    public Double getIntangibleValueScore(){return intangibleValueScore;} public void setIntangibleValueScore(Double v){intangibleValueScore=v;}
    public Double getGrowthValueScore(){return growthValueScore;} public void setGrowthValueScore(Double v){growthValueScore=v;}
    public Double getInvestorConfidenceScore(){return investorConfidenceScore;} public void setInvestorConfidenceScore(Double v){investorConfidenceScore=v;}
    public Double getEnterpriseValuationIntelligenceScore(){return enterpriseValuationIntelligenceScore;} public void setEnterpriseValuationIntelligenceScore(Double v){enterpriseValuationIntelligenceScore=v;}
    public AutonomousEnterpriseValuationIntelligencePriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseValuationIntelligencePriority v){priority=v;}
    public AutonomousEnterpriseValuationIntelligenceStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseValuationIntelligenceStatus v){status=v;}
    public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
