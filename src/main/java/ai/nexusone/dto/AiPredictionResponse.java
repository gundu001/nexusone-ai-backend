package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AiPredictionResponse(
        String predictionCode,
        String category,
        String severity,
        String title,
        String narrative,
        List<String> recommendedActions,
        LocalDateTime generatedAt
) { }
