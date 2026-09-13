package ai.nexusone.dto;

import java.time.LocalDate;

public record RiskForecastResponse(
        LocalDate forecastDate,
        String riskLevel,
        double riskScore,
        double projectedSuccessProbability,
        long historicalSampleSize,
        String rationale
) { }
