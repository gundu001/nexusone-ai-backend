package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "advisor_results", indexes = {
        @Index(name = "idx_advisor_repository_generated", columnList = "repository_name,generated_at")
})
public class AdvisorResultEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name="repository_name", nullable=false, length=255) private String repositoryName;
    @Column(name="risk_score", nullable=false) private Integer riskScore;
    @Column(nullable=false, length=30) private String severity;
    @Column(name="confidence_score", nullable=false) private Integer confidenceScore;
    @Column(name="deployment_decision", nullable=false, length=50) private String deploymentDecision;
    @Lob @Column(columnDefinition="LONGTEXT") private String recommendations;
    @Lob @Column(columnDefinition="LONGTEXT") private String strengths;
    @Lob @Column(columnDefinition="LONGTEXT") private String risks;
    @Column(name="generated_at", nullable=false) private LocalDateTime generatedAt;
    public AdvisorResultEntity() {}
    @PrePersist public void beforeInsert(){ if(generatedAt==null) generatedAt=LocalDateTime.now(); }
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getRepositoryName(){return repositoryName;} public void setRepositoryName(String v){repositoryName=v;}
    public Integer getRiskScore(){return riskScore;} public void setRiskScore(Integer v){riskScore=v;}
    public String getSeverity(){return severity;} public void setSeverity(String v){severity=v;}
    public Integer getConfidenceScore(){return confidenceScore;} public void setConfidenceScore(Integer v){confidenceScore=v;}
    public String getDeploymentDecision(){return deploymentDecision;} public void setDeploymentDecision(String v){deploymentDecision=v;}
    public String getRecommendations(){return recommendations;} public void setRecommendations(String v){recommendations=v;}
    public String getStrengths(){return strengths;} public void setStrengths(String v){strengths=v;}
    public String getRisks(){return risks;} public void setRisks(String v){risks=v;}
    public LocalDateTime getGeneratedAt(){return generatedAt;} public void setGeneratedAt(LocalDateTime v){generatedAt=v;}
}
