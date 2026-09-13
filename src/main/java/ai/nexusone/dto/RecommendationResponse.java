package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record RecommendationResponse(
        Long executionId,
        String recommendationCode,
        String category,
        String severity,
        String title,
        String summary,
        List<String> nextSteps,
        LocalDateTime generatedAt
) { }
