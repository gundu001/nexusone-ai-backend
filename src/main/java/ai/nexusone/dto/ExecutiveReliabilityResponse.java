package ai.nexusone.dto;

public record ExecutiveReliabilityResponse(
        double deploymentSuccessRate,
        double deploymentFailureRate,
        double averageDurationSeconds,
        double meanTimeToRecoverySeconds,
        long completedDeployments,
        long failedDeployments,
        long recoveredDeployments,
        String reliabilityRating
) { }
