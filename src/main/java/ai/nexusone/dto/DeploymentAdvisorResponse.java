package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DeploymentAdvisorResponse(
        Long executionId,
        String repositoryName,
        String jobName,
        Integer buildNumber,
        String executionStatus,
        String deploymentResult,
        String riskLevel,
        String recommendation,
        String summary,
        List<String> observations,
        List<String> recommendedActions,
        LocalDateTime generatedAt) {
}
