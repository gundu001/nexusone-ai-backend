package ai.nexusone.dto;
import ai.nexusone.enums.BoardroomPriority;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record BoardroomBriefingRequest(
        @NotBlank String title,
        @NotBlank String executiveSummary,
        @NotBlank String strategicAgenda,
        @NotBlank String boardRecommendation,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double expectedRoi,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double strategicAlignment,
        @NotNull @DecimalMin("0") @DecimalMax("100") Double riskExposure,
        @NotNull BoardroomPriority priority,
        @NotBlank String createdBy) {}
