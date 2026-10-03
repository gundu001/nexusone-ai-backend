package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="autonomous_enterprise_meta_intelligence_reports",indexes={
 @Index(name="idx_ami_status",columnList="status"),
 @Index(name="idx_ami_priority",columnList="priority"),
 @Index(name="idx_ami_created",columnList="created_at")})
public class AutonomousEnterpriseMetaIntelligenceReport {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false,columnDefinition="TEXT") private String intelligenceAssessment;
 @Column(nullable=false) private Double assessmentScore;
 @Column(nullable=false,columnDefinition="TEXT") private String intelligenceOrchestration;
 @Column(nullable=false) private Double orchestrationScore;
 @Column(nullable=false,columnDefinition="TEXT") private String intelligencePrioritization;
 @Column(nullable=false) private Double prioritizationScore;
 @Column(nullable=false,columnDefinition="TEXT") private String intelligenceGovernance;
 @Column(nullable=false) private Double governanceScore;
 @Column(nullable=false,columnDefinition="TEXT") private String conflictResolution;
 @Column(nullable=false) private Double conflictResolutionScore;
 @Column(nullable=false,columnDefinition="TEXT") private String intelligenceOptimization;
 @Column(nullable=false) private Double optimizationScore;
 @Column(nullable=false,columnDefinition="TEXT") private String trustManagement;
 @Column(nullable=false) private Double trustScore;
 @Column(nullable=false,columnDefinition="TEXT") private String performanceManagement;
 @Column(nullable=false) private Double performanceScore;
 @Column(nullable=false,columnDefinition="TEXT") private String metaRecommendations;
 @Column(nullable=false) private Double autonomousEnterpriseMetaIntelligenceScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseMetaIntelligencePriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AutonomousEnterpriseMetaIntelligenceStatus status=AutonomousEnterpriseMetaIntelligenceStatus.GENERATED;
 @Column(nullable=false) private String createdBy;
 private String reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt;
 @Column(name="created_at",nullable=false,updatable=false) private LocalDateTime createdAt;
 @Column(name="updated_at",nullable=false) private LocalDateTime updatedAt;
 @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=AutonomousEnterpriseMetaIntelligenceStatus.GENERATED;}
 @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;}
 public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getIntelligenceAssessment(){return intelligenceAssessment;} public void setIntelligenceAssessment(String v){intelligenceAssessment=v;}
 public Double getAssessmentScore(){return assessmentScore;} public void setAssessmentScore(Double v){assessmentScore=v;}
 public String getIntelligenceOrchestration(){return intelligenceOrchestration;} public void setIntelligenceOrchestration(String v){intelligenceOrchestration=v;}
 public Double getOrchestrationScore(){return orchestrationScore;} public void setOrchestrationScore(Double v){orchestrationScore=v;}
 public String getIntelligencePrioritization(){return intelligencePrioritization;} public void setIntelligencePrioritization(String v){intelligencePrioritization=v;}
 public Double getPrioritizationScore(){return prioritizationScore;} public void setPrioritizationScore(Double v){prioritizationScore=v;}
 public String getIntelligenceGovernance(){return intelligenceGovernance;} public void setIntelligenceGovernance(String v){intelligenceGovernance=v;}
 public Double getGovernanceScore(){return governanceScore;} public void setGovernanceScore(Double v){governanceScore=v;}
 public String getConflictResolution(){return conflictResolution;} public void setConflictResolution(String v){conflictResolution=v;}
 public Double getConflictResolutionScore(){return conflictResolutionScore;} public void setConflictResolutionScore(Double v){conflictResolutionScore=v;}
 public String getIntelligenceOptimization(){return intelligenceOptimization;} public void setIntelligenceOptimization(String v){intelligenceOptimization=v;}
 public Double getOptimizationScore(){return optimizationScore;} public void setOptimizationScore(Double v){optimizationScore=v;}
 public String getTrustManagement(){return trustManagement;} public void setTrustManagement(String v){trustManagement=v;}
 public Double getTrustScore(){return trustScore;} public void setTrustScore(Double v){trustScore=v;}
 public String getPerformanceManagement(){return performanceManagement;} public void setPerformanceManagement(String v){performanceManagement=v;}
 public Double getPerformanceScore(){return performanceScore;} public void setPerformanceScore(Double v){performanceScore=v;}
 public String getMetaRecommendations(){return metaRecommendations;} public void setMetaRecommendations(String v){metaRecommendations=v;}
 public Double getAutonomousEnterpriseMetaIntelligenceScore(){return autonomousEnterpriseMetaIntelligenceScore;} public void setAutonomousEnterpriseMetaIntelligenceScore(Double v){autonomousEnterpriseMetaIntelligenceScore=v;}
 public AutonomousEnterpriseMetaIntelligencePriority getPriority(){return priority;} public void setPriority(AutonomousEnterpriseMetaIntelligencePriority v){priority=v;}
 public AutonomousEnterpriseMetaIntelligenceStatus getStatus(){return status;} public void setStatus(AutonomousEnterpriseMetaIntelligenceStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
 public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
