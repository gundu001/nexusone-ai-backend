package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "root_cause_analysis", indexes = {
        @Index(name = "idx_rca_incident_created", columnList = "incidentId,createdAt")
})
public class RootCauseAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String incidentId;
    @Column(length = 2000)
    private String queryText;
    @Column(length = 5000)
    private String rootCause;
    @Column(length = 5000)
    private String answer;
    private double confidence;
    private String pattern;
    private Instant createdAt;

    protected RootCauseAnalysis() {}

    public RootCauseAnalysis(String incidentId, String queryText, String rootCause,
                             String answer, double confidence, String pattern) {
        this.incidentId = incidentId;
        this.queryText = queryText;
        this.rootCause = rootCause;
        this.answer = answer;
        this.confidence = confidence;
        this.pattern = pattern;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getIncidentId() { return incidentId; }
    public String getQueryText() { return queryText; }
    public String getRootCause() { return rootCause; }
    public String getAnswer() { return answer; }
    public double getConfidence() { return confidence; }
    public String getPattern() { return pattern; }
    public Instant getCreatedAt() { return createdAt; }
}
