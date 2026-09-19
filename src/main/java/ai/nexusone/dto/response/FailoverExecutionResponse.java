package ai.nexusone.dto.response;
import java.time.LocalDateTime;
public record FailoverExecutionResponse(Long id,Long planId,String applicationName,String sourceProvider,String targetProvider,String action,String status,boolean dryRun,String initiatedBy,String message,LocalDateTime startedAt,LocalDateTime completedAt) {}
