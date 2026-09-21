package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "intelligence_mesh_event", indexes = {
        @Index(name = "idx_mesh_created_at", columnList = "createdAt"),
        @Index(name = "idx_mesh_status", columnList = "status"),
        @Index(name = "idx_mesh_source_target", columnList = "sourceAgent,targetAgent")
})
public class IntelligenceMeshEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80) private String sourceAgent;
    @Column(nullable = false, length = 80) private String targetAgent;
    @Column(nullable = false, length = 80) private String eventType;
    @Column(nullable = false, length = 160) private String applicationName;
    @Column(nullable = false, length = 80) private String environment;
    @Column(nullable = false, length = 1000) private String contextSummary;
    @Column(nullable = false) private Double sourceConfidence;
    @Column(nullable = false) private Double targetConfidence;
    @Column(nullable = false) private Double consensusScore;
    @Column(nullable = false) private Boolean approvalRequired;
    @Column(nullable = false, length = 40) private String status;
    @Column(nullable = false, length = 80) private String decision;
    @Column(nullable = false, length = 1000) private String recommendation;
    @Column(nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist void beforeInsert(){ if(createdAt==null) createdAt=LocalDateTime.now(); }
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getSourceAgent(){return sourceAgent;} public void setSourceAgent(String v){sourceAgent=v;}
    public String getTargetAgent(){return targetAgent;} public void setTargetAgent(String v){targetAgent=v;}
    public String getEventType(){return eventType;} public void setEventType(String v){eventType=v;}
    public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
    public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
    public String getContextSummary(){return contextSummary;} public void setContextSummary(String v){contextSummary=v;}
    public Double getSourceConfidence(){return sourceConfidence;} public void setSourceConfidence(Double v){sourceConfidence=v;}
    public Double getTargetConfidence(){return targetConfidence;} public void setTargetConfidence(Double v){targetConfidence=v;}
    public Double getConsensusScore(){return consensusScore;} public void setConsensusScore(Double v){consensusScore=v;}
    public Boolean getApprovalRequired(){return approvalRequired;} public void setApprovalRequired(Boolean v){approvalRequired=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getDecision(){return decision;} public void setDecision(String v){decision=v;}
    public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
