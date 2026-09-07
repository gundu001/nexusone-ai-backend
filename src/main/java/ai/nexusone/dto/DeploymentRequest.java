package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public class DeploymentRequest {
    @NotBlank(message = "Repository name is required.")
    private String repositoryName;

    @NotBlank(message = "Environment is required.")
    private String environment;

    public DeploymentRequest() {
    }

    public String getRepositoryName() { return repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }
}
