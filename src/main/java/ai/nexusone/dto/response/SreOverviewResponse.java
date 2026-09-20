package ai.nexusone.dto.response;

public record SreOverviewResponse(
        long assessments,
        double averageReliabilityScore,
        double averageAvailability,
        double averageMttrMinutes,
        double averageMtbfHours,
        long sloCompliantServices,
        long highRiskServices,
        String overallStatus
) {}
