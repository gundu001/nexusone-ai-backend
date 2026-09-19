package ai.nexusone.dto.response.eoc;
public record EocOperationsResponse(long pendingApprovals, long approvedPlans, long dryRuns, long successfulExecutions, long rollbacks, long historyRecords, int successRate) {}
