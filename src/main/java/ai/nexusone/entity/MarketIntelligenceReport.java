package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="market_intelligence_reports", indexes={
 @Index(name="idx_market_status",columnList="status"),
 @Index(name="idx_market_priority",columnList="priority"),
 @Index(name="idx_market_created",columnList="createdAt")})
public class MarketIntelligenceReport {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false,length=3000) private String marketLandscape;
 @Column(nullable=false,length=3000) private String competitorAnalysis;
 @Column(nullable=false,length=3000) private String differentiationStrategy;
 @Column(nullable=false,length=3000) private String opportunityAssessment;
 @Column(nullable=false,length=3000) private String strategicRecommendation;
 @Column(nullable=false) private Double marketAttractivenessScore;
 @Column(nullable=false) private Double competitivePositionScore;
 @Column(nullable=false) private Double differentiationScore;
 @Column(nullable=false) private Double innovationStrengthScore;
 @Column(nullable=false) private Double executionReadinessScore;
 @Column(nullable=false) private Double marketThreatExposure;
 @Column(nullable=false) private Double marketCompetitiveConfidenceScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private MarketPriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private MarketIntelligenceStatus status=MarketIntelligenceStatus.GENERATED;
 @Column(nullable=false) private String createdBy;
 private String reviewedBy, decidedBy, publishedBy;
 private LocalDateTime reviewedAt, decidedAt, publishedAt, createdAt, updatedAt;
 @PrePersist void create(){createdAt=updatedAt=LocalDateTime.now();if(status==null)status=MarketIntelligenceStatus.GENERATED;}
 @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getMarketLandscape(){return marketLandscape;} public void setMarketLandscape(String v){marketLandscape=v;}
 public String getCompetitorAnalysis(){return competitorAnalysis;} public void setCompetitorAnalysis(String v){competitorAnalysis=v;}
 public String getDifferentiationStrategy(){return differentiationStrategy;} public void setDifferentiationStrategy(String v){differentiationStrategy=v;}
 public String getOpportunityAssessment(){return opportunityAssessment;} public void setOpportunityAssessment(String v){opportunityAssessment=v;}
 public String getStrategicRecommendation(){return strategicRecommendation;} public void setStrategicRecommendation(String v){strategicRecommendation=v;}
 public Double getMarketAttractivenessScore(){return marketAttractivenessScore;} public void setMarketAttractivenessScore(Double v){marketAttractivenessScore=v;}
 public Double getCompetitivePositionScore(){return competitivePositionScore;} public void setCompetitivePositionScore(Double v){competitivePositionScore=v;}
 public Double getDifferentiationScore(){return differentiationScore;} public void setDifferentiationScore(Double v){differentiationScore=v;}
 public Double getInnovationStrengthScore(){return innovationStrengthScore;} public void setInnovationStrengthScore(Double v){innovationStrengthScore=v;}
 public Double getExecutionReadinessScore(){return executionReadinessScore;} public void setExecutionReadinessScore(Double v){executionReadinessScore=v;}
 public Double getMarketThreatExposure(){return marketThreatExposure;} public void setMarketThreatExposure(Double v){marketThreatExposure=v;}
 public Double getMarketCompetitiveConfidenceScore(){return marketCompetitiveConfidenceScore;} public void setMarketCompetitiveConfidenceScore(Double v){marketCompetitiveConfidenceScore=v;}
 public MarketPriority getPriority(){return priority;} public void setPriority(MarketPriority v){priority=v;}
 public MarketIntelligenceStatus getStatus(){return status;} public void setStatus(MarketIntelligenceStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
 public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
