package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="autonomous_enterprise_transformation")
public class AutonomousEnterpriseTransformation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=300) private String title;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String transformationVision;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String transformationRoadmap;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String operatingModelTransformation;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String workforceTransformation;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String processTransformation;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String technologyTransformation;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String customerTransformation;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String transformationRiskAssessment;
 private Double visionScore,roadmapScore,operatingModelScore,workforceScore,processScore,technologyScore,customerScore,executionScore,transformationScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AutonomousEnterpriseTransformationPriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AutonomousEnterpriseTransformationStatus status;
 private String createdBy,reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt,createdAt,updatedAt;
 @PrePersist void prePersist(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseTransformationStatus.GENERATED;}
 @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getQuantumOptimizationStrategy(){return transformationVision;} public void setQuantumOptimizationStrategy(String v){transformationVision=v;}
 public String getQuantumSimulationModel(){return transformationRoadmap;} public void setQuantumSimulationModel(String v){transformationRoadmap=v;}
 public String getHybridQuantumClassicalOrchestration(){return operatingModelTransformation;} public void setHybridQuantumClassicalOrchestration(String v){operatingModelTransformation=v;}
 public String getQuantumRiskModeling(){return workforceTransformation;} public void setQuantumRiskModeling(String v){workforceTransformation=v;}
 public String getQuantumSecurityReadiness(){return processTransformation;} public void setQuantumSecurityReadiness(String v){processTransformation=v;}
 public String getQuantumDataStrategy(){return technologyTransformation;} public void setQuantumDataStrategy(String v){technologyTransformation=v;}
 public String getEnterpriseUseCases(){return customerTransformation;} public void setEnterpriseUseCases(String v){customerTransformation=v;}
 public String getTransformationRecommendations(){return transformationRiskAssessment;} public void setTransformationRecommendations(String v){transformationRiskAssessment=v;}
 public Double getOptimizationScore(){return visionScore;} public void setOptimizationScore(Double v){visionScore=v;}
 public Double getSimulationScore(){return roadmapScore;} public void setSimulationScore(Double v){roadmapScore=v;}
 public Double getOrchestrationScore(){return operatingModelScore;} public void setOrchestrationScore(Double v){operatingModelScore=v;}
 public Double getRiskModelingScore(){return workforceScore;} public void setRiskModelingScore(Double v){workforceScore=v;}
 public Double getSecurityReadinessScore(){return processScore;} public void setSecurityReadinessScore(Double v){processScore=v;}
 public Double getDataReadinessScore(){return technologyScore;} public void setDataReadinessScore(Double v){technologyScore=v;}
 public Double getUseCaseValueScore(){return customerScore;} public void setUseCaseValueScore(Double v){customerScore=v;}
 public Double getAdoptionReadinessScore(){return executionScore;} public void setAdoptionReadinessScore(Double v){executionScore=v;}
 public Double getTransformationScore(){return transformationScore;} public void setTransformationScore(Double v){transformationScore=v;}
 public AutonomousEnterpriseTransformationPriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseTransformationPriority v){priority=v;}
 public AutonomousEnterpriseTransformationStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseTransformationStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
 public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
