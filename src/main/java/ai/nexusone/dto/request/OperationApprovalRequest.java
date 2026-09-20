package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record OperationApprovalRequest(
        @NotBlank String approvedBy,
        String comments
) {}
