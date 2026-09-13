package ai.nexusone.dto;

public record ExecutiveInsightResponse(
        String code,
        String category,
        String severity,
        String title,
        String description,
        String recommendedAction
) { }
