package ai.nexusone.dto;

public record DigitalBoardMemberAnalyticsResponse(
        long total,
        long generated,
        long reviewed,
        long approved,
        long rejected,
        long published,
        double averageBoardAgenda,
        double averageStrategicOversight,
        double averageFinancialStewardship,
        double averageRiskGovernance,
        double averageTechnologyOversight,
        double averageAiGovernance,
        double averageCybersecurityOversight,
        double averageStakeholderAlignment,
        double averageExecutiveAccountability,
        double enterpriseDigitalBoardMemberIntelligenceScore
) {}
