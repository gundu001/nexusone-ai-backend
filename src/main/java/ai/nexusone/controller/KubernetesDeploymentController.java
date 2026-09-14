package ai.nexusone.controller;

import ai.nexusone.dto.response.ClusterHealthResponse;
import ai.nexusone.dto.response.DeploymentTrendResponse;
import ai.nexusone.dto.response.KubernetesDeploymentResponse;
import ai.nexusone.dto.response.KubernetesNamespaceResponse;
import ai.nexusone.dto.response.KubernetesNodeResponse;
import ai.nexusone.dto.response.KubernetesOverviewResponse;
import ai.nexusone.dto.response.KubernetesPodResponse;
import ai.nexusone.dto.response.KubernetesRecommendationResponse;
import ai.nexusone.service.KubernetesDeploymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/kubernetes")
public class KubernetesDeploymentController {

    private final KubernetesDeploymentService service;

    public KubernetesDeploymentController(KubernetesDeploymentService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public ResponseEntity<KubernetesOverviewResponse> overview() {
        return ResponseEntity.ok(service.getOverview());
    }

    @GetMapping("/cluster-health")
    public ResponseEntity<List<ClusterHealthResponse>> clusterHealth() {
        return ResponseEntity.ok(service.getClusterHealth());
    }

    @GetMapping("/nodes")
    public ResponseEntity<List<KubernetesNodeResponse>> nodes() {
        return ResponseEntity.ok(service.getNodes());
    }

    @GetMapping("/deployments")
    public ResponseEntity<List<KubernetesDeploymentResponse>> deployments() {
        return ResponseEntity.ok(service.getDeployments());
    }

    @GetMapping("/pods")
    public ResponseEntity<List<KubernetesPodResponse>> pods() {
        return ResponseEntity.ok(service.getPods());
    }

    @GetMapping("/namespaces")
    public ResponseEntity<List<KubernetesNamespaceResponse>> namespaces() {
        return ResponseEntity.ok(service.getNamespaces());
    }

    @GetMapping("/deployment-trends")
    public ResponseEntity<DeploymentTrendResponse> deploymentTrends() {
        return ResponseEntity.ok(service.getDeploymentTrends());
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<KubernetesRecommendationResponse>> recommendations() {
        return ResponseEntity.ok(service.getRecommendations());
    }
}
