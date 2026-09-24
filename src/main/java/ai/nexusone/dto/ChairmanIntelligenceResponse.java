package ai.nexusone.dto;
import ai.nexusone.enums.*; import java.time.LocalDateTime;
public record ChairmanIntelligenceResponse(
 Long id, String title, String executiveSummary, String chairmanAssessment,
 String longTermStrategy, String investmentRecommendation, String shareholderImpact,
 Double revenueGrowthScore, Double profitabilityScore, Double innovationScore,
 Double transformationScore, Double governanceScore, Double riskExposure,
 Double chairmanScore, ChairmanPriority priority, ChairmanIntelligenceStatus status,
 String createdBy, String reviewedBy, LocalDateTime reviewedAt,
 String decidedBy, LocalDateTime decidedAt, String executedBy,
 LocalDateTime executedAt, LocalDateTime createdAt, LocalDateTime updatedAt) {}
