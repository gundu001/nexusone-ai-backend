package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record AutonomousEnterpriseOmniIntelligenceResponse(
 Long id,String title,String omnichannelAssessment,String crossDomainFusion,
 String multimodalReasoning,String intelligenceSynchronization,String contextUnification,
 String decisionOrchestration,String governanceAndTrust,String outcomeOptimization,
 String omniRecommendations,Double assessmentScore,Double fusionScore,Double reasoningScore,
 Double synchronizationScore,Double contextScore,Double orchestrationScore,Double trustScore,
 Double optimizationScore,Double autonomousEnterpriseOmniIntelligenceScore,
 AutonomousEnterpriseOmniIntelligencePriority priority,AutonomousEnterpriseOmniIntelligenceStatus status,
 String createdBy,String reviewedBy,LocalDateTime reviewedAt,String decidedBy,LocalDateTime decidedAt,
 String publishedBy,LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
