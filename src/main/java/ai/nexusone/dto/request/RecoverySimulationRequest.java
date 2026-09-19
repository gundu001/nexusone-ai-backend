package ai.nexusone.dto.request;
import jakarta.validation.constraints.*;
public record RecoverySimulationRequest(@NotBlank String applicationName,@NotBlank String scenario,@Min(1) int targetRtoMinutes,@Min(0) int targetRpoMinutes,@Min(0) @Max(100) int infrastructureHealth) {}
