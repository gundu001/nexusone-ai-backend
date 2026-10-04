package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="autonomous_enterprise_autonomous_innovation")
public class AutonomousEnterpriseAutonomousInnovation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=300) private String title;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String innovationOpportunity;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String marketDisruptionStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String newRevenueStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String technologyInnovationStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String businessModelInnovation;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String customerExperienceInnovation;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String innovationRiskAssessment;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String autonomousInnovationRecommendations;
 private Double innovationPotentialScore,marketOpportunityScore,technologyFeasibilityScore,revenuePotentialScore,businessModelScore,customerValueScore,riskReadinessScore,executionReadinessScore,autonomousInnovationScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AutonomousEnterpriseAutonomousInnovationPriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AutonomousEnterpriseAutonomousInnovationStatus status;
 private String createdBy,reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt,createdAt,updatedAt;
 @PrePersist void prePersist(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseAutonomousInnovationStatus.GENERATED;}
 @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getInnovationOpportunity(){return innovationOpportunity;} public void setInnovationOpportunity(String v){innovationOpportunity=v;}
 public String getMarketDisruptionStrategy(){return marketDisruptionStrategy;} public void setMarketDisruptionStrategy(String v){marketDisruptionStrategy=v;}
 public String getNewRevenueStrategy(){return newRevenueStrategy;} public void setNewRevenueStrategy(String v){newRevenueStrategy=v;}
 public String getTechnologyInnovationStrategy(){return technologyInnovationStrategy;} public void setTechnologyInnovationStrategy(String v){technologyInnovationStrategy=v;}
 public String getBusinessModelInnovation(){return businessModelInnovation;} public void setBusinessModelInnovation(String v){businessModelInnovation=v;}
 public String getCustomerExperienceInnovation(){return customerExperienceInnovation;} public void setCustomerExperienceInnovation(String v){customerExperienceInnovation=v;}
 public String getInnovationRiskAssessment(){return innovationRiskAssessment;} public void setInnovationRiskAssessment(String v){innovationRiskAssessment=v;}
 public String getAutonomousInnovationRecommendations(){return autonomousInnovationRecommendations;} public void setAutonomousInnovationRecommendations(String v){autonomousInnovationRecommendations=v;}
 public Double getInnovationPotentialScore(){return innovationPotentialScore;} public void setInnovationPotentialScore(Double v){innovationPotentialScore=v;}
 public Double getMarketOpportunityScore(){return marketOpportunityScore;} public void setMarketOpportunityScore(Double v){marketOpportunityScore=v;}
 public Double getTechnologyFeasibilityScore(){return technologyFeasibilityScore;} public void setTechnologyFeasibilityScore(Double v){technologyFeasibilityScore=v;}
 public Double getRevenuePotentialScore(){return revenuePotentialScore;} public void setRevenuePotentialScore(Double v){revenuePotentialScore=v;}
 public Double getBusinessModelScore(){return businessModelScore;} public void setBusinessModelScore(Double v){businessModelScore=v;}
 public Double getCustomerValueScore(){return customerValueScore;} public void setCustomerValueScore(Double v){customerValueScore=v;}
 public Double getRiskReadinessScore(){return riskReadinessScore;} public void setRiskReadinessScore(Double v){riskReadinessScore=v;}
 public Double getExecutionReadinessScore(){return executionReadinessScore;} public void setExecutionReadinessScore(Double v){executionReadinessScore=v;}
 public Double getAutonomousInnovationScore(){return autonomousInnovationScore;} public void setAutonomousInnovationScore(Double v){autonomousInnovationScore=v;}
 public AutonomousEnterpriseAutonomousInnovationPriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseAutonomousInnovationPriority v){priority=v;}
 public AutonomousEnterpriseAutonomousInnovationStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseAutonomousInnovationStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;} public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;} public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;} public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
