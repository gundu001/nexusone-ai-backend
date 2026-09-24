package ai.nexusone.dto;
import ai.nexusone.enums.ChairmanPriority;
import jakarta.validation.constraints.*;
public record ChairmanIntelligenceRequest(
 @NotBlank String title, @NotBlank String executiveSummary,
 @NotBlank String chairmanAssessment, @NotBlank String longTermStrategy,
 @NotBlank String investmentRecommendation, @NotBlank String shareholderImpact,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double revenueGrowthScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double profitabilityScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double innovationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double transformationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double governanceScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double riskExposure,
 @NotNull ChairmanPriority priority, @NotBlank String createdBy) {}
