package ai.nexusone.dto;

import ai.nexusone.enums.DigitalBoardMemberPriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DigitalBoardMemberIntelligenceRequest(
        @NotBlank String title,
        @NotBlank String boardAgenda,
        @NotBlank String strategicOversight,
        @NotBlank String financialStewardship,
        @NotBlank String riskGovernance,
        @NotBlank String technologyOversight,
        @NotBlank String aiGovernance,
        @NotBlank String cybersecurityOversight,
        @NotBlank String stakeholderAlignment,
        @NotBlank String executiveAccountability,
        @NotBlank String boardRecommendations,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double boardAgendaScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicOversightScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double financialStewardshipScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double riskGovernanceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double technologyOversightScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double aiGovernanceScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double cybersecurityOversightScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double stakeholderAlignmentScore,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double executiveAccountabilityScore,
        @NotNull DigitalBoardMemberPriority priority,
        @NotBlank String createdBy
) {}
