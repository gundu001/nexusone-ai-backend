package ai.nexusone.dto;

import ai.nexusone.enums.DeploymentResult;
import ai.nexusone.enums.DeploymentStatus;

import java.time.LocalDateTime;

public record DeploymentExecutionResponse(
        Long executionId,
        String repositoryName,
        String jobName,
        Integer buildNumber,
        DeploymentStatus status,
        DeploymentResult deploymentResult,
        String triggeredBy,
        LocalDateTime startedAt,
        LocalDateTime completedAt,
        String message
) { }
