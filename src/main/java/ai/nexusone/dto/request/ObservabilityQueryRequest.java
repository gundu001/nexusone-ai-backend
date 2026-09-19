package ai.nexusone.dto.request;
import jakarta.validation.constraints.NotBlank;
public record ObservabilityQueryRequest(@NotBlank String serviceName,@NotBlank String question) {}
