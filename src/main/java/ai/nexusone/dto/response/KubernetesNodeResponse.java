package ai.nexusone.dto.response;

public record KubernetesNodeResponse(
        String nodeName,
        String role,
        String status,
        String kubernetesVersion,
        double cpuUsage,
        double memoryUsage,
        int podCount,
        String riskLevel) {
}
