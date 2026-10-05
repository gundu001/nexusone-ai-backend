package ai.nexusone.entity; import ai.nexusone.enums.*; import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="autonomous_enterprise_strategic_growth") public class AutonomousEnterpriseStrategicGrowth {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false,length=300) private String title;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String growthVision;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String growthStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String marketExpansionPlan;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String customerGrowthStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String productPortfolioGrowth;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String digitalGrowthTransformation;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String partnerEcosystemGrowth;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String growthRiskAssessment;
 private Double visionScore,growthStrategyScore,marketExpansionScore,customerGrowthScore,productGrowthScore,digitalGrowthScore,ecosystemGrowthScore,executionScore,strategicGrowthScore; @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseStrategicGrowthPriority priority; @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseStrategicGrowthStatus status;
 private String createdBy,reviewedBy,decidedBy,publishedBy; private LocalDateTime reviewedAt,decidedAt,publishedAt,createdAt,updatedAt;
 @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseStrategicGrowthStatus.GENERATED;} @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}  public String getGrowthVision(){return growthVision;} public void setGrowthVision(String v){growthVision=v;}
 public String getGrowthStrategy(){return growthStrategy;} public void setGrowthStrategy(String v){growthStrategy=v;}
 public String getMarketExpansionPlan(){return marketExpansionPlan;} public void setMarketExpansionPlan(String v){marketExpansionPlan=v;}
 public String getCustomerGrowthStrategy(){return customerGrowthStrategy;} public void setCustomerGrowthStrategy(String v){customerGrowthStrategy=v;}
 public String getProductPortfolioGrowth(){return productPortfolioGrowth;} public void setProductPortfolioGrowth(String v){productPortfolioGrowth=v;}
 public String getDigitalGrowthTransformation(){return digitalGrowthTransformation;} public void setDigitalGrowthTransformation(String v){digitalGrowthTransformation=v;}
 public String getPartnerEcosystemGrowth(){return partnerEcosystemGrowth;} public void setPartnerEcosystemGrowth(String v){partnerEcosystemGrowth=v;}
 public String getGrowthRiskAssessment(){return growthRiskAssessment;} public void setGrowthRiskAssessment(String v){growthRiskAssessment=v;}
 public Double getVisionScore(){return visionScore;} public void setVisionScore(Double v){visionScore=v;}
 public Double getGrowthStrategyScore(){return growthStrategyScore;} public void setGrowthStrategyScore(Double v){growthStrategyScore=v;}
 public Double getMarketExpansionScore(){return marketExpansionScore;} public void setMarketExpansionScore(Double v){marketExpansionScore=v;}
 public Double getCustomerGrowthScore(){return customerGrowthScore;} public void setCustomerGrowthScore(Double v){customerGrowthScore=v;}
 public Double getProductGrowthScore(){return productGrowthScore;} public void setProductGrowthScore(Double v){productGrowthScore=v;}
 public Double getDigitalGrowthScore(){return digitalGrowthScore;} public void setDigitalGrowthScore(Double v){digitalGrowthScore=v;}
 public Double getEcosystemGrowthScore(){return ecosystemGrowthScore;} public void setEcosystemGrowthScore(Double v){ecosystemGrowthScore=v;}
 public Double getExecutionScore(){return executionScore;} public void setExecutionScore(Double v){executionScore=v;}
 public Double getStrategicGrowthScore(){return strategicGrowthScore;} public void setStrategicGrowthScore(Double v){strategicGrowthScore=v;}
 public AutonomousEnterpriseStrategicGrowthPriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseStrategicGrowthPriority v){priority=v;} public AutonomousEnterpriseStrategicGrowthStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseStrategicGrowthStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;} public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;} public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;} public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;} public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;} public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
