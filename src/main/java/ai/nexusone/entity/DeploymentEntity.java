package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "deployments")
public class DeploymentEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(nullable = false)
 private String repositoryName;

 @Column(nullable = false)
 private String environment;

 @Column(nullable = false)
 private String status;

 private Integer riskScore;
 private String riskSeverity;
 private String recommendation;
 private LocalDateTime requestedAt;
 private LocalDateTime startedAt;
 private LocalDateTime completedAt;

 @Column(length = 2000)
 private String statusMessage;

 public DeploymentEntity() {
 }

 @PrePersist
 public void beforeInsert() {
  if (requestedAt == null) {
   requestedAt = LocalDateTime.now();
  }
  if (status == null || status.isBlank()) {
   status = "PENDING";
  }
 }

 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }
 public String getRepositoryName() { return repositoryName; }
 public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
 public String getEnvironment() { return environment; }
 public void setEnvironment(String environment) { this.environment = environment; }
 public String getStatus() { return status; }
 public void setStatus(String status) { this.status = status; }
 public Integer getRiskScore() { return riskScore; }
 public void setRiskScore(Integer riskScore) { this.riskScore = riskScore; }
 public String getRiskSeverity() { return riskSeverity; }
 public void setRiskSeverity(String riskSeverity) { this.riskSeverity = riskSeverity; }
 public String getRecommendation() { return recommendation; }
 public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
 public LocalDateTime getRequestedAt() { return requestedAt; }
 public void setRequestedAt(LocalDateTime requestedAt) { this.requestedAt = requestedAt; }
 public LocalDateTime getStartedAt() { return startedAt; }
 public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
 public LocalDateTime getCompletedAt() { return completedAt; }
 public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
 public String getStatusMessage() { return statusMessage; }
 public void setStatusMessage(String statusMessage) { this.statusMessage = statusMessage; }
}
