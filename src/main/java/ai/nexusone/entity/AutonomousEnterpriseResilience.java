package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="autonomous_enterprise_resilience")
public class AutonomousEnterpriseResilience {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=300) private String title;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String resilienceVision;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String businessContinuityPlan;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String disasterRecoveryStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String cyberResilienceFramework;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String infrastructureResilienceModel;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String workforceResilienceProgram;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String thirdPartyResilienceAssessment;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String supplyChainResilienceStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String operationalResilienceBlueprint;
 private Double visionScore,continuityScore,recoveryScore,cyberScore,infrastructureScore,workforceScore,thirdPartyScore,supplyChainScore,operationalScore,resilienceScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseResiliencePriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseResilienceStatus status;
 private String createdBy,reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt,createdAt,updatedAt;
 @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseResilienceStatus.GENERATED;}
 @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getResilienceVision(){return resilienceVision;} public void setResilienceVision(String v){resilienceVision=v;}
 public String getBusinessContinuityPlan(){return businessContinuityPlan;} public void setBusinessContinuityPlan(String v){businessContinuityPlan=v;}
 public String getDisasterRecoveryStrategy(){return disasterRecoveryStrategy;} public void setDisasterRecoveryStrategy(String v){disasterRecoveryStrategy=v;}
 public String getCyberResilienceFramework(){return cyberResilienceFramework;} public void setCyberResilienceFramework(String v){cyberResilienceFramework=v;}
 public String getInfrastructureResilienceModel(){return infrastructureResilienceModel;} public void setInfrastructureResilienceModel(String v){infrastructureResilienceModel=v;}
 public String getWorkforceResilienceProgram(){return workforceResilienceProgram;} public void setWorkforceResilienceProgram(String v){workforceResilienceProgram=v;}
 public String getThirdPartyResilienceAssessment(){return thirdPartyResilienceAssessment;} public void setThirdPartyResilienceAssessment(String v){thirdPartyResilienceAssessment=v;}
 public String getSupplyChainResilienceStrategy(){return supplyChainResilienceStrategy;} public void setSupplyChainResilienceStrategy(String v){supplyChainResilienceStrategy=v;}
 public String getOperationalResilienceBlueprint(){return operationalResilienceBlueprint;} public void setOperationalResilienceBlueprint(String v){operationalResilienceBlueprint=v;}
 public Double getVisionScore(){return visionScore;} public void setVisionScore(Double v){visionScore=v;}
 public Double getContinuityScore(){return continuityScore;} public void setContinuityScore(Double v){continuityScore=v;}
 public Double getRecoveryScore(){return recoveryScore;} public void setRecoveryScore(Double v){recoveryScore=v;}
 public Double getCyberScore(){return cyberScore;} public void setCyberScore(Double v){cyberScore=v;}
 public Double getInfrastructureScore(){return infrastructureScore;} public void setInfrastructureScore(Double v){infrastructureScore=v;}
 public Double getWorkforceScore(){return workforceScore;} public void setWorkforceScore(Double v){workforceScore=v;}
 public Double getThirdPartyScore(){return thirdPartyScore;} public void setThirdPartyScore(Double v){thirdPartyScore=v;}
 public Double getSupplyChainScore(){return supplyChainScore;} public void setSupplyChainScore(Double v){supplyChainScore=v;}
 public Double getOperationalScore(){return operationalScore;} public void setOperationalScore(Double v){operationalScore=v;}
 public Double getResilienceScore(){return resilienceScore;} public void setResilienceScore(Double v){resilienceScore=v;}
 public AutonomousEnterpriseResiliencePriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseResiliencePriority v){priority=v;}
 public AutonomousEnterpriseResilienceStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseResilienceStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
 public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
