package ai.nexusone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "deployment_readiness_results", indexes = {
        @Index(name = "idx_readiness_repo_time", columnList = "repository_name,generated_at")
})
public class DeploymentReadinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "repository_name", nullable = false, length = 255)
    private String repositoryName;

    @Column(name = "repository_path", nullable = false, length = 1000)
    private String repositoryPath;

    @Column(name = "readiness_score", nullable = false)
    private Integer readinessScore;

    @Column(name = "deployment_decision", nullable = false, length = 50)
    private String deploymentDecision;

    @Lob
    @Column(name = "strengths", columnDefinition = "LONGTEXT")
    private String strengths;

    @Lob
    @Column(name = "warnings", columnDefinition = "LONGTEXT")
    private String warnings;

    @Lob
    @Column(name = "recommendations", columnDefinition = "LONGTEXT")
    private String recommendations;

    @Column(name = "generated_at", nullable = false)
    private LocalDateTime generatedAt;

    public DeploymentReadinessEntity() {
    }

    @PrePersist
    public void prePersist() {
        if (generatedAt == null) generatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRepositoryName() { return repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
    public String getRepositoryPath() { return repositoryPath; }
    public void setRepositoryPath(String repositoryPath) { this.repositoryPath = repositoryPath; }
    public Integer getReadinessScore() { return readinessScore; }
    public void setReadinessScore(Integer readinessScore) { this.readinessScore = readinessScore; }
    public String getDeploymentDecision() { return deploymentDecision; }
    public void setDeploymentDecision(String deploymentDecision) { this.deploymentDecision = deploymentDecision; }
    public String getStrengths() { return strengths; }
    public void setStrengths(String strengths) { this.strengths = strengths; }
    public String getWarnings() { return warnings; }
    public void setWarnings(String warnings) { this.warnings = warnings; }
    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}
