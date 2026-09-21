package ai.nexusone.dto.request;
import jakarta.validation.constraints.*;
public record IntelligenceMeshRequest(
 @NotBlank String sourceAgent, @NotBlank String targetAgent, @NotBlank String eventType,
 @NotBlank String applicationName, @NotBlank String environment,
 @NotBlank @Size(max=1000) String contextSummary,
 @NotNull @DecimalMin("0.0") @DecimalMax("100.0") Double sourceConfidence,
 @NotNull @DecimalMin("0.0") @DecimalMax("100.0") Double targetConfidence,
 @NotNull Boolean approvalRequired) {}
