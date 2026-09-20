package ai.nexusone.dto.response;

public record ExecutiveIntelligenceOverviewResponse(
        long totalAssessments,
        double averageEnterpriseHealthScore,
        double averageBusinessReadinessScore,
        double averageOperationalExcellenceScore,
        double averageTechnologyMaturityScore,
        long highRiskAssessments,
        long executiveActionsRequired,
        String overallStatus
) {}
