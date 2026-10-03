package ai.nexusone.entity;
import ai.nexusone.enums.*; import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="autonomous_enterprise_self_evolving_intelligence_reports",indexes={@Index(name="idx_sei_status",columnList="status"),@Index(name="idx_sei_priority",columnList="priority"),@Index(name="idx_sei_created",columnList="created_at")})
public class AutonomousEnterpriseSelfEvolvingIntelligenceReport{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false) private String title;
 @Column(nullable=false,columnDefinition="TEXT") private String learningVelocity;
 @Column(nullable=false) private Double learningVelocityScore;
 @Column(nullable=false,columnDefinition="TEXT") private String adaptationCapacity;
 @Column(nullable=false) private Double adaptationCapacityScore;
 @Column(nullable=false,columnDefinition="TEXT") private String modelEvolution;
 @Column(nullable=false) private Double modelEvolutionScore;
 @Column(nullable=false,columnDefinition="TEXT") private String knowledgeExpansion;
 @Column(nullable=false) private Double knowledgeExpansionScore;
 @Column(nullable=false,columnDefinition="TEXT") private String feedbackIntegration;
 @Column(nullable=false) private Double feedbackIntegrationScore;
 @Column(nullable=false,columnDefinition="TEXT") private String capabilityEmergence;
 @Column(nullable=false) private Double capabilityEmergenceScore;
 @Column(nullable=false,columnDefinition="TEXT") private String governanceAlignment;
 @Column(nullable=false) private Double governanceAlignmentScore;
 @Column(nullable=false,columnDefinition="TEXT") private String safetyPreservation;
 @Column(nullable=false) private Double safetyPreservationScore;
 @Column(nullable=false,columnDefinition="TEXT") private String outcomeImprovement;
 @Column(nullable=false) private Double outcomeImprovementScore;
 @Column(nullable=false,columnDefinition="TEXT") private String evolutionRecommendations; @Column(nullable=false) private Double autonomousEnterpriseSelfEvolvingIntelligenceScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseSelfEvolvingIntelligencePriority priority; @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseSelfEvolvingIntelligenceStatus status=AutonomousEnterpriseSelfEvolvingIntelligenceStatus.GENERATED;
 @Column(nullable=false) private String createdBy; private String reviewedBy,decidedBy,publishedBy; private LocalDateTime reviewedAt,decidedAt,publishedAt; @Column(name="created_at",nullable=false,updatable=false) private LocalDateTime createdAt; @Column(name="updated_at",nullable=false) private LocalDateTime updatedAt;
 @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseSelfEvolvingIntelligenceStatus.GENERATED;} @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getLearningVelocity(){return learningVelocity;} public void setLearningVelocity(String v){learningVelocity=v;} public Double getLearningVelocityScore(){return learningVelocityScore;} public void setLearningVelocityScore(Double v){learningVelocityScore=v;}
 public String getAdaptationCapacity(){return adaptationCapacity;} public void setAdaptationCapacity(String v){adaptationCapacity=v;} public Double getAdaptationCapacityScore(){return adaptationCapacityScore;} public void setAdaptationCapacityScore(Double v){adaptationCapacityScore=v;}
 public String getModelEvolution(){return modelEvolution;} public void setModelEvolution(String v){modelEvolution=v;} public Double getModelEvolutionScore(){return modelEvolutionScore;} public void setModelEvolutionScore(Double v){modelEvolutionScore=v;}
 public String getKnowledgeExpansion(){return knowledgeExpansion;} public void setKnowledgeExpansion(String v){knowledgeExpansion=v;} public Double getKnowledgeExpansionScore(){return knowledgeExpansionScore;} public void setKnowledgeExpansionScore(Double v){knowledgeExpansionScore=v;}
 public String getFeedbackIntegration(){return feedbackIntegration;} public void setFeedbackIntegration(String v){feedbackIntegration=v;} public Double getFeedbackIntegrationScore(){return feedbackIntegrationScore;} public void setFeedbackIntegrationScore(Double v){feedbackIntegrationScore=v;}
 public String getCapabilityEmergence(){return capabilityEmergence;} public void setCapabilityEmergence(String v){capabilityEmergence=v;} public Double getCapabilityEmergenceScore(){return capabilityEmergenceScore;} public void setCapabilityEmergenceScore(Double v){capabilityEmergenceScore=v;}
 public String getGovernanceAlignment(){return governanceAlignment;} public void setGovernanceAlignment(String v){governanceAlignment=v;} public Double getGovernanceAlignmentScore(){return governanceAlignmentScore;} public void setGovernanceAlignmentScore(Double v){governanceAlignmentScore=v;}
 public String getSafetyPreservation(){return safetyPreservation;} public void setSafetyPreservation(String v){safetyPreservation=v;} public Double getSafetyPreservationScore(){return safetyPreservationScore;} public void setSafetyPreservationScore(Double v){safetyPreservationScore=v;}
 public String getOutcomeImprovement(){return outcomeImprovement;} public void setOutcomeImprovement(String v){outcomeImprovement=v;} public Double getOutcomeImprovementScore(){return outcomeImprovementScore;} public void setOutcomeImprovementScore(Double v){outcomeImprovementScore=v;}
 public String getEvolutionRecommendations(){return evolutionRecommendations;} public void setEvolutionRecommendations(String v){evolutionRecommendations=v;} public Double getAutonomousEnterpriseSelfEvolvingIntelligenceScore(){return autonomousEnterpriseSelfEvolvingIntelligenceScore;} public void setAutonomousEnterpriseSelfEvolvingIntelligenceScore(Double v){autonomousEnterpriseSelfEvolvingIntelligenceScore=v;}
 public AutonomousEnterpriseSelfEvolvingIntelligencePriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseSelfEvolvingIntelligencePriority v){priority=v;} public AutonomousEnterpriseSelfEvolvingIntelligenceStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseSelfEvolvingIntelligenceStatus v){status=v;} public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;} public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;} public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;} public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;} public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;} public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;} public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
