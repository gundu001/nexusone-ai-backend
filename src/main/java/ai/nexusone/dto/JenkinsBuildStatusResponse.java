package ai.nexusone.dto;
import java.time.LocalDateTime;
public record JenkinsBuildStatusResponse(Long executionId,String repositoryName,String jobName,Long queueId,Integer buildNumber,String status,String deploymentResult,Boolean building,Long durationMillis,String jenkinsUrl,LocalDateTime startedAt,LocalDateTime completedAt,String message) {}
