package ai.nexusone.controller;

import ai.nexusone.dto.response.CloudProviderResponse;
import ai.nexusone.dto.response.CostOptimizationResponse;
import ai.nexusone.dto.response.DeploymentStrategyResponse;
import ai.nexusone.dto.response.MultiCloudOverviewResponse;
import ai.nexusone.dto.response.MultiCloudRecommendationResponse;
import ai.nexusone.dto.response.RiskAssessmentResponse;
import ai.nexusone.service.MultiCloudDeploymentAdvisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/multicloud")
public class MultiCloudDeploymentAdvisorController {
    private final MultiCloudDeploymentAdvisorService service;

    public MultiCloudDeploymentAdvisorController(MultiCloudDeploymentAdvisorService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public ResponseEntity<MultiCloudOverviewResponse> overview() {
        return ResponseEntity.ok(service.getOverview());
    }

    @GetMapping("/providers")
    public ResponseEntity<List<CloudProviderResponse>> providers() {
        return ResponseEntity.ok(service.getProviders());
    }

    @GetMapping("/cost-optimization")
    public ResponseEntity<List<CostOptimizationResponse>> costOptimization() {
        return ResponseEntity.ok(service.getCostOptimization());
    }

    @GetMapping("/deployment-strategies")
    public ResponseEntity<List<DeploymentStrategyResponse>> deploymentStrategies() {
        return ResponseEntity.ok(service.getDeploymentStrategies());
    }

    @GetMapping("/risk-assessment")
    public ResponseEntity<List<RiskAssessmentResponse>> riskAssessment() {
        return ResponseEntity.ok(service.getRiskAssessment());
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<MultiCloudRecommendationResponse>> recommendations() {
        return ResponseEntity.ok(service.getRecommendations());
    }
}
