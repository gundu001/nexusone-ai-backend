package ai.nexusone.dto;

public record RiskTrendResponse(
        String riskLevel,
        long count,
        double percentage
) { }
