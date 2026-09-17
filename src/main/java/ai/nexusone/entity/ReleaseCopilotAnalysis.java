package ai.nexusone.entity;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
@Table(name="release_copilot_analysis",indexes=@Index(name="idx_release_copilot_release_time",columnList="release_id,created_at"))
public class ReleaseCopilotAnalysis {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="release_id",nullable=false) private Long releaseId;
 @Column(name="query_text",nullable=false,length=1000) private String queryText;
 @Column(nullable=false,length=5000) private String answer;
 @Column(nullable=false) private double confidence;
 @Column(name="created_at",nullable=false) private Instant createdAt;
 protected ReleaseCopilotAnalysis() {}
 public ReleaseCopilotAnalysis(Long releaseId,String queryText,String answer,double confidence){this.releaseId=releaseId;this.queryText=queryText;this.answer=answer;this.confidence=confidence;this.createdAt=Instant.now();}
 public Long getId(){return id;} public Long getReleaseId(){return releaseId;} public String getQueryText(){return queryText;} public String getAnswer(){return answer;} public double getConfidence(){return confidence;} public Instant getCreatedAt(){return createdAt;}
}
