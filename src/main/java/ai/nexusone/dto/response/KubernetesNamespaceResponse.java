package ai.nexusone.dto.response;

public record KubernetesNamespaceResponse(
        String namespace,
        int deploymentCount,
        int podCount,
        double cpuUsage,
        double memoryUsage,
        String status) {
}
