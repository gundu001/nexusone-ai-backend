package ai.nexusone.dto.request;
import jakarta.validation.constraints.*;
public record ResiliencePlanRequest(
 @NotBlank String applicationName, @NotBlank String environment,
 @NotBlank String primaryProvider, @NotBlank String primaryRegion,
 @NotBlank String secondaryProvider, @NotBlank String secondaryRegion,
 @Min(1) int rtoMinutes, @Min(0) int rpoMinutes) {}
