package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record AnalyzeRequest(@NotBlank String repositoryUrl) {}
