package ai.nexusone.dto;
import ai.nexusone.enums.FuturePredictionPriority;
import jakarta.validation.constraints.*;
public record FuturePredictionIntelligenceRequest(
 @NotBlank String title,
 @NotBlank String futureOutlook,
 @NotBlank String technologyTrendForecast,
 @NotBlank String marketEvolutionForecast,
 @NotBlank String customerBehaviorForecast,
 @NotBlank String economicForecast,
 @NotBlank String supplyChainForecast,
 @NotBlank String workforceForecast,
 @NotBlank String cyberThreatForecast,
 @NotBlank String regulatoryForecast,
 @NotBlank String industryDisruptionForecast,
 @NotBlank String strategicRecommendations,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double technologyPredictionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double marketPredictionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double customerPredictionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double economicPredictionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double supplyChainPredictionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double workforcePredictionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double cyberThreatPredictionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double regulatoryPredictionScore,
 @NotNull @DecimalMin("0") @DecimalMax("100") Double industryDisruptionPredictionScore,
 @NotNull FuturePredictionPriority priority,
 @NotBlank String createdBy) {}
