package ai.nexusone.entity;
import ai.nexusone.enums.*; import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="autonomous_enterprise_swarm_intelligence_reports",indexes={@Index(name="idx_asi_status",columnList="status"),@Index(name="idx_asi_priority",columnList="priority"),@Index(name="idx_asi_created",columnList="created_at")})
public class AutonomousEnterpriseSwarmIntelligenceReport{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false) private String title;
 @Column(nullable=false,columnDefinition="TEXT") private String agentCoordination;
 @Column(nullable=false) private Double agentCoordinationScore;
 @Column(nullable=false,columnDefinition="TEXT") private String taskDistribution;
 @Column(nullable=false) private Double taskDistributionScore;
 @Column(nullable=false,columnDefinition="TEXT") private String modelSwarm;
 @Column(nullable=false) private Double modelSwarmScore;
 @Column(nullable=false,columnDefinition="TEXT") private String emergentBehavior;
 @Column(nullable=false) private Double emergentBehaviorScore;
 @Column(nullable=false,columnDefinition="TEXT") private String collectiveProblemSolving;
 @Column(nullable=false) private Double collectiveProblemSolvingScore;
 @Column(nullable=false,columnDefinition="TEXT") private String resourceOptimization;
 @Column(nullable=false) private Double resourceOptimizationScore;
 @Column(nullable=false,columnDefinition="TEXT") private String communicationEfficiency;
 @Column(nullable=false) private Double communicationEfficiencyScore;
 @Column(nullable=false,columnDefinition="TEXT") private String resilience;
 @Column(nullable=false) private Double resilienceScore;
 @Column(nullable=false,columnDefinition="TEXT") private String autonomousExecution;
 @Column(nullable=false) private Double autonomousExecutionScore;
 @Column(nullable=false,columnDefinition="TEXT") private String swarmRecommendations; @Column(nullable=false) private Double autonomousEnterpriseSwarmIntelligenceScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseSwarmIntelligencePriority priority; @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseSwarmIntelligenceStatus status=AutonomousEnterpriseSwarmIntelligenceStatus.GENERATED;
 @Column(nullable=false) private String createdBy; private String reviewedBy,decidedBy,publishedBy; private LocalDateTime reviewedAt,decidedAt,publishedAt; @Column(name="created_at",nullable=false,updatable=false) private LocalDateTime createdAt; @Column(name="updated_at",nullable=false) private LocalDateTime updatedAt;
 @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseSwarmIntelligenceStatus.GENERATED;} @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getAgentCoordination(){return agentCoordination;} public void setAgentCoordination(String v){agentCoordination=v;} public Double getAgentCoordinationScore(){return agentCoordinationScore;} public void setAgentCoordinationScore(Double v){agentCoordinationScore=v;}
 public String getTaskDistribution(){return taskDistribution;} public void setTaskDistribution(String v){taskDistribution=v;} public Double getTaskDistributionScore(){return taskDistributionScore;} public void setTaskDistributionScore(Double v){taskDistributionScore=v;}
 public String getModelSwarm(){return modelSwarm;} public void setModelSwarm(String v){modelSwarm=v;} public Double getModelSwarmScore(){return modelSwarmScore;} public void setModelSwarmScore(Double v){modelSwarmScore=v;}
 public String getEmergentBehavior(){return emergentBehavior;} public void setEmergentBehavior(String v){emergentBehavior=v;} public Double getEmergentBehaviorScore(){return emergentBehaviorScore;} public void setEmergentBehaviorScore(Double v){emergentBehaviorScore=v;}
 public String getCollectiveProblemSolving(){return collectiveProblemSolving;} public void setCollectiveProblemSolving(String v){collectiveProblemSolving=v;} public Double getCollectiveProblemSolvingScore(){return collectiveProblemSolvingScore;} public void setCollectiveProblemSolvingScore(Double v){collectiveProblemSolvingScore=v;}
 public String getResourceOptimization(){return resourceOptimization;} public void setResourceOptimization(String v){resourceOptimization=v;} public Double getResourceOptimizationScore(){return resourceOptimizationScore;} public void setResourceOptimizationScore(Double v){resourceOptimizationScore=v;}
 public String getCommunicationEfficiency(){return communicationEfficiency;} public void setCommunicationEfficiency(String v){communicationEfficiency=v;} public Double getCommunicationEfficiencyScore(){return communicationEfficiencyScore;} public void setCommunicationEfficiencyScore(Double v){communicationEfficiencyScore=v;}
 public String getResilience(){return resilience;} public void setResilience(String v){resilience=v;} public Double getResilienceScore(){return resilienceScore;} public void setResilienceScore(Double v){resilienceScore=v;}
 public String getAutonomousExecution(){return autonomousExecution;} public void setAutonomousExecution(String v){autonomousExecution=v;} public Double getAutonomousExecutionScore(){return autonomousExecutionScore;} public void setAutonomousExecutionScore(Double v){autonomousExecutionScore=v;}
 public String getSwarmRecommendations(){return swarmRecommendations;} public void setSwarmRecommendations(String v){swarmRecommendations=v;} public Double getAutonomousEnterpriseSwarmIntelligenceScore(){return autonomousEnterpriseSwarmIntelligenceScore;} public void setAutonomousEnterpriseSwarmIntelligenceScore(Double v){autonomousEnterpriseSwarmIntelligenceScore=v;}
 public AutonomousEnterpriseSwarmIntelligencePriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseSwarmIntelligencePriority v){priority=v;} public AutonomousEnterpriseSwarmIntelligenceStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseSwarmIntelligenceStatus v){status=v;} public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;} public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;} public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;} public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;} public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;} public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;} public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
