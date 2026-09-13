package ai.nexusone.dto;

public record PolicyViolationResponse(
        Long executionId,
        String policyCode,
        String policyName,
        String severity,
        String description,
        String remediation,
        boolean blocking
) { }
