package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class RiskAnalysisRequest {

    @NotBlank
    private String applicationName;

    @NotBlank
    private String environment;

    private String repositoryName;

    @PositiveOrZero
    private int criticalFindings;

    @PositiveOrZero
    private int highFindings;

    @PositiveOrZero
    private int mediumFindings;

    @PositiveOrZero
    private int lowFindings;

    @PositiveOrZero
    private int testsFailed;

    private boolean buildSuccessful;

    private boolean dockerfilePresent;

    private boolean healthcheckPresent;

    public RiskAnalysisRequest() {
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public int getCriticalFindings() {
        return criticalFindings;
    }

    public void setCriticalFindings(int criticalFindings) {
        this.criticalFindings = criticalFindings;
    }

    public int getHighFindings() {
        return highFindings;
    }

    public void setHighFindings(int highFindings) {
        this.highFindings = highFindings;
    }

    public int getMediumFindings() {
        return mediumFindings;
    }

    public void setMediumFindings(int mediumFindings) {
        this.mediumFindings = mediumFindings;
    }

    public int getLowFindings() {
        return lowFindings;
    }

    public void setLowFindings(int lowFindings) {
        this.lowFindings = lowFindings;
    }

    public int getTestsFailed() {
        return testsFailed;
    }

    public void setTestsFailed(int testsFailed) {
        this.testsFailed = testsFailed;
    }

    public boolean isBuildSuccessful() {
        return buildSuccessful;
    }

    public void setBuildSuccessful(boolean buildSuccessful) {
        this.buildSuccessful = buildSuccessful;
    }

    public boolean isDockerfilePresent() {
        return dockerfilePresent;
    }

    public void setDockerfilePresent(boolean dockerfilePresent) {
        this.dockerfilePresent = dockerfilePresent;
    }

    public boolean isHealthcheckPresent() {
        return healthcheckPresent;
    }

    public void setHealthcheckPresent(boolean healthcheckPresent) {
        this.healthcheckPresent = healthcheckPresent;
    }
}