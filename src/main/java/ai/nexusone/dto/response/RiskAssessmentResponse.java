package ai.nexusone.dto.response;

public record RiskAssessmentResponse(
        long riskId,
        String provider,
        String resource,
        String riskLevel,
        String reason,
        String mitigation) {
}
