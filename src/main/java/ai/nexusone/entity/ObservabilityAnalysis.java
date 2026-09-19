package ai.nexusone.entity;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="observability_analysis",indexes={@Index(name="idx_obs_service",columnList="service_name"),@Index(name="idx_obs_created",columnList="created_at")})
public class ObservabilityAnalysis {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="service_name",nullable=false,length=100) private String serviceName;
 @Column(name="query_text",nullable=false,length=1000) private String queryText;
 @Column(nullable=false,length=1000) private String observation;
 @Column(nullable=false,length=1000) private String impact;
 @Column(nullable=false,length=1500) private String recommendation;
 @Column(nullable=false) private double confidence;
 @Column(nullable=false,length=30) private String severity;
 @Column(name="created_at",nullable=false,updatable=false) private Instant createdAt;
 protected ObservabilityAnalysis(){}
 public ObservabilityAnalysis(String s,String q,String o,String i,String r,double c,String v){serviceName=s;queryText=q;observation=o;impact=i;recommendation=r;confidence=c;severity=v;}
 @PrePersist void pre(){if(createdAt==null)createdAt=Instant.now();}
 public Long getId(){return id;} public String getServiceName(){return serviceName;} public String getQueryText(){return queryText;} public String getObservation(){return observation;} public String getImpact(){return impact;} public String getRecommendation(){return recommendation;} public double getConfidence(){return confidence;} public String getSeverity(){return severity;} public Instant getCreatedAt(){return createdAt;}
}
