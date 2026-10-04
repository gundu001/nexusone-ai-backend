package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="autonomous_enterprise_quantum_intelligence")
public class AutonomousEnterpriseQuantumIntelligence {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=300) private String title;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String quantumOptimizationStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String quantumSimulationModel;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String hybridQuantumClassicalOrchestration;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String quantumRiskModeling;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String quantumSecurityReadiness;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String quantumDataStrategy;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String enterpriseUseCases;
 @Lob @Column(columnDefinition="LONGTEXT",nullable=false) private String quantumIntelligenceRecommendations;
 private Double optimizationScore,simulationScore,orchestrationScore,riskModelingScore,securityReadinessScore,dataReadinessScore,useCaseValueScore,adoptionReadinessScore,quantumIntelligenceScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AutonomousEnterpriseQuantumIntelligencePriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AutonomousEnterpriseQuantumIntelligenceStatus status;
 private String createdBy,reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt,createdAt,updatedAt;
 @PrePersist void prePersist(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseQuantumIntelligenceStatus.GENERATED;}
 @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getQuantumOptimizationStrategy(){return quantumOptimizationStrategy;} public void setQuantumOptimizationStrategy(String v){quantumOptimizationStrategy=v;}
 public String getQuantumSimulationModel(){return quantumSimulationModel;} public void setQuantumSimulationModel(String v){quantumSimulationModel=v;}
 public String getHybridQuantumClassicalOrchestration(){return hybridQuantumClassicalOrchestration;} public void setHybridQuantumClassicalOrchestration(String v){hybridQuantumClassicalOrchestration=v;}
 public String getQuantumRiskModeling(){return quantumRiskModeling;} public void setQuantumRiskModeling(String v){quantumRiskModeling=v;}
 public String getQuantumSecurityReadiness(){return quantumSecurityReadiness;} public void setQuantumSecurityReadiness(String v){quantumSecurityReadiness=v;}
 public String getQuantumDataStrategy(){return quantumDataStrategy;} public void setQuantumDataStrategy(String v){quantumDataStrategy=v;}
 public String getEnterpriseUseCases(){return enterpriseUseCases;} public void setEnterpriseUseCases(String v){enterpriseUseCases=v;}
 public String getQuantumIntelligenceRecommendations(){return quantumIntelligenceRecommendations;} public void setQuantumIntelligenceRecommendations(String v){quantumIntelligenceRecommendations=v;}
 public Double getOptimizationScore(){return optimizationScore;} public void setOptimizationScore(Double v){optimizationScore=v;}
 public Double getSimulationScore(){return simulationScore;} public void setSimulationScore(Double v){simulationScore=v;}
 public Double getOrchestrationScore(){return orchestrationScore;} public void setOrchestrationScore(Double v){orchestrationScore=v;}
 public Double getRiskModelingScore(){return riskModelingScore;} public void setRiskModelingScore(Double v){riskModelingScore=v;}
 public Double getSecurityReadinessScore(){return securityReadinessScore;} public void setSecurityReadinessScore(Double v){securityReadinessScore=v;}
 public Double getDataReadinessScore(){return dataReadinessScore;} public void setDataReadinessScore(Double v){dataReadinessScore=v;}
 public Double getUseCaseValueScore(){return useCaseValueScore;} public void setUseCaseValueScore(Double v){useCaseValueScore=v;}
 public Double getAdoptionReadinessScore(){return adoptionReadinessScore;} public void setAdoptionReadinessScore(Double v){adoptionReadinessScore=v;}
 public Double getQuantumIntelligenceScore(){return quantumIntelligenceScore;} public void setQuantumIntelligenceScore(Double v){quantumIntelligenceScore=v;}
 public AutonomousEnterpriseQuantumIntelligencePriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseQuantumIntelligencePriority v){priority=v;}
 public AutonomousEnterpriseQuantumIntelligenceStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseQuantumIntelligenceStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
 public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
