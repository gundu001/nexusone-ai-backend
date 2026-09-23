package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="learning_optimization_records",indexes={@Index(name="idx_learning_outcome",columnList="outcomeId"),@Index(name="idx_learning_status",columnList="status"),@Index(name="idx_learning_created",columnList="createdAt")})
public class LearningRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private Long outcomeId; @Column(nullable=false) private Long executionId;
 @Column(nullable=false) private String applicationName; @Column(nullable=false) private String environment;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private OptimizationArea optimizationArea;
 @Column(nullable=false) private Double observedScore; @Column(nullable=false) private Double targetScore;
 private Double improvementGap; private Double confidenceScore; private Integer priorityScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private LearningStatus status;
 @Column(length=1200) private String learningSummary; @Column(length=1200) private String optimizationRecommendation;
 @Column(length=2000) private String evidence; @Column(nullable=false) private String createdBy;
 private String appliedBy; private LocalDateTime appliedAt; private LocalDateTime createdAt; private LocalDateTime updatedAt;
 public LearningRecord(){}
 @PrePersist void insert(){createdAt=updatedAt=LocalDateTime.now();} @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public Long getOutcomeId(){return outcomeId;} public void setOutcomeId(Long v){outcomeId=v;} public Long getExecutionId(){return executionId;} public void setExecutionId(Long v){executionId=v;}
 public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;} public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
 public OptimizationArea getOptimizationArea(){return optimizationArea;} public void setOptimizationArea(OptimizationArea v){optimizationArea=v;} public Double getObservedScore(){return observedScore;} public void setObservedScore(Double v){observedScore=v;}
 public Double getTargetScore(){return targetScore;} public void setTargetScore(Double v){targetScore=v;} public Double getImprovementGap(){return improvementGap;} public void setImprovementGap(Double v){improvementGap=v;}
 public Double getConfidenceScore(){return confidenceScore;} public void setConfidenceScore(Double v){confidenceScore=v;} public Integer getPriorityScore(){return priorityScore;} public void setPriorityScore(Integer v){priorityScore=v;}
 public LearningStatus getStatus(){return status;} public void setStatus(LearningStatus v){status=v;} public String getLearningSummary(){return learningSummary;} public void setLearningSummary(String v){learningSummary=v;}
 public String getOptimizationRecommendation(){return optimizationRecommendation;} public void setOptimizationRecommendation(String v){optimizationRecommendation=v;} public String getEvidence(){return evidence;} public void setEvidence(String v){evidence=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;} public String getAppliedBy(){return appliedBy;} public void setAppliedBy(String v){appliedBy=v;} public LocalDateTime getAppliedAt(){return appliedAt;} public void setAppliedAt(LocalDateTime v){appliedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
