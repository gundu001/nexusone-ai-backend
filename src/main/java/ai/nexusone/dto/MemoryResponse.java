package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record MemoryResponse(
 Long id,String title,MemoryType memoryType,String sourceModule,Long knowledgeId,Long incidentId,
 Long decisionId,Long executionId,Long outcomeId,Long learningId,String context,String observation,
 String learnedPattern,String reasoningRule,String recommendedAction,Double confidenceScore,
 Double importanceScore,Integer accessCount,Integer reinforcementCount,MemoryStatus status,
 String createdBy,String validatedBy,LocalDateTime validatedAt,LocalDateTime lastAccessedAt,
 LocalDateTime createdAt,LocalDateTime updatedAt) {}
