package ai.nexusone.controller;

import ai.nexusone.dto.orchestrator.*;
import ai.nexusone.service.ReleaseOrchestratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/release-orchestrator")
@CrossOrigin(origins = "*")
public class ReleaseOrchestratorController {
    private final ReleaseOrchestratorService service;

    public ReleaseOrchestratorController(ReleaseOrchestratorService service) {
        this.service = service;
    }

    @GetMapping("/overview/{executionId}")
    public ResponseEntity<OrchestratorOverviewResponse> overview(@PathVariable Long executionId) {
        return ResponseEntity.ok(service.getOverview(executionId));
    }

    @PostMapping("/analyze/{executionId}")
    public ResponseEntity<ReleaseAnalysisResponse> analyze(@PathVariable Long executionId) {
        return ResponseEntity.ok(service.analyzeRelease(executionId));
    }

    @PostMapping("/orchestrate/{executionId}")
    public ResponseEntity<OrchestrationDecisionResponse> orchestrate(@PathVariable Long executionId) {
        return ResponseEntity.ok(service.executeOrchestration(executionId));
    }

    @GetMapping("/history/{executionId}")
    public ResponseEntity<List<OrchestrationHistoryResponse>> history(@PathVariable Long executionId) {
        return ResponseEntity.ok(service.getHistory(executionId));
    }

    @GetMapping("/flows")
    public ResponseEntity<List<ReleaseFlowResponse>> flows() {
        return ResponseEntity.ok(service.getFlows());
    }

    @GetMapping("/workflows")
    public ResponseEntity<List<WorkflowDefinitionResponse>> workflows() {
        return ResponseEntity.ok(service.getWorkflows());
    }

    @PostMapping("/start/{executionId}")
    public ResponseEntity<WorkflowStartResponse> start(@PathVariable Long executionId) {
        return ResponseEntity.ok(service.startWorkflow(executionId));
    }
}
