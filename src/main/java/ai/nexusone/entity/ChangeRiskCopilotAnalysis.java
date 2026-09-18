package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "change_risk_copilot_analysis",
       indexes = @Index(name = "idx_change_risk_copilot_change_time", columnList = "change_id,created_at"))
public class ChangeRiskCopilotAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "change_id", nullable = false)
    private Long changeId;

    @Column(name = "query_text", nullable = false, length = 1000)
    private String queryText;

    @Column(nullable = false, length = 5000)
    private String answer;

    @Column(nullable = false)
    private double confidence;

    @Column(name = "risk_level", nullable = false, length = 30)
    private String riskLevel;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected ChangeRiskCopilotAnalysis() {}

    public ChangeRiskCopilotAnalysis(Long changeId, String queryText, String answer,
                                     double confidence, String riskLevel) {
        this.changeId = changeId;
        this.queryText = queryText;
        this.answer = answer;
        this.confidence = confidence;
        this.riskLevel = riskLevel;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public Long getChangeId() { return changeId; }
    public String getQueryText() { return queryText; }
    public String getAnswer() { return answer; }
    public double getConfidence() { return confidence; }
    public String getRiskLevel() { return riskLevel; }
    public Instant getCreatedAt() { return createdAt; }
}
