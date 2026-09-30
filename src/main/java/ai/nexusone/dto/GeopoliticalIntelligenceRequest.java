package ai.nexusone.dto;
import ai.nexusone.enums.GeopoliticalPriority;
import jakarta.validation.constraints.*;
public record GeopoliticalIntelligenceRequest(
 @NotBlank String title,@NotBlank String geopoliticalOutlook,@NotBlank String regionalConflictAnalysis,
 @NotBlank String politicalStabilityAnalysis,@NotBlank String sanctionsTradeAnalysis,
 @NotBlank String energyResourceSecurityAnalysis,@NotBlank String supplyChainGeopoliticalAnalysis,
 @NotBlank String regulatorySovereigntyAnalysis,@NotBlank String diplomaticRelationsAnalysis,
 @NotBlank String strategicRecommendations,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double regionalStabilityScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double politicalStabilityScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double sanctionsExposureScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double energySecurityScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double supplyChainResilienceScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double regulatorySovereigntyScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double diplomaticRelationsScore,
 @NotNull GeopoliticalPriority priority,@NotBlank String createdBy) {}
