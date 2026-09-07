package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record DeploymentRequest(@NotBlank String applicationName,@NotBlank String environment,@NotBlank String provider,@NotBlank String version) {}
