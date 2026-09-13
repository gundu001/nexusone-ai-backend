package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ComplianceResponse(
        Long executionId,
        boolean compliant,
        double complianceScore,
        int passedPolicyCount,
        int failedPolicyCount,
        List<String> passedPolicies,
        List<String> failedPolicies,
        LocalDateTime evaluatedAt
) { }
