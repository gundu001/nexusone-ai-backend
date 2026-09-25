package ai.nexusone.dto;
import ai.nexusone.enums.MarketPriority;
import jakarta.validation.constraints.*;
public record MarketIntelligenceRequest(
 @NotBlank String title, @NotBlank String marketLandscape,
 @NotBlank String competitorAnalysis, @NotBlank String differentiationStrategy,
 @NotBlank String opportunityAssessment, @NotBlank String strategicRecommendation,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double marketAttractivenessScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double competitivePositionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double differentiationScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double innovationStrengthScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double executionReadinessScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double marketThreatExposure,
 @NotNull MarketPriority priority, @NotBlank String createdBy) {}
