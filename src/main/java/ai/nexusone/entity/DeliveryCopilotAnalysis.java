package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "delivery_copilot_analysis", indexes = {
        @Index(name = "idx_delivery_analysis_repository_created", columnList = "repository_name,created_at"),
        @Index(name = "idx_delivery_analysis_created", columnList = "created_at")
})
public class DeliveryCopilotAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "repository_name", length = 150)
    private String repositoryName;

    @Column(name = "query_text", nullable = false, length = 1000)
    private String queryText;

    @Column(nullable = false, length = 5000)
    private String answer;

    @Column(nullable = false)
    private double confidence;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected DeliveryCopilotAnalysis() {}

    public DeliveryCopilotAnalysis(String repositoryName, String queryText, String answer, double confidence) {
        this.repositoryName = repositoryName;
        this.queryText = queryText;
        this.answer = answer;
        this.confidence = confidence;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getRepositoryName() { return repositoryName; }
    public String getQueryText() { return queryText; }
    public String getAnswer() { return answer; }
    public double getConfidence() { return confidence; }
    public Instant getCreatedAt() { return createdAt; }
}
