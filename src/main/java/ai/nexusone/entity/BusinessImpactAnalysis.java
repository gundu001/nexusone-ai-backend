package ai.nexusone.entity;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="business_impact_analyses")
public class BusinessImpactAnalysis {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String applicationName; @Column(nullable=false) private String incidentType;
 private int affectedServices; private long estimatedUsersAffected; private double estimatedFinancialImpact;
 @Column(nullable=false) private String severity; @Column(length=1500) private String continuityRecommendation;
 @Column(nullable=false) private LocalDateTime analyzedAt;
 @PrePersist void init(){if(analyzedAt==null)analyzedAt=LocalDateTime.now();}
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;} public String getIncidentType(){return incidentType;} public void setIncidentType(String v){incidentType=v;} public int getAffectedServices(){return affectedServices;} public void setAffectedServices(int v){affectedServices=v;} public long getEstimatedUsersAffected(){return estimatedUsersAffected;} public void setEstimatedUsersAffected(long v){estimatedUsersAffected=v;} public double getEstimatedFinancialImpact(){return estimatedFinancialImpact;} public void setEstimatedFinancialImpact(double v){estimatedFinancialImpact=v;} public String getSeverity(){return severity;} public void setSeverity(String v){severity=v;} public String getContinuityRecommendation(){return continuityRecommendation;} public void setContinuityRecommendation(String v){continuityRecommendation=v;} public LocalDateTime getAnalyzedAt(){return analyzedAt;} public void setAnalyzedAt(LocalDateTime v){analyzedAt=v;}
}
