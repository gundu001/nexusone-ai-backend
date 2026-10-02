package ai.nexusone.entity;
import ai.nexusone.enums.*; import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="autonomous_enterprise_reasoning_reports",indexes={@Index(name="idx_aer_status",columnList="status"),@Index(name="idx_aer_priority",columnList="priority"),@Index(name="idx_aer_created",columnList="created_at")})
public class AutonomousEnterpriseReasoningReport {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false) private String title;
 @Column(nullable=false,columnDefinition="TEXT") private String causalReasoning;
 @Column(nullable=false) private Double causalReasoningScore;
 @Column(nullable=false,columnDefinition="TEXT") private String multiStepReasoning;
 @Column(nullable=false) private Double multiStepReasoningScore;
 @Column(nullable=false,columnDefinition="TEXT") private String strategicReasoning;
 @Column(nullable=false) private Double strategicReasoningScore;
 @Column(nullable=false,columnDefinition="TEXT") private String operationalReasoning;
 @Column(nullable=false) private Double operationalReasoningScore;
 @Column(nullable=false,columnDefinition="TEXT") private String predictiveReasoning;
 @Column(nullable=false) private Double predictiveReasoningScore;
 @Column(nullable=false,columnDefinition="TEXT") private String decisionJustification;
 @Column(nullable=false) private Double decisionJustificationScore;
 @Column(nullable=false,columnDefinition="TEXT") private String adaptiveReasoning;
 @Column(nullable=false) private Double adaptiveReasoningScore;
 @Column(nullable=false,columnDefinition="TEXT") private String goalDrivenReasoning;
 @Column(nullable=false) private Double goalDrivenReasoningScore;
 @Column(nullable=false,columnDefinition="TEXT") private String enterpriseKnowledgeReasoning;
 @Column(nullable=false) private Double enterpriseKnowledgeReasoningScore;
 @Column(nullable=false,columnDefinition="TEXT") private String reasoningRecommendations; @Column(nullable=false) private Double autonomousEnterpriseReasoningScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseReasoningPriority priority; @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseReasoningStatus status=AutonomousEnterpriseReasoningStatus.GENERATED;
 @Column(nullable=false) private String createdBy; private String reviewedBy,decidedBy,publishedBy; private LocalDateTime reviewedAt,decidedAt,publishedAt; @Column(name="created_at",nullable=false,updatable=false) private LocalDateTime createdAt; @Column(name="updated_at",nullable=false) private LocalDateTime updatedAt;
 @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseReasoningStatus.GENERATED;} @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getCausalReasoning(){return causalReasoning;} public void setCausalReasoning(String v){causalReasoning=v;} public Double getCausalReasoningScore(){return causalReasoningScore;} public void setCausalReasoningScore(Double v){causalReasoningScore=v;}
 public String getMultiStepReasoning(){return multiStepReasoning;} public void setMultiStepReasoning(String v){multiStepReasoning=v;} public Double getMultiStepReasoningScore(){return multiStepReasoningScore;} public void setMultiStepReasoningScore(Double v){multiStepReasoningScore=v;}
 public String getStrategicReasoning(){return strategicReasoning;} public void setStrategicReasoning(String v){strategicReasoning=v;} public Double getStrategicReasoningScore(){return strategicReasoningScore;} public void setStrategicReasoningScore(Double v){strategicReasoningScore=v;}
 public String getOperationalReasoning(){return operationalReasoning;} public void setOperationalReasoning(String v){operationalReasoning=v;} public Double getOperationalReasoningScore(){return operationalReasoningScore;} public void setOperationalReasoningScore(Double v){operationalReasoningScore=v;}
 public String getPredictiveReasoning(){return predictiveReasoning;} public void setPredictiveReasoning(String v){predictiveReasoning=v;} public Double getPredictiveReasoningScore(){return predictiveReasoningScore;} public void setPredictiveReasoningScore(Double v){predictiveReasoningScore=v;}
 public String getDecisionJustification(){return decisionJustification;} public void setDecisionJustification(String v){decisionJustification=v;} public Double getDecisionJustificationScore(){return decisionJustificationScore;} public void setDecisionJustificationScore(Double v){decisionJustificationScore=v;}
 public String getAdaptiveReasoning(){return adaptiveReasoning;} public void setAdaptiveReasoning(String v){adaptiveReasoning=v;} public Double getAdaptiveReasoningScore(){return adaptiveReasoningScore;} public void setAdaptiveReasoningScore(Double v){adaptiveReasoningScore=v;}
 public String getGoalDrivenReasoning(){return goalDrivenReasoning;} public void setGoalDrivenReasoning(String v){goalDrivenReasoning=v;} public Double getGoalDrivenReasoningScore(){return goalDrivenReasoningScore;} public void setGoalDrivenReasoningScore(Double v){goalDrivenReasoningScore=v;}
 public String getEnterpriseKnowledgeReasoning(){return enterpriseKnowledgeReasoning;} public void setEnterpriseKnowledgeReasoning(String v){enterpriseKnowledgeReasoning=v;} public Double getEnterpriseKnowledgeReasoningScore(){return enterpriseKnowledgeReasoningScore;} public void setEnterpriseKnowledgeReasoningScore(Double v){enterpriseKnowledgeReasoningScore=v;}
 public String getReasoningRecommendations(){return reasoningRecommendations;} public void setReasoningRecommendations(String v){reasoningRecommendations=v;} public Double getAutonomousEnterpriseReasoningScore(){return autonomousEnterpriseReasoningScore;} public void setAutonomousEnterpriseReasoningScore(Double v){autonomousEnterpriseReasoningScore=v;}
 public AutonomousEnterpriseReasoningPriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseReasoningPriority v){priority=v;} public AutonomousEnterpriseReasoningStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseReasoningStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;} public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;} public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;} public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;} public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;} public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
