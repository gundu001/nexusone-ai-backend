package ai.nexusone.dto.response;

public record AutonomousOperationsOverviewResponse(
        long totalDecisions,
        long pendingApproval,
        long approvedActions,
        long executedActions,
        long failedActions,
        double averageConfidenceScore,
        String operationalStatus
) {}
