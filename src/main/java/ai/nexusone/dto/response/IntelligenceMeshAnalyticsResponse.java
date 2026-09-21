package ai.nexusone.dto.response;

public record IntelligenceMeshAnalyticsResponse(

        int averageConsensus,
        int highestConsensus,
        int lowestConsensus,
        long totalCollaborations,
        long coordinatedActions,
        long pendingApprovals

) {}