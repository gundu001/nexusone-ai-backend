package ai.nexusone.dto.response;

public record GovernanceSecurityOverviewResponse(
        long totalAssessments,
        double averageGovernanceScore,
        double averageComplianceScore,
        double averageSecurityScore,
        long compliantAssessments,
        long nonCompliantAssessments,
        long criticalRiskAssessments,
        long openPolicyViolations,
        String overallStatus
) {}
