package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record AutonomousEnterpriseMetaIntelligenceResponse(
 Long id,String title,String intelligenceAssessment,String intelligenceOrchestration,
 String intelligencePrioritization,String intelligenceGovernance,String conflictResolution,
 String intelligenceOptimization,String trustManagement,String performanceManagement,
 String metaRecommendations,Double assessmentScore,Double orchestrationScore,
 Double prioritizationScore,Double governanceScore,Double conflictResolutionScore,
 Double optimizationScore,Double trustScore,Double performanceScore,
 Double autonomousEnterpriseMetaIntelligenceScore,
 AutonomousEnterpriseMetaIntelligencePriority priority,
 AutonomousEnterpriseMetaIntelligenceStatus status,String createdBy,String reviewedBy,
 LocalDateTime reviewedAt,String decidedBy,LocalDateTime decidedAt,String publishedBy,
 LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
