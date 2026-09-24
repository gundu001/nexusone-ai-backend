package ai.nexusone.dto;
public record BoardroomAnalyticsResponse(
        long total, long drafted, long reviewed, long approved, long rejected,
        long executed, double averageAlignment, double averageExpectedRoi,
        double averageRiskExposure, double enterpriseBoardroomScore) {}
