package ai.nexusone.dto;

public class RiskRow {

    private String repositoryName;
    private Integer riskScore;
    private String severity;
    private String findings;

    public RiskRow(
            String repositoryName,
            Integer riskScore,
            String severity,
            String findings) {

        this.repositoryName = repositoryName;
        this.riskScore = riskScore;
        this.severity = severity;
        this.findings = findings;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public String getSeverity() {
        return severity;
    }

    public String getFindings() {
        return findings;
    }
}