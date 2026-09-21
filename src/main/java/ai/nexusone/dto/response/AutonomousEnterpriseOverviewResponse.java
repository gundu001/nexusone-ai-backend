package ai.nexusone.dto.response;

public record AutonomousEnterpriseOverviewResponse(
        long totalAssessments,
        double averageEnterpriseHealthScore,
        double averageEnterpriseRiskScore,
        double averageEnterpriseReadinessScore,
        double averageAutonomyMaturityScore,
        long criticalAssessments,
        long governedActionsPending,
        String overallStatus
) {}
