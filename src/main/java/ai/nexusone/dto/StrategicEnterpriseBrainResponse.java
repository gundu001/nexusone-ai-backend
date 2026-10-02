package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record StrategicEnterpriseBrainResponse(
        Long id, String title,
        String strategicVision,
        String enterpriseContext,
        String decisionIntelligence,
        String scenarioPlanning,
        String riskAnticipation,
        String executionAlignment,
        String learningAdaptation,
        String innovationIntelligence,
        String valueOrchestration,
        String strategicRecommendations,
        Double strategicVisionScore,
        Double enterpriseContextScore,
        Double decisionIntelligenceScore,
        Double scenarioPlanningScore,
        Double riskAnticipationScore,
        Double executionAlignmentScore,
        Double learningAdaptationScore,
        Double innovationIntelligenceScore,
        Double valueOrchestrationScore,
        Double strategicEnterpriseBrainScore,
        StrategicEnterpriseBrainPriority priority,
        StrategicEnterpriseBrainStatus status,
        String createdBy, String reviewedBy, LocalDateTime reviewedAt,
        String decidedBy, LocalDateTime decidedAt,
        String publishedBy, LocalDateTime publishedAt,
        LocalDateTime createdAt, LocalDateTime updatedAt
) {}
