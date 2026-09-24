package ai.nexusone.dto;

import ai.nexusone.enums.CeoIntelligenceStatus;
import ai.nexusone.enums.CeoPriority;
import java.time.LocalDateTime;

public record CeoIntelligenceResponse(
        Long id,
        String title,
        String executiveSummary,
        String strategicPriority,
        String ceoRecommendation,
        Double enterpriseHealthScore,
        Double transformationScore,
        Double financialScore,
        Double operationalScore,
        Double riskExposure,
        Double innovationScore,
        Double overallCeoScore,
        CeoPriority priority,
        CeoIntelligenceStatus status,
        String createdBy,
        String reviewedBy,
        LocalDateTime reviewedAt,
        String decidedBy,
        LocalDateTime decidedAt,
        String executedBy,
        LocalDateTime executedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
