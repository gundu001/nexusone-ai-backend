package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="autonomous_enterprise_collective_intelligence_reports",indexes={
 @Index(name="idx_aci_status",columnList="status"),@Index(name="idx_aci_priority",columnList="priority"),
 @Index(name="idx_aci_created",columnList="created_at")})
public class AutonomousEnterpriseCollectiveIntelligenceReport {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false,columnDefinition="TEXT") private String humanExpertise;
 @Column(nullable=false) private Double humanExpertiseScore;
 @Column(nullable=false,columnDefinition="TEXT") private String agentCollaboration;
 @Column(nullable=false) private Double agentCollaborationScore;
 @Column(nullable=false,columnDefinition="TEXT") private String crossTeamKnowledge;
 @Column(nullable=false) private Double crossTeamKnowledgeScore;
 @Column(nullable=false,columnDefinition="TEXT") private String consensusQuality;
 @Column(nullable=false) private Double consensusQualityScore;
 @Column(nullable=false,columnDefinition="TEXT") private String diversityOfThought;
 @Column(nullable=false) private Double diversityOfThoughtScore;
 @Column(nullable=false,columnDefinition="TEXT") private String sharedLearning;
 @Column(nullable=false) private Double sharedLearningScore;
 @Column(nullable=false,columnDefinition="TEXT") private String decisionAlignment;
 @Column(nullable=false) private Double decisionAlignmentScore;
 @Column(nullable=false,columnDefinition="TEXT") private String governanceAlignment;
 @Column(nullable=false) private Double governanceAlignmentScore;
 @Column(nullable=false,columnDefinition="TEXT") private String outcomeImpact;
 @Column(nullable=false) private Double outcomeImpactScore;
 @Column(nullable=false,columnDefinition="TEXT") private String collectiveRecommendations;
 @Column(nullable=false) private Double autonomousEnterpriseCollectiveIntelligenceScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseCollectiveIntelligencePriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseCollectiveIntelligenceStatus status=AutonomousEnterpriseCollectiveIntelligenceStatus.GENERATED;
 @Column(nullable=false) private String createdBy;
 private String reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt;
 @Column(name="created_at",nullable=false,updatable=false) private LocalDateTime createdAt;
 @Column(name="updated_at",nullable=false) private LocalDateTime updatedAt;
 @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseCollectiveIntelligenceStatus.GENERATED;}
 @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getHumanExpertise(){return humanExpertise;} public void setHumanExpertise(String v){humanExpertise=v;} public Double getHumanExpertiseScore(){return humanExpertiseScore;} public void setHumanExpertiseScore(Double v){humanExpertiseScore=v;}
 public String getAgentCollaboration(){return agentCollaboration;} public void setAgentCollaboration(String v){agentCollaboration=v;} public Double getAgentCollaborationScore(){return agentCollaborationScore;} public void setAgentCollaborationScore(Double v){agentCollaborationScore=v;}
 public String getCrossTeamKnowledge(){return crossTeamKnowledge;} public void setCrossTeamKnowledge(String v){crossTeamKnowledge=v;} public Double getCrossTeamKnowledgeScore(){return crossTeamKnowledgeScore;} public void setCrossTeamKnowledgeScore(Double v){crossTeamKnowledgeScore=v;}
 public String getConsensusQuality(){return consensusQuality;} public void setConsensusQuality(String v){consensusQuality=v;} public Double getConsensusQualityScore(){return consensusQualityScore;} public void setConsensusQualityScore(Double v){consensusQualityScore=v;}
 public String getDiversityOfThought(){return diversityOfThought;} public void setDiversityOfThought(String v){diversityOfThought=v;} public Double getDiversityOfThoughtScore(){return diversityOfThoughtScore;} public void setDiversityOfThoughtScore(Double v){diversityOfThoughtScore=v;}
 public String getSharedLearning(){return sharedLearning;} public void setSharedLearning(String v){sharedLearning=v;} public Double getSharedLearningScore(){return sharedLearningScore;} public void setSharedLearningScore(Double v){sharedLearningScore=v;}
 public String getDecisionAlignment(){return decisionAlignment;} public void setDecisionAlignment(String v){decisionAlignment=v;} public Double getDecisionAlignmentScore(){return decisionAlignmentScore;} public void setDecisionAlignmentScore(Double v){decisionAlignmentScore=v;}
 public String getGovernanceAlignment(){return governanceAlignment;} public void setGovernanceAlignment(String v){governanceAlignment=v;} public Double getGovernanceAlignmentScore(){return governanceAlignmentScore;} public void setGovernanceAlignmentScore(Double v){governanceAlignmentScore=v;}
 public String getOutcomeImpact(){return outcomeImpact;} public void setOutcomeImpact(String v){outcomeImpact=v;} public Double getOutcomeImpactScore(){return outcomeImpactScore;} public void setOutcomeImpactScore(Double v){outcomeImpactScore=v;}
 public String getCollectiveRecommendations(){return collectiveRecommendations;} public void setCollectiveRecommendations(String v){collectiveRecommendations=v;}
 public Double getAutonomousEnterpriseCollectiveIntelligenceScore(){return autonomousEnterpriseCollectiveIntelligenceScore;} public void setAutonomousEnterpriseCollectiveIntelligenceScore(Double v){autonomousEnterpriseCollectiveIntelligenceScore=v;}
 public AutonomousEnterpriseCollectiveIntelligencePriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseCollectiveIntelligencePriority v){priority=v;}
 public AutonomousEnterpriseCollectiveIntelligenceStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseCollectiveIntelligenceStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;} public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;} public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;} public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
