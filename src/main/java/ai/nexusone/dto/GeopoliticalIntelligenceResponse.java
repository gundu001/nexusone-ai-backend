package ai.nexusone.dto;
import ai.nexusone.enums.*; import java.time.LocalDateTime;
public record GeopoliticalIntelligenceResponse(Long id,String title,String geopoliticalOutlook,
 String regionalConflictAnalysis,String politicalStabilityAnalysis,String sanctionsTradeAnalysis,
 String energyResourceSecurityAnalysis,String supplyChainGeopoliticalAnalysis,
 String regulatorySovereigntyAnalysis,String diplomaticRelationsAnalysis,String strategicRecommendations,
 Double regionalStabilityScore,Double politicalStabilityScore,Double sanctionsExposureScore,
 Double energySecurityScore,Double supplyChainResilienceScore,Double regulatorySovereigntyScore,
 Double diplomaticRelationsScore,Double geopoliticalIntelligenceScore,GeopoliticalPriority priority,
 GeopoliticalStatus status,String createdBy,String reviewedBy,LocalDateTime reviewedAt,String decidedBy,
 LocalDateTime decidedAt,String publishedBy,LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
