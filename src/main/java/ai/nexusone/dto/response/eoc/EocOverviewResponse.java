package ai.nexusone.dto.response.eoc;
public record EocOverviewResponse(long applications, long deploymentsToday, long activeIncidents, long predictedRisks, long remediationCandidates, long successfulExecutions, long rollbacks, int healthScore) {}
