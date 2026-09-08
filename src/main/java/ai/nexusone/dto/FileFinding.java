package ai.nexusone.dto;

public class FileFinding {
    private String severity;
    private String file;
    private String rule;
    private String issue;
    private String recommendation;

    public FileFinding() {}

    public FileFinding(String severity, String file, String rule,
                       String issue, String recommendation) {
        this.severity = severity;
        this.file = file;
        this.rule = rule;
        this.issue = issue;
        this.recommendation = recommendation;
    }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public String getFile() { return file; }
    public void setFile(String file) { this.file = file; }
    public String getRule() { return rule; }
    public void setRule(String rule) { this.rule = rule; }
    public String getIssue() { return issue; }
    public void setIssue(String issue) { this.issue = issue; }
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
}
