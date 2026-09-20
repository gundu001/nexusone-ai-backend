package ai.nexusone.dto.response;

public record PlatformEngineeringOverviewResponse(
        long totalAssessments,
        double averagePlatformScore,
        double averageDeploymentSuccess,
        double averageAutomationCoverage,
        long healthyPlatforms,
        long platformsAtRisk,
        long criticalPlatforms,
        String overallStatus
) {}
