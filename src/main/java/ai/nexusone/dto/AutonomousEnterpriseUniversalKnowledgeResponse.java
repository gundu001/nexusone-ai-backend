package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record AutonomousEnterpriseUniversalKnowledgeResponse(
 Long id,String title,String knowledgeGraphStrategy,String crossFabricKnowledgeLinking,
 String organizationalMemory,String semanticDiscovery,String knowledgeGovernance,
 String knowledgeQuality,String reuseAndLearning,String universalKnowledgeRecommendations,
 Double graphScore,Double linkingScore,Double memoryScore,Double discoveryScore,
 Double governanceScore,Double qualityScore,Double reuseScore,Double learningScore,
 Double universalKnowledgeScore,AutonomousEnterpriseUniversalKnowledgePriority priority,
 AutonomousEnterpriseUniversalKnowledgeStatus status,String createdBy,String reviewedBy,
 LocalDateTime reviewedAt,String decidedBy,LocalDateTime decidedAt,String publishedBy,
 LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
