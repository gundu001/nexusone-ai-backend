package ai.nexusone.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GovernanceSecurityAssessmentRequest(
        @NotBlank String applicationName,
        @NotBlank String environment,
        @NotBlank String complianceFramework,
        @NotNull Boolean releaseApproved,
        @NotNull Boolean securityScanPassed,
        @NotNull @Min(0) Integer criticalVulnerabilities,
        @NotNull @Min(0) Integer highVulnerabilities,
        @NotNull @Min(0) Integer complianceChecksPassed,
        @NotNull @Min(0) Integer complianceChecksFailed,
        @NotNull Boolean auditEvidenceAvailable,
        @NotNull Boolean encryptionEnabled,
        @NotNull Boolean leastPrivilegeApplied,
        @NotNull @Min(0) @Max(100) Double policyCoveragePercent
) {}
