package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ExecutivePlatformHealthResponse(
        double platformHealthScore,
        String healthStatus,
        long totalDeployments,
        long healthyDeployments,
        long activeDeployments,
        long unhealthyDeployments,
        double healthyDeploymentRate,
        double activeDeploymentRate,
        double failureRate,
        List<String> healthSignals,
        LocalDateTime generatedAt
) { }
