package ai.nexusone.dto.request;

import jakarta.validation.constraints.*;

public record DecisionFabricRequest(
        @NotBlank @Size(max=160) String decisionName,
        @NotBlank @Size(max=160) String applicationName,
        @NotBlank @Size(max=80) String environment,
        @NotBlank @Size(max=80) String decisionType,
        @NotBlank @Size(max=120) String sourceAgent,
        @NotNull @DecimalMin("0.0") @DecimalMax("100.0") Double confidenceScore,
        @NotNull @Min(0) @Max(100) Integer riskScore,
        @NotNull @Min(0) @Max(100) Integer policyComplianceScore,
        @NotNull Boolean approvalRequired,
        @NotBlank @Size(max=1500) String contextSummary
) {}
