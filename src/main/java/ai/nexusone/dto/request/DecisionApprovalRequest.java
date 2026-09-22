package ai.nexusone.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DecisionApprovalRequest(
        @NotBlank @Size(max=160) String approvedBy,
        @NotBlank @Size(max=20) String outcome,
        @Size(max=1000) String comment
) {}
