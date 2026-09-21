package ai.nexusone.dto.response;

public record AutonomousCommandCenterOverviewResponse(
        long totalAssessments,
        double averageEnterpriseHealthScore,
        double averageEnterpriseRiskScore,
        double averageEnterpriseReadinessScore,
        double averageAutonomyMaturityScore,
        long criticalAssessments,
        long governedActionsPending,
        String overallStatus
) {}
