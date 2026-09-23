package ai.nexusone.dto;
public record LearningAnalyticsResponse(long totalLearnings,long discovered,long recommended,long applied,long rejected,double averageGap,double averageConfidence,double applicationRate) {}
