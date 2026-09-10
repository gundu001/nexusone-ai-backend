package ai.nexusone.dto;

import java.util.ArrayList;
import java.util.List;

public class ReleaseImpactRequest {
    private String repositoryName;
    private List<String> changedFiles = new ArrayList<>();

    public ReleaseImpactRequest() {}
    public String getRepositoryName() { return repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
    public List<String> getChangedFiles() { return changedFiles; }
    public void setChangedFiles(List<String> changedFiles) {
        this.changedFiles = changedFiles == null ? new ArrayList<>() : changedFiles;
    }
}
