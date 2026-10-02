package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="strategic_autonomous_enterprise_brain_reports", indexes={
 @Index(name="idx_saeb_status",columnList="status"), @Index(name="idx_saeb_priority",columnList="priority"),
 @Index(name="idx_saeb_created",columnList="created_at")})
public class StrategicEnterpriseBrainReport {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String title;
    @Column(nullable=false, columnDefinition="TEXT") private String strategicVision;
    @Column(nullable=false, columnDefinition="TEXT") private String enterpriseContext;
    @Column(nullable=false, columnDefinition="TEXT") private String decisionIntelligence;
    @Column(nullable=false, columnDefinition="TEXT") private String scenarioPlanning;
    @Column(nullable=false, columnDefinition="TEXT") private String riskAnticipation;
    @Column(nullable=false, columnDefinition="TEXT") private String executionAlignment;
    @Column(nullable=false, columnDefinition="TEXT") private String learningAdaptation;
    @Column(nullable=false, columnDefinition="TEXT") private String innovationIntelligence;
    @Column(nullable=false, columnDefinition="TEXT") private String valueOrchestration;
    @Column(nullable=false,columnDefinition="TEXT") private String strategicRecommendations;
    @Column(nullable=false) private Double strategicVisionScore;
    @Column(nullable=false) private Double enterpriseContextScore;
    @Column(nullable=false) private Double decisionIntelligenceScore;
    @Column(nullable=false) private Double scenarioPlanningScore;
    @Column(nullable=false) private Double riskAnticipationScore;
    @Column(nullable=false) private Double executionAlignmentScore;
    @Column(nullable=false) private Double learningAdaptationScore;
    @Column(nullable=false) private Double innovationIntelligenceScore;
    @Column(nullable=false) private Double valueOrchestrationScore;
    @Column(nullable=false) private Double strategicEnterpriseBrainScore;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private StrategicEnterpriseBrainPriority priority;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private StrategicEnterpriseBrainStatus status=StrategicEnterpriseBrainStatus.GENERATED;
    @Column(nullable=false) private String createdBy;
    private String reviewedBy, decidedBy, publishedBy;
    private LocalDateTime reviewedAt, decidedAt, publishedAt;
    @Column(name="created_at",nullable=false,updatable=false) private LocalDateTime createdAt;
    @Column(name="updated_at",nullable=false) private LocalDateTime updatedAt;
    @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(status==null)status=StrategicEnterpriseBrainStatus.GENERATED;}
    @PreUpdate void update(){updatedAt=LocalDateTime.now();}
    public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getStrategicVision() { return strategicVision; }
    public void setStrategicVision(String value) { strategicVision=value; }
    public Double getStrategicVisionScore() { return strategicVisionScore; }
    public void setStrategicVisionScore(Double value) { strategicVisionScore=value; }
    public String getEnterpriseContext() { return enterpriseContext; }
    public void setEnterpriseContext(String value) { enterpriseContext=value; }
    public Double getEnterpriseContextScore() { return enterpriseContextScore; }
    public void setEnterpriseContextScore(Double value) { enterpriseContextScore=value; }
    public String getDecisionIntelligence() { return decisionIntelligence; }
    public void setDecisionIntelligence(String value) { decisionIntelligence=value; }
    public Double getDecisionIntelligenceScore() { return decisionIntelligenceScore; }
    public void setDecisionIntelligenceScore(Double value) { decisionIntelligenceScore=value; }
    public String getScenarioPlanning() { return scenarioPlanning; }
    public void setScenarioPlanning(String value) { scenarioPlanning=value; }
    public Double getScenarioPlanningScore() { return scenarioPlanningScore; }
    public void setScenarioPlanningScore(Double value) { scenarioPlanningScore=value; }
    public String getRiskAnticipation() { return riskAnticipation; }
    public void setRiskAnticipation(String value) { riskAnticipation=value; }
    public Double getRiskAnticipationScore() { return riskAnticipationScore; }
    public void setRiskAnticipationScore(Double value) { riskAnticipationScore=value; }
    public String getExecutionAlignment() { return executionAlignment; }
    public void setExecutionAlignment(String value) { executionAlignment=value; }
    public Double getExecutionAlignmentScore() { return executionAlignmentScore; }
    public void setExecutionAlignmentScore(Double value) { executionAlignmentScore=value; }
    public String getLearningAdaptation() { return learningAdaptation; }
    public void setLearningAdaptation(String value) { learningAdaptation=value; }
    public Double getLearningAdaptationScore() { return learningAdaptationScore; }
    public void setLearningAdaptationScore(Double value) { learningAdaptationScore=value; }
    public String getInnovationIntelligence() { return innovationIntelligence; }
    public void setInnovationIntelligence(String value) { innovationIntelligence=value; }
    public Double getInnovationIntelligenceScore() { return innovationIntelligenceScore; }
    public void setInnovationIntelligenceScore(Double value) { innovationIntelligenceScore=value; }
    public String getValueOrchestration() { return valueOrchestration; }
    public void setValueOrchestration(String value) { valueOrchestration=value; }
    public Double getValueOrchestrationScore() { return valueOrchestrationScore; }
    public void setValueOrchestrationScore(Double value) { valueOrchestrationScore=value; }
    public String getStrategicRecommendations(){return strategicRecommendations;} public void setStrategicRecommendations(String v){strategicRecommendations=v;}
    public Double getStrategicEnterpriseBrainScore(){return strategicEnterpriseBrainScore;} public void setStrategicEnterpriseBrainScore(Double v){strategicEnterpriseBrainScore=v;}
    public StrategicEnterpriseBrainPriority getPriority(){return priority;} public void setPriority(StrategicEnterpriseBrainPriority v){priority=v;}
    public StrategicEnterpriseBrainStatus getStatus(){return status;} public void setStatus(StrategicEnterpriseBrainStatus v){status=v;}
    public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
    public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
    public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
    public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
    public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
    public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
    public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
