package ai.nexusone.entity;

import ai.nexusone.enums.DeploymentResult;
import ai.nexusone.enums.DeploymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deployment_executions")
public class DeploymentExecutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "repository_name", nullable = false, length = 150)
    private String repositoryName;

    @Column(name = "job_name", nullable = false, length = 150)
    private String jobName;

    @Column(name = "queue_id")
    private Long queueId;

    @Column(name = "build_number")
    private Integer buildNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private DeploymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "deployment_result", nullable = false, length = 30)
    private DeploymentResult deploymentResult;

    @Column(name = "triggered_by", nullable = false, length = 100)
    private String triggeredBy;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "jenkins_url", length = 500)
    private String jenkinsUrl;

    @Column(name = "jenkins_message", length = 1000)
    private String jenkinsMessage;

    // ==========================
    // Getters and Setters
    // ==========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    public Integer getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(Integer buildNumber) {
        this.buildNumber = buildNumber;
    }

    public DeploymentStatus getStatus() {
        return status;
    }

    public void setStatus(DeploymentStatus status) {
        this.status = status;
    }

    public DeploymentResult getDeploymentResult() {
        return deploymentResult;
    }

    public void setDeploymentResult(DeploymentResult deploymentResult) {
        this.deploymentResult = deploymentResult;
    }

    public String getTriggeredBy() {
        return triggeredBy;
    }

    public void setTriggeredBy(String triggeredBy) {
        this.triggeredBy = triggeredBy;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getJenkinsUrl() {
        return jenkinsUrl;
    }

    public void setJenkinsUrl(String jenkinsUrl) {
        this.jenkinsUrl = jenkinsUrl;
    }

    public String getJenkinsMessage() {
        return jenkinsMessage;
    }

    public void setJenkinsMessage(String jenkinsMessage) {
        this.jenkinsMessage = jenkinsMessage;
    }
}