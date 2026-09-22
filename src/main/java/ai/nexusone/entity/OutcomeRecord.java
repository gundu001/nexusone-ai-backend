package ai.nexusone.entity;

import ai.nexusone.enums.OutcomeStatus;
import ai.nexusone.enums.OutcomeType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "outcome_intelligence_records", indexes = {
    @Index(name = "idx_outcome_execution", columnList = "executionId"),
    @Index(name = "idx_outcome_status", columnList = "status"),
    @Index(name = "idx_outcome_created", columnList = "createdAt")
})
public class OutcomeRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private Long executionId;
    private Long decisionId;
    @Column(nullable = false) private String outcomeName;
    @Column(nullable = false) private String applicationName;
    @Column(nullable = false) private String environment;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private OutcomeType outcomeType;
    @Column(nullable = false) private Double availabilityScore;
    @Column(nullable = false) private Double performanceScore;
    @Column(nullable = false) private Double errorReductionScore;
    @Column(nullable = false) private Double costEfficiencyScore;
    @Column(nullable = false) private Double businessKpiScore;
    private Double overallScore;
    @Enumerated(EnumType.STRING) private OutcomeStatus status;
    @Column(length = 1200) private String summary;
    @Column(length = 1200) private String recommendation;
    @Column(length = 2000) private String evidence;
    @Column(nullable = false) private String measuredBy;
    private LocalDateTime measuredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OutcomeRecord() {}
    @PrePersist void beforeInsert(){ createdAt=updatedAt=LocalDateTime.now(); if(measuredAt==null) measuredAt=LocalDateTime.now(); }
    @PreUpdate void beforeUpdate(){ updatedAt=LocalDateTime.now(); }
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getExecutionId(){return executionId;} public void setExecutionId(Long v){executionId=v;}
    public Long getDecisionId(){return decisionId;} public void setDecisionId(Long v){decisionId=v;}
    public String getOutcomeName(){return outcomeName;} public void setOutcomeName(String v){outcomeName=v;}
    public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
    public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
    public OutcomeType getOutcomeType(){return outcomeType;} public void setOutcomeType(OutcomeType v){outcomeType=v;}
    public Double getAvailabilityScore(){return availabilityScore;} public void setAvailabilityScore(Double v){availabilityScore=v;}
    public Double getPerformanceScore(){return performanceScore;} public void setPerformanceScore(Double v){performanceScore=v;}
    public Double getErrorReductionScore(){return errorReductionScore;} public void setErrorReductionScore(Double v){errorReductionScore=v;}
    public Double getCostEfficiencyScore(){return costEfficiencyScore;} public void setCostEfficiencyScore(Double v){costEfficiencyScore=v;}
    public Double getBusinessKpiScore(){return businessKpiScore;} public void setBusinessKpiScore(Double v){businessKpiScore=v;}
    public Double getOverallScore(){return overallScore;} public void setOverallScore(Double v){overallScore=v;}
    public OutcomeStatus getStatus(){return status;} public void setStatus(OutcomeStatus v){status=v;}
    public String getSummary(){return summary;} public void setSummary(String v){summary=v;}
    public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;}
    public String getEvidence(){return evidence;} public void setEvidence(String v){evidence=v;}
    public String getMeasuredBy(){return measuredBy;} public void setMeasuredBy(String v){measuredBy=v;}
    public LocalDateTime getMeasuredAt(){return measuredAt;} public void setMeasuredAt(LocalDateTime v){measuredAt=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
