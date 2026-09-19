package ai.nexusone.dto.request;
import jakarta.validation.constraints.*;
public record RecoveryAssessmentRequest(@NotBlank String applicationName,@NotBlank String primaryCloud,@NotBlank String recoveryCloud,@Min(1) int rtoMinutes,@Min(0) int rpoMinutes,@Min(0) @Max(100) int backupScore,@Min(0) @Max(100) int replicationScore,@Min(0) @Max(100) int failoverScore) {}
