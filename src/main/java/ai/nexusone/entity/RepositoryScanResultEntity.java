package ai.nexusone.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "repository_scan_results")
public class RepositoryScanResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String repositoryName;

    private String technology;

    private Integer javaFileCount;

    private Boolean pomFilePresent;

    private Boolean dockerfilePresent;

    private Boolean applicationConfigPresent;

    private Boolean readmePresent;

    private String scanStatus;

    private LocalDateTime scanTime;

    public RepositoryScanResultEntity() {
    }

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

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public Integer getJavaFileCount() {
        return javaFileCount;
    }

    public void setJavaFileCount(Integer javaFileCount) {
        this.javaFileCount = javaFileCount;
    }

    public Boolean getPomFilePresent() {
        return pomFilePresent;
    }

    public void setPomFilePresent(Boolean pomFilePresent) {
        this.pomFilePresent = pomFilePresent;
    }

    public Boolean getDockerfilePresent() {
        return dockerfilePresent;
    }

    public void setDockerfilePresent(Boolean dockerfilePresent) {
        this.dockerfilePresent = dockerfilePresent;
    }

    public Boolean getApplicationConfigPresent() {
        return applicationConfigPresent;
    }

    public void setApplicationConfigPresent(Boolean applicationConfigPresent) {
        this.applicationConfigPresent = applicationConfigPresent;
    }

    public Boolean getReadmePresent() {
        return readmePresent;
    }

    public void setReadmePresent(Boolean readmePresent) {
        this.readmePresent = readmePresent;
    }

    public String getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(String scanStatus) {
        this.scanStatus = scanStatus;
    }

    public LocalDateTime getScanTime() {
        return scanTime;
    }

    public void setScanTime(LocalDateTime scanTime) {
        this.scanTime = scanTime;
    }
}