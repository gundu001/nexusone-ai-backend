package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DeploymentExecutionRequest {

    @NotBlank(message = "Repository name is required")
    @Size(max = 150, message = "Repository name must not exceed 150 characters")
    private String repositoryName;

    @NotBlank(message = "Jenkins job name is required")
    @Size(max = 150, message = "Job name must not exceed 150 characters")
    private String jobName;

    @NotBlank(message = "Triggered by is required")
    @Size(max = 100, message = "Triggered by must not exceed 100 characters")
    private String triggeredBy;

    public String getRepositoryName() { return repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }
    public String getTriggeredBy() { return triggeredBy; }
    public void setTriggeredBy(String triggeredBy) { this.triggeredBy = triggeredBy; }
}
