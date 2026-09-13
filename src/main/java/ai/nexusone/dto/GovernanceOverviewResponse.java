package ai.nexusone.dto;

import java.time.LocalDateTime;

public record GovernanceOverviewResponse(
        long totalReleases,
        long readyReleases,
        long blockedReleases,
        long reviewRequiredReleases,
        double averageReadinessScore,
        double complianceRate,
        long policyViolationCount,
        String overallDecision,
        String governanceRiskLevel,
        LocalDateTime generatedAt
) { }
