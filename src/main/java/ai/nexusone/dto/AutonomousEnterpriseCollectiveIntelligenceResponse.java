package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record AutonomousEnterpriseCollectiveIntelligenceResponse(
 Long id,String title,String humanExpertise,String agentCollaboration,String crossTeamKnowledge,
 String consensusQuality,String diversityOfThought,String sharedLearning,String decisionAlignment,
 String governanceAlignment,String outcomeImpact,String collectiveRecommendations,
 Double humanExpertiseScore,Double agentCollaborationScore,Double crossTeamKnowledgeScore,
 Double consensusQualityScore,Double diversityOfThoughtScore,Double sharedLearningScore,
 Double decisionAlignmentScore,Double governanceAlignmentScore,Double outcomeImpactScore,
 Double autonomousEnterpriseCollectiveIntelligenceScore,
 AutonomousEnterpriseCollectiveIntelligencePriority priority,
 AutonomousEnterpriseCollectiveIntelligenceStatus status,String createdBy,String reviewedBy,
 LocalDateTime reviewedAt,String decidedBy,LocalDateTime decidedAt,String publishedBy,
 LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
