package ai.nexusone.controller;

import ai.nexusone.dto.DeploymentOverviewResponse;
import ai.nexusone.dto.DeploymentTrendResponse;
import ai.nexusone.dto.RcaInsightResponse;
import ai.nexusone.dto.RiskTrendResponse;
import ai.nexusone.dto.SelfHealingInsightResponse;
import ai.nexusone.service.DeploymentIntelligenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/intelligence")
public class DeploymentIntelligenceController {

    private final DeploymentIntelligenceService service;

    public DeploymentIntelligenceController(DeploymentIntelligenceService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public ResponseEntity<DeploymentOverviewResponse> overview() {
        return ResponseEntity.ok(service.getOverview());
    }

    @GetMapping("/deployment-trends")
    public ResponseEntity<List<DeploymentTrendResponse>> deploymentTrends() {
        return ResponseEntity.ok(service.getDeploymentTrends());
    }

    @GetMapping("/risk-trends")
    public ResponseEntity<List<RiskTrendResponse>> riskTrends() {
        return ResponseEntity.ok(service.getRiskTrends());
    }

    @GetMapping("/rca-insights")
    public ResponseEntity<List<RcaInsightResponse>> rcaInsights() {
        return ResponseEntity.ok(service.getRcaInsights());
    }

    @GetMapping("/self-healing-insights")
    public ResponseEntity<List<SelfHealingInsightResponse>> selfHealingInsights() {
        return ResponseEntity.ok(service.getSelfHealingInsights());
    }
}
