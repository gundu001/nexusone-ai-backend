package ai.nexusone.dto.response;

public record ReleaseReadinessScoreResponse(
        Long releaseId,
        String application,
        String version,
        String environment,
        double codeQualityScore,
        double testCoverageScore,
        double securityScore,
        double changeRiskScore,
        double operationalScore,
        double overallReadinessScore,
        String readinessLevel,
        String decision) {
}
