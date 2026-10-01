package ai.nexusone.dto;

import jakarta.validation.constraints.NotBlank;

public record DigitalBoardMemberActionRequest(
        @NotBlank String actionBy
) {}
