package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="autonomous_enterprise_universal_knowledge")
public class AutonomousEnterpriseUniversalKnowledge {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=300) private String title;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String knowledgeGraphStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String crossFabricKnowledgeLinking;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String organizationalMemory;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String semanticDiscovery;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String knowledgeGovernance;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String knowledgeQuality;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String reuseAndLearning;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String universalKnowledgeRecommendations;
 private Double graphScore,linkingScore,memoryScore,discoveryScore,governanceScore,qualityScore,reuseScore,learningScore,universalKnowledgeScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AutonomousEnterpriseUniversalKnowledgePriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AutonomousEnterpriseUniversalKnowledgeStatus status;
 private String createdBy,reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt,createdAt,updatedAt;
 @PrePersist void prePersist(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseUniversalKnowledgeStatus.GENERATED;}
 @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getKnowledgeGraphStrategy(){return knowledgeGraphStrategy;} public void setKnowledgeGraphStrategy(String v){knowledgeGraphStrategy=v;}
 public String getCrossFabricKnowledgeLinking(){return crossFabricKnowledgeLinking;} public void setCrossFabricKnowledgeLinking(String v){crossFabricKnowledgeLinking=v;}
 public String getOrganizationalMemory(){return organizationalMemory;} public void setOrganizationalMemory(String v){organizationalMemory=v;}
 public String getSemanticDiscovery(){return semanticDiscovery;} public void setSemanticDiscovery(String v){semanticDiscovery=v;}
 public String getKnowledgeGovernance(){return knowledgeGovernance;} public void setKnowledgeGovernance(String v){knowledgeGovernance=v;}
 public String getKnowledgeQuality(){return knowledgeQuality;} public void setKnowledgeQuality(String v){knowledgeQuality=v;}
 public String getReuseAndLearning(){return reuseAndLearning;} public void setReuseAndLearning(String v){reuseAndLearning=v;}
 public String getUniversalKnowledgeRecommendations(){return universalKnowledgeRecommendations;} public void setUniversalKnowledgeRecommendations(String v){universalKnowledgeRecommendations=v;}
 public Double getGraphScore(){return graphScore;} public void setGraphScore(Double v){graphScore=v;} public Double getLinkingScore(){return linkingScore;} public void setLinkingScore(Double v){linkingScore=v;}
 public Double getMemoryScore(){return memoryScore;} public void setMemoryScore(Double v){memoryScore=v;} public Double getDiscoveryScore(){return discoveryScore;} public void setDiscoveryScore(Double v){discoveryScore=v;}
 public Double getGovernanceScore(){return governanceScore;} public void setGovernanceScore(Double v){governanceScore=v;} public Double getQualityScore(){return qualityScore;} public void setQualityScore(Double v){qualityScore=v;}
 public Double getReuseScore(){return reuseScore;} public void setReuseScore(Double v){reuseScore=v;} public Double getLearningScore(){return learningScore;} public void setLearningScore(Double v){learningScore=v;}
 public Double getUniversalKnowledgeScore(){return universalKnowledgeScore;} public void setUniversalKnowledgeScore(Double v){universalKnowledgeScore=v;}
 public AutonomousEnterpriseUniversalKnowledgePriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseUniversalKnowledgePriority v){priority=v;}
 public AutonomousEnterpriseUniversalKnowledgeStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseUniversalKnowledgeStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;} public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;} public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;} public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
