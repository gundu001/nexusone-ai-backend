package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ReleaseSuccessPredictionResponse(
        Long executionId,
        String repositoryName,
        String currentStatus,
        double successProbability,
        double failureProbability,
        double confidenceScore,
        String prediction,
        List<String> contributingSignals,
        LocalDateTime generatedAt
) { }
