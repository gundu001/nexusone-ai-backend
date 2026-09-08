package ai.nexusone.dto;

public class RiskDistribution {

    private String severity;

    private long count;

    public RiskDistribution() {
    }

    public RiskDistribution(
            String severity,
            long count) {

        this.severity = severity;
        this.count = count;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(
            String severity) {

        this.severity = severity;
    }

    public long getCount() {
        return count;
    }

    public void setCount(
            long count) {

        this.count = count;
    }
}