package ai.nexusone.entity;
import ai.nexusone.enums.BoardroomBriefingStatus;
import ai.nexusone.enums.BoardroomPriority;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="boardroom_briefings", indexes={
 @Index(name="idx_boardroom_status", columnList="status"),
 @Index(name="idx_boardroom_priority", columnList="priority"),
 @Index(name="idx_boardroom_created", columnList="createdAt")})
public class BoardroomBriefing {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false,length=3000) private String executiveSummary;
 @Column(nullable=false,length=3000) private String strategicAgenda;
 @Column(nullable=false,length=3000) private String boardRecommendation;
 @Column(nullable=false) private Double expectedRoi;
 @Column(nullable=false) private Double strategicAlignment;
 @Column(nullable=false) private Double riskExposure;
 @Column(nullable=false) private Double boardroomScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private BoardroomPriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private BoardroomBriefingStatus status=BoardroomBriefingStatus.DRAFTED;
 @Column(nullable=false) private String createdBy;
 private String reviewedBy; private LocalDateTime reviewedAt;
 private String decidedBy; private LocalDateTime decidedAt;
 private String executedBy; private LocalDateTime executedAt;
 private LocalDateTime createdAt; private LocalDateTime updatedAt;
 @PrePersist void prePersist(){var now=LocalDateTime.now();createdAt=now;updatedAt=now;if(status==null)status=BoardroomBriefingStatus.DRAFTED;}
 @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getExecutiveSummary(){return executiveSummary;} public void setExecutiveSummary(String v){executiveSummary=v;}
 public String getStrategicAgenda(){return strategicAgenda;} public void setStrategicAgenda(String v){strategicAgenda=v;}
 public String getBoardRecommendation(){return boardRecommendation;} public void setBoardRecommendation(String v){boardRecommendation=v;}
 public Double getExpectedRoi(){return expectedRoi;} public void setExpectedRoi(Double v){expectedRoi=v;}
 public Double getStrategicAlignment(){return strategicAlignment;} public void setStrategicAlignment(Double v){strategicAlignment=v;}
 public Double getRiskExposure(){return riskExposure;} public void setRiskExposure(Double v){riskExposure=v;}
 public Double getBoardroomScore(){return boardroomScore;} public void setBoardroomScore(Double v){boardroomScore=v;}
 public BoardroomPriority getPriority(){return priority;} public void setPriority(BoardroomPriority v){priority=v;}
 public BoardroomBriefingStatus getStatus(){return status;} public void setStatus(BoardroomBriefingStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
 public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public String getExecutedBy(){return executedBy;} public void setExecutedBy(String v){executedBy=v;}
 public LocalDateTime getExecutedAt(){return executedAt;} public void setExecutedAt(LocalDateTime v){executedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
