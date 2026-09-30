package ai.nexusone.dto;

import ai.nexusone.enums.EnterpriseRiskPriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnterpriseRiskIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String enterpriseRiskOutlook,
        @NotBlank String operationalRiskAnalysis,
        @NotBlank String financialRiskAnalysis,
        @NotBlank String cyberSecurityRiskAnalysis,
        @NotBlank String regulatoryComplianceRiskAnalysis,
        @NotBlank String strategicRiskAnalysis,
        @NotBlank String thirdPartyRiskAnalysis,
        @NotBlank String businessContinuityAnalysis,
        @NotBlank String riskMitigationRecommendations,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double operationalRiskScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double financialRiskScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double cyberSecurityRiskScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double regulatoryComplianceRiskScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicRiskScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double thirdPartyRiskScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double businessContinuityRiskScore,
        @NotNull EnterpriseRiskPriority priority,
        @NotBlank String createdBy) {
}
