package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record KnowledgeResponse(
 Long id,String title,KnowledgeCategory category,String sourceModule,Long incidentId,Long decisionId,
 Long executionId,Long outcomeId,Long learningId,String businessContext,String problemStatement,
 String solutionSummary,String lessonsLearned,String bestPractices,String reusablePlaybook,
 Double confidenceScore,Integer reuseCount,KnowledgeStatus status,String createdBy,String validatedBy,
 LocalDateTime validatedAt,LocalDateTime createdAt,LocalDateTime updatedAt
) {}
