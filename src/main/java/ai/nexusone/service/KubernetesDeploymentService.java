package ai.nexusone.service;

import ai.nexusone.dto.response.ClusterHealthResponse;
import ai.nexusone.dto.response.DeploymentTrendResponse;
import ai.nexusone.dto.response.KubernetesDeploymentResponse;
import ai.nexusone.dto.response.KubernetesNamespaceResponse;
import ai.nexusone.dto.response.KubernetesNodeResponse;
import ai.nexusone.dto.response.KubernetesOverviewResponse;
import ai.nexusone.dto.response.KubernetesPodResponse;
import ai.nexusone.dto.response.KubernetesRecommendationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KubernetesDeploymentService {

    public KubernetesOverviewResponse getOverview() {
        return new KubernetesOverviewResponse(1, 1, 0, 4, 3, 3, 18, 1, 6, 5, 94.0);
    }

    public List<ClusterHealthResponse> getClusterHealth() {
        return List.of(new ClusterHealthResponse(
                "nexusone-prod-cluster", "HEALTHY", 94.0, 3, 3, 18, 1, 64.0, 71.0));
    }

    public List<KubernetesNodeResponse> getNodes() {
        return List.of(
                new KubernetesNodeResponse("control-plane-01", "CONTROL_PLANE", "READY", "v1.31.2", 42.0, 58.0, 6, "LOW"),
                new KubernetesNodeResponse("worker-node-01", "WORKER", "READY", "v1.31.2", 68.0, 74.0, 7, "MEDIUM"),
                new KubernetesNodeResponse("worker-node-02", "WORKER", "READY", "v1.31.2", 55.0, 66.0, 6, "LOW")
        );
    }

    public List<KubernetesDeploymentResponse> getDeployments() {
        return List.of(
                deployment("nexusone-backend", "production", 3, 3, "nexusone/backend:5.1", "HEALTHY"),
                deployment("nexusone-frontend", "production", 2, 2, "nexusone/frontend:5.1", "HEALTHY"),
                deployment("risk-engine", "ai-services", 3, 2, "nexusone/risk-engine:5.0", "WARNING"),
                deployment("release-orchestrator", "ai-services", 2, 2, "nexusone/orchestrator:5.7", "HEALTHY"),
                deployment("mysql", "data", 1, 1, "mysql:8.4", "HEALTHY"),
                deployment("jenkins-agent", "devops", 2, 2, "jenkins/inbound-agent:latest", "HEALTHY")
        );
    }

    public List<KubernetesPodResponse> getPods() {
        return List.of(
                new KubernetesPodResponse("nexusone-backend-7c9d8f-1", "production", "worker-node-01", "RUNNING", 0, 38.0, 52.0, "LOW"),
                new KubernetesPodResponse("nexusone-backend-7c9d8f-2", "production", "worker-node-02", "RUNNING", 0, 41.0, 55.0, "LOW"),
                new KubernetesPodResponse("nexusone-frontend-6f4b7d-1", "production", "worker-node-01", "RUNNING", 0, 25.0, 34.0, "LOW"),
                new KubernetesPodResponse("risk-engine-86b5cc-1", "ai-services", "worker-node-01", "RUNNING", 1, 78.0, 83.0, "HIGH"),
                new KubernetesPodResponse("risk-engine-86b5cc-2", "ai-services", "worker-node-02", "CRASH_LOOP_BACKOFF", 6, 0.0, 0.0, "CRITICAL")
        );
    }

    public List<KubernetesNamespaceResponse> getNamespaces() {
        return List.of(
                new KubernetesNamespaceResponse("production", 2, 7, 61.0, 68.0, "HEALTHY"),
                new KubernetesNamespaceResponse("ai-services", 2, 6, 79.0, 82.0, "WARNING"),
                new KubernetesNamespaceResponse("data", 1, 2, 48.0, 70.0, "HEALTHY"),
                new KubernetesNamespaceResponse("devops", 1, 3, 57.0, 63.0, "HEALTHY")
        );
    }

    public DeploymentTrendResponse getDeploymentTrends() {
        List<DeploymentTrendResponse.TrendPoint> trend = List.of(
                new DeploymentTrendResponse.TrendPoint("Mon", 18, 1),
                new DeploymentTrendResponse.TrendPoint("Tue", 21, 2),
                new DeploymentTrendResponse.TrendPoint("Wed", 19, 1),
                new DeploymentTrendResponse.TrendPoint("Thu", 24, 2),
                new DeploymentTrendResponse.TrendPoint("Fri", 26, 2)
        );
        return new DeploymentTrendResponse(108, 8, 93.1, trend);
    }

    public List<KubernetesRecommendationResponse> getRecommendations() {
        return List.of(
                new KubernetesRecommendationResponse(5101, "CRITICAL", "POD_RELIABILITY", "risk-engine-86b5cc-2",
                        "The pod has repeated restarts and is not reaching a ready state.",
                        "Inspect pod logs and events, correct the failing configuration, and restart the rollout."),
                new KubernetesRecommendationResponse(5102, "HIGH", "RESOURCE_OPTIMIZATION", "ai-services",
                        "Namespace memory utilization is above the preferred operating threshold.",
                        "Review requests and limits, then scale the risk-engine deployment if demand is sustained."),
                new KubernetesRecommendationResponse(5103, "MEDIUM", "HIGH_AVAILABILITY", "nexusone-backend",
                        "Backend replicas are healthy but concentrated across two worker nodes.",
                        "Add pod anti-affinity or topology spread constraints for stronger failure-domain distribution.")
        );
    }

    private KubernetesDeploymentResponse deployment(String name, String namespace, int desired,
                                                      int available, String image, String status) {
        int unavailable = Math.max(desired - available, 0);
        double availability = desired == 0 ? 0.0 : Math.round((available * 10000.0) / desired) / 100.0;
        return new KubernetesDeploymentResponse(name, namespace, desired, available, unavailable, image, status, availability);
    }
}
