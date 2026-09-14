package ai.nexusone.dto.devopscommand;

public class DevOpsRecommendationResponse {
    private final Long recommendationId;
    private final String severity;
    private final String recommendation;
    private final String suggestedCommand;
    private final int confidenceScore;

    public DevOpsRecommendationResponse(Long recommendationId, String severity, String recommendation, String suggestedCommand, int confidenceScore) {
        this.recommendationId = recommendationId;
        this.severity = severity;
        this.recommendation = recommendation;
        this.suggestedCommand = suggestedCommand;
        this.confidenceScore = confidenceScore;
    }

    public Long getRecommendationId() { return recommendationId; }
    public String getSeverity() { return severity; }
    public String getRecommendation() { return recommendation; }
    public String getSuggestedCommand() { return suggestedCommand; }
    public int getConfidenceScore() { return confidenceScore; }
}
