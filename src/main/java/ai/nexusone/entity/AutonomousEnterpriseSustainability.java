package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="autonomous_enterprise_sustainability")
public class AutonomousEnterpriseSustainability {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=300) private String title;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String sustainabilityVision;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String esgStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String carbonReductionPlan;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String energyEfficiencyProgram;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String circularEconomyStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String sustainableSupplyChain;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String climateRiskAssessment;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String regulatoryCompliancePlan;
 private Double visionScore,esgScore,carbonReductionScore,energyEfficiencyScore,circularEconomyScore,supplyChainScore,climateRiskScore,complianceScore,sustainabilityScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseSustainabilityPriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseSustainabilityStatus status;
 private String createdBy,reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt,createdAt,updatedAt;
 @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseSustainabilityStatus.GENERATED;}
 @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getSustainabilityVision(){return sustainabilityVision;} public void setSustainabilityVision(String v){sustainabilityVision=v;}
 public String getEsgStrategy(){return esgStrategy;} public void setEsgStrategy(String v){esgStrategy=v;}
 public String getCarbonReductionPlan(){return carbonReductionPlan;} public void setCarbonReductionPlan(String v){carbonReductionPlan=v;}
 public String getEnergyEfficiencyProgram(){return energyEfficiencyProgram;} public void setEnergyEfficiencyProgram(String v){energyEfficiencyProgram=v;}
 public String getCircularEconomyStrategy(){return circularEconomyStrategy;} public void setCircularEconomyStrategy(String v){circularEconomyStrategy=v;}
 public String getSustainableSupplyChain(){return sustainableSupplyChain;} public void setSustainableSupplyChain(String v){sustainableSupplyChain=v;}
 public String getClimateRiskAssessment(){return climateRiskAssessment;} public void setClimateRiskAssessment(String v){climateRiskAssessment=v;}
 public String getRegulatoryCompliancePlan(){return regulatoryCompliancePlan;} public void setRegulatoryCompliancePlan(String v){regulatoryCompliancePlan=v;}
 public Double getVisionScore(){return visionScore;} public void setVisionScore(Double v){visionScore=v;}
 public Double getEsgScore(){return esgScore;} public void setEsgScore(Double v){esgScore=v;}
 public Double getCarbonReductionScore(){return carbonReductionScore;} public void setCarbonReductionScore(Double v){carbonReductionScore=v;}
 public Double getEnergyEfficiencyScore(){return energyEfficiencyScore;} public void setEnergyEfficiencyScore(Double v){energyEfficiencyScore=v;}
 public Double getCircularEconomyScore(){return circularEconomyScore;} public void setCircularEconomyScore(Double v){circularEconomyScore=v;}
 public Double getSupplyChainScore(){return supplyChainScore;} public void setSupplyChainScore(Double v){supplyChainScore=v;}
 public Double getClimateRiskScore(){return climateRiskScore;} public void setClimateRiskScore(Double v){climateRiskScore=v;}
 public Double getComplianceScore(){return complianceScore;} public void setComplianceScore(Double v){complianceScore=v;}
 public Double getSustainabilityScore(){return sustainabilityScore;} public void setSustainabilityScore(Double v){sustainabilityScore=v;}
 public AutonomousEnterpriseSustainabilityPriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseSustainabilityPriority v){priority=v;}
 public AutonomousEnterpriseSustainabilityStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseSustainabilityStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
 public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
