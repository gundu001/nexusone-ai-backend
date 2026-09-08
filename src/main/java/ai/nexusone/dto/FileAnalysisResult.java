package ai.nexusone.dto;

import java.util.ArrayList;
import java.util.List;

public class FileAnalysisResult {
    private String repositoryName;
    private String repositoryPath;
    private int filesAnalyzed;
    private int totalFindings;
    private int criticalFindings;
    private int highFindings;
    private int mediumFindings;
    private int lowFindings;
    private int riskScore;
    private String overallSeverity;
    private List<FileFinding> findings = new ArrayList<>();

    public FileAnalysisResult() {}

    public String getRepositoryName() { return repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
    public String getRepositoryPath() { return repositoryPath; }
    public void setRepositoryPath(String repositoryPath) { this.repositoryPath = repositoryPath; }
    public int getFilesAnalyzed() { return filesAnalyzed; }
    public void setFilesAnalyzed(int filesAnalyzed) { this.filesAnalyzed = filesAnalyzed; }
    public int getTotalFindings() { return totalFindings; }
    public void setTotalFindings(int totalFindings) { this.totalFindings = totalFindings; }
    public int getCriticalFindings() { return criticalFindings; }
    public void setCriticalFindings(int criticalFindings) { this.criticalFindings = criticalFindings; }
    public int getHighFindings() { return highFindings; }
    public void setHighFindings(int highFindings) { this.highFindings = highFindings; }
    public int getMediumFindings() { return mediumFindings; }
    public void setMediumFindings(int mediumFindings) { this.mediumFindings = mediumFindings; }
    public int getLowFindings() { return lowFindings; }
    public void setLowFindings(int lowFindings) { this.lowFindings = lowFindings; }
    public int getRiskScore() { return riskScore; }
    public void setRiskScore(int riskScore) { this.riskScore = riskScore; }
    public String getOverallSeverity() { return overallSeverity; }
    public void setOverallSeverity(String overallSeverity) { this.overallSeverity = overallSeverity; }
    public List<FileFinding> getFindings() { return findings; }
    public void setFindings(List<FileFinding> findings) { this.findings = findings; }
}
