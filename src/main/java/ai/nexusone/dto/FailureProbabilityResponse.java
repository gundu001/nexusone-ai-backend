package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record FailureProbabilityResponse(
        Long executionId,
        double failureProbability,
        String probabilityBand,
        String riskLevel,
        List<String> riskFactors,
        List<String> mitigations,
        LocalDateTime generatedAt
) { }
