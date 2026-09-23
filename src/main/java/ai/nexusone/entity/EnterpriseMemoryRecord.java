package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="enterprise_memory_records",indexes={
 @Index(name="idx_memory_type",columnList="memoryType"),@Index(name="idx_memory_status",columnList="status"),
 @Index(name="idx_memory_knowledge",columnList="knowledgeId"),@Index(name="idx_memory_created",columnList="createdAt")})
public class EnterpriseMemoryRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private MemoryType memoryType;
 @Column(nullable=false) private String sourceModule;
 private Long knowledgeId,incidentId,decisionId,executionId,outcomeId,learningId;
 @Column(length=2500,nullable=false) private String context;
 @Column(length=2500,nullable=false) private String observation;
 @Column(length=3000,nullable=false) private String learnedPattern;
 @Column(length=3000,nullable=false) private String reasoningRule;
 @Column(length=3000,nullable=false) private String recommendedAction;
 @Column(nullable=false) private Double confidenceScore;
 @Column(nullable=false) private Double importanceScore;
 @Column(nullable=false) private Integer accessCount=0;
 @Column(nullable=false) private Integer reinforcementCount=0;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private MemoryStatus status=MemoryStatus.ACTIVE;
 @Column(nullable=false) private String createdBy;
 private String validatedBy; private LocalDateTime validatedAt,lastAccessedAt,createdAt,updatedAt;
 @PrePersist void insert(){createdAt=updatedAt=LocalDateTime.now();if(accessCount==null)accessCount=0;if(reinforcementCount==null)reinforcementCount=0;if(status==null)status=MemoryStatus.ACTIVE;}
 @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public MemoryType getMemoryType(){return memoryType;} public void setMemoryType(MemoryType v){memoryType=v;}
 public String getSourceModule(){return sourceModule;} public void setSourceModule(String v){sourceModule=v;}
 public Long getKnowledgeId(){return knowledgeId;} public void setKnowledgeId(Long v){knowledgeId=v;}
 public Long getIncidentId(){return incidentId;} public void setIncidentId(Long v){incidentId=v;}
 public Long getDecisionId(){return decisionId;} public void setDecisionId(Long v){decisionId=v;}
 public Long getExecutionId(){return executionId;} public void setExecutionId(Long v){executionId=v;}
 public Long getOutcomeId(){return outcomeId;} public void setOutcomeId(Long v){outcomeId=v;}
 public Long getLearningId(){return learningId;} public void setLearningId(Long v){learningId=v;}
 public String getContext(){return context;} public void setContext(String v){context=v;}
 public String getObservation(){return observation;} public void setObservation(String v){observation=v;}
 public String getLearnedPattern(){return learnedPattern;} public void setLearnedPattern(String v){learnedPattern=v;}
 public String getReasoningRule(){return reasoningRule;} public void setReasoningRule(String v){reasoningRule=v;}
 public String getRecommendedAction(){return recommendedAction;} public void setRecommendedAction(String v){recommendedAction=v;}
 public Double getConfidenceScore(){return confidenceScore;} public void setConfidenceScore(Double v){confidenceScore=v;}
 public Double getImportanceScore(){return importanceScore;} public void setImportanceScore(Double v){importanceScore=v;}
 public Integer getAccessCount(){return accessCount;} public void setAccessCount(Integer v){accessCount=v;}
 public Integer getReinforcementCount(){return reinforcementCount;} public void setReinforcementCount(Integer v){reinforcementCount=v;}
 public MemoryStatus getStatus(){return status;} public void setStatus(MemoryStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getValidatedBy(){return validatedBy;} public void setValidatedBy(String v){validatedBy=v;}
 public LocalDateTime getValidatedAt(){return validatedAt;} public void setValidatedAt(LocalDateTime v){validatedAt=v;}
 public LocalDateTime getLastAccessedAt(){return lastAccessedAt;} public void setLastAccessedAt(LocalDateTime v){lastAccessedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
