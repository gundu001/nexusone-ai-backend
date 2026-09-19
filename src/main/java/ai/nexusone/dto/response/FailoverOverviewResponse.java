package ai.nexusone.dto.response;
public record FailoverOverviewResponse(long totalPlans,long readyPlans,long executions,long successfulExecutions,double averageReadinessScore) {}
