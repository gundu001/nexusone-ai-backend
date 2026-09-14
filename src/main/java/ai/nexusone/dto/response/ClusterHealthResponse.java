package ai.nexusone.dto.response;

public record ClusterHealthResponse(
        String clusterName,
        String status,
        double healthScore,
        int totalNodes,
        int readyNodes,
        int runningPods,
        int failedPods,
        double cpuUsage,
        double memoryUsage) {
}
