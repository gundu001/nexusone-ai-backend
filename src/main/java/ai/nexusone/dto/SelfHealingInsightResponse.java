package ai.nexusone.dto;

public record SelfHealingInsightResponse(
        String diagnosisCode,
        String diagnosis,
        String riskLevel,
        long occurrences,
        long healingRecommendedCount,
        long approvalRequiredCount,
        double recommendationRate,
        double averageConfidenceScore
) { }
