package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record FuturePredictionIntelligenceResponse(
 Long id,String title,String futureOutlook,String technologyTrendForecast,String marketEvolutionForecast,
 String customerBehaviorForecast,String economicForecast,String supplyChainForecast,String workforceForecast,
 String cyberThreatForecast,String regulatoryForecast,String industryDisruptionForecast,String strategicRecommendations,
 Double technologyPredictionScore,Double marketPredictionScore,Double customerPredictionScore,
 Double economicPredictionScore,Double supplyChainPredictionScore,Double workforcePredictionScore,
 Double cyberThreatPredictionScore,Double regulatoryPredictionScore,Double industryDisruptionPredictionScore,
 Double futurePredictionIntelligenceScore,FuturePredictionPriority priority,FuturePredictionStatus status,
 String createdBy,String reviewedBy,LocalDateTime reviewedAt,String decidedBy,LocalDateTime decidedAt,
 String publishedBy,LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
