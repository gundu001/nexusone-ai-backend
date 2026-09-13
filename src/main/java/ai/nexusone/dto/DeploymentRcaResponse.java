package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DeploymentRcaResponse(
        Long executionId,
        String repositoryName,
        String jobName,
        Integer buildNumber,
        String executionStatus,
        String deploymentResult,
        String rootCauseCode,
        String rootCause,
        int confidenceScore,
        String severity,
        String summary,
        List<String> evidence,
        List<String> recommendedFixes,
        LocalDateTime generatedAt) {
}
