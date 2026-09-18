package ai.nexusone.dto.response;

public record ChangeRiskCopilotOverviewResponse(
        int totalChanges,
        int highRiskChanges,
        int approvalRequired,
        double averageRiskScore,
        long analysisHistoryCount
) {}
