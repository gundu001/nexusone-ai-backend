package ai.nexusone.dto;
import java.time.LocalDateTime;
public record DeploymentHistoryResponse(Long executionId,String repositoryName,String jobName,Integer buildNumber,String status,String deploymentResult,String triggeredBy,LocalDateTime startedAt,LocalDateTime completedAt,String jenkinsUrl) {}
