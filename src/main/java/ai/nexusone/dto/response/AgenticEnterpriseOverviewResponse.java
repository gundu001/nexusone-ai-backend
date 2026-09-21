package ai.nexusone.dto.response;

public record AgenticEnterpriseOverviewResponse(
        long totalExecutions,
        long successfulExecutions,
        long failedExecutions,
        long pendingApprovals,
        double automationCoverage,
        double averageConfidenceScore,
        String overallStatus
) {}
