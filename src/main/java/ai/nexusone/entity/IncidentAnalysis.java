package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="incident_analysis") public class IncidentAnalysis {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private String incidentId; private String queryText; @Column(length=5000) private String answer; private Instant createdAt;
 protected IncidentAnalysis(){} public IncidentAnalysis(String incidentId,String queryText,String answer){this.incidentId=incidentId;this.queryText=queryText;this.answer=answer;this.createdAt=Instant.now();}
 public Long getId(){return id;} public String getIncidentId(){return incidentId;} public String getQueryText(){return queryText;} public String getAnswer(){return answer;} public Instant getCreatedAt(){return createdAt;}
}
