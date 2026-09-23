package ai.nexusone.dto;
public record MemoryAnalyticsResponse(long totalMemories,long activeMemories,long validatedMemories,long reinforcedMemories,long archivedMemories,double averageConfidence,double averageImportance,long totalAccesses,long totalReinforcements) {}
