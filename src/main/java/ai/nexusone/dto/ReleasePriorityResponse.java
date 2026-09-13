package ai.nexusone.dto;

public record ReleasePriorityResponse(
        Long executionId,
        String repositoryName,
        String jobName,
        double priorityScore,
        String priorityBand,
        int rank,
        String recommendedDisposition,
        String rationale
) { }
