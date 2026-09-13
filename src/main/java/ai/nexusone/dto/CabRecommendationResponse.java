package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CabRecommendationResponse(
        Long executionId,
        String recommendation,
        String priority,
        boolean cabReviewRequired,
        String summary,
        List<String> reviewTopics,
        List<String> requiredEvidence,
        LocalDateTime generatedAt
) { }
