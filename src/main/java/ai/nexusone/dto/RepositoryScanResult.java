package ai.nexusone.dto;

import java.util.ArrayList;
import java.util.List;

public class RepositoryScanResult {

    private String repositoryName;
    private String repositoryPath;
    private String detectedTechnology;

    private boolean pomFilePresent;
    private boolean gradleFilePresent;
    private boolean dockerfilePresent;
    private boolean applicationConfigPresent;
    private boolean dockerComposePresent;
    private boolean kubernetesFilesPresent;
    private boolean readmePresent;

    private int javaFileCount;
    private int yamlFileCount;

    private List<String> detectedFiles = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();

    public RepositoryScanResult() {
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public String getDetectedTechnology() {
        return detectedTechnology;
    }

    public void setDetectedTechnology(String detectedTechnology) {
        this.detectedTechnology = detectedTechnology;
    }

    public boolean isPomFilePresent() {
        return pomFilePresent;
    }

    public void setPomFilePresent(boolean pomFilePresent) {
        this.pomFilePresent = pomFilePresent;
    }

    public boolean isGradleFilePresent() {
        return gradleFilePresent;
    }

    public void setGradleFilePresent(boolean gradleFilePresent) {
        this.gradleFilePresent = gradleFilePresent;
    }

    public boolean isDockerfilePresent() {
        return dockerfilePresent;
    }

    public void setDockerfilePresent(boolean dockerfilePresent) {
        this.dockerfilePresent = dockerfilePresent;
    }

    public boolean isApplicationConfigPresent() {
        return applicationConfigPresent;
    }

    public void setApplicationConfigPresent(boolean applicationConfigPresent) {
        this.applicationConfigPresent = applicationConfigPresent;
    }

    public boolean isDockerComposePresent() {
        return dockerComposePresent;
    }

    public void setDockerComposePresent(boolean dockerComposePresent) {
        this.dockerComposePresent = dockerComposePresent;
    }

    public boolean isKubernetesFilesPresent() {
        return kubernetesFilesPresent;
    }

    public void setKubernetesFilesPresent(boolean kubernetesFilesPresent) {
        this.kubernetesFilesPresent = kubernetesFilesPresent;
    }

    public boolean isReadmePresent() {
        return readmePresent;
    }

    public void setReadmePresent(boolean readmePresent) {
        this.readmePresent = readmePresent;
    }

    public int getJavaFileCount() {
        return javaFileCount;
    }

    public void setJavaFileCount(int javaFileCount) {
        this.javaFileCount = javaFileCount;
    }

    public int getYamlFileCount() {
        return yamlFileCount;
    }

    public void setYamlFileCount(int yamlFileCount) {
        this.yamlFileCount = yamlFileCount;
    }

    public List<String> getDetectedFiles() {
        return detectedFiles;
    }

    public void setDetectedFiles(List<String> detectedFiles) {
        this.detectedFiles = detectedFiles;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
