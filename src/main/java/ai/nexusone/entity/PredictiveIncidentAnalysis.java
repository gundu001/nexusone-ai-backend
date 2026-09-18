package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "predictive_incident_analysis", indexes = {
        @Index(name = "idx_predictive_application", columnList = "application"),
        @Index(name = "idx_predictive_created_at", columnList = "created_at")
})
public class PredictiveIncidentAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String application;

    @Column(name = "query_text", nullable = false, length = 1000)
    private String queryText;

    @Column(name = "predicted_incident", nullable = false, length = 500)
    private String predictedIncident;

    @Column(nullable = false, length = 2000)
    private String answer;

    @Column(name = "risk_score", nullable = false)
    private double riskScore;

    @Column(name = "risk_level", nullable = false, length = 30)
    private String riskLevel;

    @Column(name = "prediction_window", nullable = false, length = 100)
    private String predictionWindow;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected PredictiveIncidentAnalysis() {}

    public PredictiveIncidentAnalysis(String application, String queryText, String predictedIncident,
                                      String answer, double riskScore, String riskLevel,
                                      String predictionWindow) {
        this.application = application;
        this.queryText = queryText;
        this.predictedIncident = predictedIncident;
        this.answer = answer;
        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
        this.predictionWindow = predictionWindow;
    }

    @PrePersist
    void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getApplication() { return application; }
    public String getQueryText() { return queryText; }
    public String getPredictedIncident() { return predictedIncident; }
    public String getAnswer() { return answer; }
    public double getRiskScore() { return riskScore; }
    public String getRiskLevel() { return riskLevel; }
    public String getPredictionWindow() { return predictionWindow; }
    public Instant getCreatedAt() { return createdAt; }
}
