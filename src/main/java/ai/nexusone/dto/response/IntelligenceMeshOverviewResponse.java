package ai.nexusone.dto.response;

public record IntelligenceMeshOverviewResponse(long totalCollaborations,long coordinatedDecisions,long pendingApprovals,long blockedActions,double averageConsensusScore,String overallStatus) {}
