package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="autonomous_enterprise_omni_intelligence")
public class AutonomousEnterpriseOmniIntelligence {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=300) private String title;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String omnichannelAssessment;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String crossDomainFusion;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String multimodalReasoning;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String intelligenceSynchronization;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String contextUnification;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String decisionOrchestration;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String governanceAndTrust;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String outcomeOptimization;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String omniRecommendations;
 private Double assessmentScore,fusionScore,reasoningScore,synchronizationScore,contextScore,orchestrationScore,trustScore,optimizationScore,autonomousEnterpriseOmniIntelligenceScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AutonomousEnterpriseOmniIntelligencePriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AutonomousEnterpriseOmniIntelligenceStatus status;
 private String createdBy,reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt,createdAt,updatedAt;
 @PrePersist void prePersist(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseOmniIntelligenceStatus.GENERATED;}
 @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getOmnichannelAssessment(){return omnichannelAssessment;} public void setOmnichannelAssessment(String v){omnichannelAssessment=v;}
 public String getCrossDomainFusion(){return crossDomainFusion;} public void setCrossDomainFusion(String v){crossDomainFusion=v;}
 public String getMultimodalReasoning(){return multimodalReasoning;} public void setMultimodalReasoning(String v){multimodalReasoning=v;}
 public String getIntelligenceSynchronization(){return intelligenceSynchronization;} public void setIntelligenceSynchronization(String v){intelligenceSynchronization=v;}
 public String getContextUnification(){return contextUnification;} public void setContextUnification(String v){contextUnification=v;}
 public String getDecisionOrchestration(){return decisionOrchestration;} public void setDecisionOrchestration(String v){decisionOrchestration=v;}
 public String getGovernanceAndTrust(){return governanceAndTrust;} public void setGovernanceAndTrust(String v){governanceAndTrust=v;}
 public String getOutcomeOptimization(){return outcomeOptimization;} public void setOutcomeOptimization(String v){outcomeOptimization=v;}
 public String getOmniRecommendations(){return omniRecommendations;} public void setOmniRecommendations(String v){omniRecommendations=v;}
 public Double getAssessmentScore(){return assessmentScore;} public void setAssessmentScore(Double v){assessmentScore=v;}
 public Double getFusionScore(){return fusionScore;} public void setFusionScore(Double v){fusionScore=v;}
 public Double getReasoningScore(){return reasoningScore;} public void setReasoningScore(Double v){reasoningScore=v;}
 public Double getSynchronizationScore(){return synchronizationScore;} public void setSynchronizationScore(Double v){synchronizationScore=v;}
 public Double getContextScore(){return contextScore;} public void setContextScore(Double v){contextScore=v;}
 public Double getOrchestrationScore(){return orchestrationScore;} public void setOrchestrationScore(Double v){orchestrationScore=v;}
 public Double getTrustScore(){return trustScore;} public void setTrustScore(Double v){trustScore=v;}
 public Double getOptimizationScore(){return optimizationScore;} public void setOptimizationScore(Double v){optimizationScore=v;}
 public Double getAutonomousEnterpriseOmniIntelligenceScore(){return autonomousEnterpriseOmniIntelligenceScore;} public void setAutonomousEnterpriseOmniIntelligenceScore(Double v){autonomousEnterpriseOmniIntelligenceScore=v;}
 public AutonomousEnterpriseOmniIntelligencePriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseOmniIntelligencePriority v){priority=v;}
 public AutonomousEnterpriseOmniIntelligenceStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseOmniIntelligenceStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
 public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
