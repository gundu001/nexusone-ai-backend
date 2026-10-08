package ai.nexusone.dto;

import ai.nexusone.enums.AutonomousEnterpriseMAIntelligencePriority;
import ai.nexusone.enums.AutonomousEnterpriseMAIntelligenceStatus;
import java.time.LocalDateTime;

public record AutonomousEnterpriseMAIntelligenceResponse(
        Long id,
        String title,
        String maIntelligenceVision,
        String acquisitionStrategy,
        String mergerSynergyAnalysis,
        String targetCompanyAssessment,
        String financialDueDiligence,
        String operationalDueDiligence,
        String culturalIntegrationStrategy,
        String executiveMADecision,
        Double synergyScore,
        Double financialStrengthScore,
        Double strategicFitScore,
        Double integrationReadinessScore,
        Double riskAssessmentScore,
        Double targetQualityScore,
        Double valueCreationScore,
        Double executionConfidenceScore,
        Double enterpriseMAIntelligenceScore,
        AutonomousEnterpriseMAIntelligencePriority priority,
        AutonomousEnterpriseMAIntelligenceStatus status,
        String createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
