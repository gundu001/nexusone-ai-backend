package ai.nexusone.dto;

public record RcaInsightResponse(
        String rootCauseCode,
        String rootCause,
        String severity,
        long occurrences,
        double percentage,
        double averageConfidenceScore
) { }
