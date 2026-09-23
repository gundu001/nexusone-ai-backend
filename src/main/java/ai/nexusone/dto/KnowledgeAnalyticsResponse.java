package ai.nexusone.dto;
public record KnowledgeAnalyticsResponse(long totalKnowledgeItems,long draftItems,long validatedItems,long recommendedItems,long archivedItems,double averageConfidence,long totalReuses,double knowledgeReuseRate) {}
