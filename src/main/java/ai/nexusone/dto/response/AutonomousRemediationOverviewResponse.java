package ai.nexusone.dto.response;

public record AutonomousRemediationOverviewResponse(
        long remediationCandidates,
        long approvedPlans,
        long successfulExecutions,
        long rollbacks,
        double successRate,
        long historyRecords
) {}
