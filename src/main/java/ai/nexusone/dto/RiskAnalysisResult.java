package ai.nexusone.dto;

import java.util.ArrayList;
import java.util.List;

public class RiskAnalysisResult {

    private String repositoryName;

    private Integer riskScore;

    private String severity;

    private List<String> findings =
            new ArrayList<>();

    public RiskAnalysisResult() {
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public List<String> getFindings() {
        return findings;
    }

    public void setFindings(List<String> findings) {
        this.findings = findings;
    }
}