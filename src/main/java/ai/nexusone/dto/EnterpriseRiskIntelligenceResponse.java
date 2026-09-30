package ai.nexusone.dto;

import ai.nexusone.enums.EnterpriseRiskPriority;
import ai.nexusone.enums.EnterpriseRiskStatus;
import java.time.LocalDateTime;

public record EnterpriseRiskIntelligenceResponse(
        Long id,
        String title,
        String enterpriseRiskOutlook,
        String operationalRiskAnalysis,
        String financialRiskAnalysis,
        String cyberSecurityRiskAnalysis,
        String regulatoryComplianceRiskAnalysis,
        String strategicRiskAnalysis,
        String thirdPartyRiskAnalysis,
        String businessContinuityAnalysis,
        String riskMitigationRecommendations,
        Double operationalRiskScore,
        Double financialRiskScore,
        Double cyberSecurityRiskScore,
        Double regulatoryComplianceRiskScore,
        Double strategicRiskScore,
        Double thirdPartyRiskScore,
        Double businessContinuityRiskScore,
        Double enterpriseRiskIntelligenceScore,
        EnterpriseRiskPriority priority,
        EnterpriseRiskStatus status,
        String createdBy,
        String reviewedBy,
        LocalDateTime reviewedAt,
        String decidedBy,
        LocalDateTime decidedAt,
        String publishedBy,
        LocalDateTime publishedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
