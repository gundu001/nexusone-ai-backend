package ai.nexusone.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record FailoverActionRequest(@NotNull Long planId, @NotBlank String initiatedBy, String reason) {}
