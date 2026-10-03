package ai.nexusone.dto;
public record AutonomousEnterpriseCollectiveIntelligenceAnalyticsResponse(
 long total,long generated,long reviewed,long approved,long rejected,long published,
 double averageHumanExpertise,double averageAgentCollaboration,double averageCrossTeamKnowledge,
 double averageConsensusQuality,double averageDiversityOfThought,double averageSharedLearning,
 double averageDecisionAlignment,double averageGovernanceAlignment,double averageOutcomeImpact,
 double enterpriseCollectiveIntelligenceScore) {}
