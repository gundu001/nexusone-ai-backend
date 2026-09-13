package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ChangeImpactResponse(
        Long executionId,
        String repositoryName,
        double impactScore,
        String impactLevel,
        boolean approvalRecommended,
        List<String> impactSignals,
        List<String> recommendedControls,
        LocalDateTime generatedAt
) { }
