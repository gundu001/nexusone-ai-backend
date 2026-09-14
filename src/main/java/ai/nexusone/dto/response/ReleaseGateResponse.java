package ai.nexusone.dto.response;

public record ReleaseGateResponse(
        Long gateId,
        Long releaseId,
        String application,
        String gateName,
        String category,
        double actualValue,
        double thresholdValue,
        String unit,
        String status,
        boolean mandatory,
        String details) {
}
