package ai.nexusone.dto.response;

public record KubernetesOverviewResponse(
        int totalClusters,
        int healthyClusters,
        int warningClusters,
        int totalNamespaces,
        int totalNodes,
        int readyNodes,
        int runningPods,
        int failedPods,
        int totalDeployments,
        int healthyDeployments,
        double clusterHealthScore) {
}
