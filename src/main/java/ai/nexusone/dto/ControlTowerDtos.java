package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public final class ControlTowerDtos {
    private ControlTowerDtos() {}

    public record OverviewResponse(
            long autonomousExecutions,
            long successfulAutomations,
            long failedAutomations,
            long approvalQueue,
            double executionRate,
            String controlStatus,
            LocalDateTime generatedAt) {}

    public record OpportunityResponse(
            long executionId,
            String repositoryName,
            String opportunityCode,
            String title,
            int confidence,
            boolean recommended,
            boolean humanApprovalRequired,
            String status,
            List<String> evidence) {}

    public record ApprovalResponse(
            long approvalId,
            long executionId,
            String action,
            String requestedRole,
            String status,
            LocalDateTime createdAt,
            LocalDateTime decidedAt,
            String decisionNote) {}

    public record DecisionRequest(String note) {}

    public record ExecutionResponse(
            long automationId,
            long executionId,
            boolean automationExecuted,
            String action,
            String result,
            String message,
            LocalDateTime executedAt) {}

    public record HistoryResponse(
            long automationId,
            long executionId,
            String action,
            String status,
            String details,
            LocalDateTime occurredAt) {}

    public record ApiError(
            String code,
            String message,
            String path,
            LocalDateTime timestamp) {}
}
