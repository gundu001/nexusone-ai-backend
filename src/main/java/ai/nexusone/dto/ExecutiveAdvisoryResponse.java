package ai.nexusone.dto;

import ai.nexusone.enums.ExecutiveAdvisoryStatus;
import ai.nexusone.enums.ExecutivePriority;
import java.time.LocalDateTime;

public record ExecutiveAdvisoryResponse(
        Long id,
        String title,
        String strategicObjective,
        String businessChallenge,
        String executiveRecommendation,
        Double investmentValue,
        Double strategicAlignment,
        Double riskScore,
        Double advisoryScore,
        ExecutivePriority priority,
        ExecutiveAdvisoryStatus status,
        String createdBy,
        String reviewedBy,
        LocalDateTime reviewedAt,
        String decidedBy,
        LocalDateTime decidedAt,
        String executedBy,
        LocalDateTime executedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
