package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="strategic_initiatives", indexes={@Index(name="idx_strategy_status",columnList="status"),@Index(name="idx_strategy_type",columnList="strategicType"),@Index(name="idx_strategy_created",columnList="createdAt")})
public class StrategicInitiative {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private StrategicType strategicType;
 @Column(nullable=false,length=3000) private String businessObjective;
 @Column(nullable=false,length=3000) private String currentState;
 @Column(nullable=false,length=3000) private String targetState;
 @Column(nullable=false,length=3000) private String businessImpact;
 @Column(nullable=false,length=3000) private String recommendation;
 @Column(nullable=false) private Double alignmentScore;
 @Column(nullable=false) private Double valueScore;
 @Column(nullable=false) private Double riskScore;
 @Column(nullable=false) private Double priorityScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private StrategicStatus status=StrategicStatus.DRAFT;
 @Column(nullable=false) private String createdBy;
 private String reviewedBy; private LocalDateTime reviewedAt; private String approvedBy; private LocalDateTime approvedAt; private LocalDateTime createdAt; private LocalDateTime updatedAt;
 @PrePersist void prePersist(){var now=LocalDateTime.now();createdAt=now;updatedAt=now;if(status==null)status=StrategicStatus.DRAFT;}
 @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;} public StrategicType getStrategicType(){return strategicType;} public void setStrategicType(StrategicType v){strategicType=v;} public String getBusinessObjective(){return businessObjective;} public void setBusinessObjective(String v){businessObjective=v;} public String getCurrentState(){return currentState;} public void setCurrentState(String v){currentState=v;} public String getTargetState(){return targetState;} public void setTargetState(String v){targetState=v;} public String getBusinessImpact(){return businessImpact;} public void setBusinessImpact(String v){businessImpact=v;} public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;} public Double getAlignmentScore(){return alignmentScore;} public void setAlignmentScore(Double v){alignmentScore=v;} public Double getValueScore(){return valueScore;} public void setValueScore(Double v){valueScore=v;} public Double getRiskScore(){return riskScore;} public void setRiskScore(Double v){riskScore=v;} public Double getPriorityScore(){return priorityScore;} public void setPriorityScore(Double v){priorityScore=v;} public StrategicStatus getStatus(){return status;} public void setStatus(StrategicStatus v){status=v;} public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;} public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;} public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;} public String getApprovedBy(){return approvedBy;} public void setApprovedBy(String v){approvedBy=v;} public LocalDateTime getApprovedAt(){return approvedAt;} public void setApprovedAt(LocalDateTime v){approvedAt=v;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
