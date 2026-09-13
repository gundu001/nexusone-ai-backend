package ai.nexusone.controller;

import ai.nexusone.dto.CabRecommendationResponse;
import ai.nexusone.dto.ComplianceResponse;
import ai.nexusone.dto.GoNoGoDecisionResponse;
import ai.nexusone.dto.GovernanceOverviewResponse;
import ai.nexusone.dto.PolicyViolationResponse;
import ai.nexusone.dto.ReleaseReadinessResponse;
import ai.nexusone.service.ReleaseGovernanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/governance")
public class ReleaseGovernanceController {
    private final ReleaseGovernanceService service;
    public ReleaseGovernanceController(ReleaseGovernanceService service) { this.service = service; }
    @GetMapping("/overview") public ResponseEntity<GovernanceOverviewResponse> overview(){ return ResponseEntity.ok(service.getOverview()); }
    @GetMapping("/release-readiness/{executionId}") public ResponseEntity<ReleaseReadinessResponse> readiness(@PathVariable Long executionId){ return ResponseEntity.ok(service.getReleaseReadiness(executionId)); }
    @GetMapping("/compliance/{executionId}") public ResponseEntity<ComplianceResponse> compliance(@PathVariable Long executionId){ return ResponseEntity.ok(service.getCompliance(executionId)); }
    @GetMapping("/policy-violations/{executionId}") public ResponseEntity<List<PolicyViolationResponse>> violations(@PathVariable Long executionId){ return ResponseEntity.ok(service.getPolicyViolations(executionId)); }
    @GetMapping("/go-no-go/{executionId}") public ResponseEntity<GoNoGoDecisionResponse> decision(@PathVariable Long executionId){ return ResponseEntity.ok(service.getGoNoGoDecision(executionId)); }
    @GetMapping("/cab-recommendations/{executionId}") public ResponseEntity<CabRecommendationResponse> cab(@PathVariable Long executionId){ return ResponseEntity.ok(service.getCabRecommendation(executionId)); }
}
