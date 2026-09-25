package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record MarketIntelligenceResponse(
 Long id, String title, String marketLandscape, String competitorAnalysis,
 String differentiationStrategy, String opportunityAssessment, String strategicRecommendation,
 Double marketAttractivenessScore, Double competitivePositionScore, Double differentiationScore,
 Double innovationStrengthScore, Double executionReadinessScore, Double marketThreatExposure,
 Double marketCompetitiveConfidenceScore, MarketPriority priority, MarketIntelligenceStatus status,
 String createdBy, String reviewedBy, LocalDateTime reviewedAt, String decidedBy,
 LocalDateTime decidedAt, String publishedBy, LocalDateTime publishedAt,
 LocalDateTime createdAt, LocalDateTime updatedAt) {}
