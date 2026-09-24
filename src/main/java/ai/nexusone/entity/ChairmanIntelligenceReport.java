package ai.nexusone.entity;
import ai.nexusone.enums.*; import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="chairman_intelligence_reports",indexes={
 @Index(name="idx_chairman_status",columnList="status"),
 @Index(name="idx_chairman_priority",columnList="priority"),
 @Index(name="idx_chairman_created",columnList="createdAt")})
public class ChairmanIntelligenceReport {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false,length=3000) private String executiveSummary;
 @Column(nullable=false,length=3000) private String chairmanAssessment;
 @Column(nullable=false,length=3000) private String longTermStrategy;
 @Column(nullable=false,length=3000) private String investmentRecommendation;
 @Column(nullable=false,length=3000) private String shareholderImpact;
 @Column(nullable=false) private Double revenueGrowthScore,profitabilityScore,innovationScore,transformationScore,governanceScore,riskExposure,chairmanScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ChairmanPriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ChairmanIntelligenceStatus status=ChairmanIntelligenceStatus.GENERATED;
 @Column(nullable=false) private String createdBy;
 private String reviewedBy,decidedBy,executedBy; private LocalDateTime reviewedAt,decidedAt,executedAt,createdAt,updatedAt;
 @PrePersist void create(){createdAt=updatedAt=LocalDateTime.now();if(status==null)status=ChairmanIntelligenceStatus.GENERATED;}
 @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getExecutiveSummary(){return executiveSummary;} public void setExecutiveSummary(String v){executiveSummary=v;}
 public String getChairmanAssessment(){return chairmanAssessment;} public void setChairmanAssessment(String v){chairmanAssessment=v;}
 public String getLongTermStrategy(){return longTermStrategy;} public void setLongTermStrategy(String v){longTermStrategy=v;}
 public String getInvestmentRecommendation(){return investmentRecommendation;} public void setInvestmentRecommendation(String v){investmentRecommendation=v;}
 public String getShareholderImpact(){return shareholderImpact;} public void setShareholderImpact(String v){shareholderImpact=v;}
 public Double getRevenueGrowthScore(){return revenueGrowthScore;} public void setRevenueGrowthScore(Double v){revenueGrowthScore=v;}
 public Double getProfitabilityScore(){return profitabilityScore;} public void setProfitabilityScore(Double v){profitabilityScore=v;}
 public Double getInnovationScore(){return innovationScore;} public void setInnovationScore(Double v){innovationScore=v;}
 public Double getTransformationScore(){return transformationScore;} public void setTransformationScore(Double v){transformationScore=v;}
 public Double getGovernanceScore(){return governanceScore;} public void setGovernanceScore(Double v){governanceScore=v;}
 public Double getRiskExposure(){return riskExposure;} public void setRiskExposure(Double v){riskExposure=v;}
 public Double getChairmanScore(){return chairmanScore;} public void setChairmanScore(Double v){chairmanScore=v;}
 public ChairmanPriority getPriority(){return priority;} public void setPriority(ChairmanPriority v){priority=v;}
 public ChairmanIntelligenceStatus getStatus(){return status;} public void setStatus(ChairmanIntelligenceStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;} public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;} public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public String getExecutedBy(){return executedBy;} public void setExecutedBy(String v){executedBy=v;} public LocalDateTime getExecutedAt(){return executedAt;} public void setExecutedAt(LocalDateTime v){executedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
