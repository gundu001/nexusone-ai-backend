package ai.nexusone.controller;

import ai.nexusone.dto.request.*;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.AutonomousRemediationHistory;
import ai.nexusone.service.AutonomousRemediationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/autonomous-remediation")
public class AutonomousRemediationController {
    private final AutonomousRemediationService service;

    public AutonomousRemediationController(AutonomousRemediationService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public AutonomousRemediationOverviewResponse overview() { return service.overview(); }

    @GetMapping("/candidates")
    public List<AutonomousRemediationCandidateResponse> candidates() { return service.candidates(); }

    @GetMapping("/plans/{application}")
    public AutonomousRemediationPlanResponse plan(@PathVariable String application) {
        return service.plan(application);
    }

    @PostMapping("/analyze")
    public AutonomousRemediationPlanResponse analyze(
            @Valid @RequestBody AutonomousRemediationAnalyzeRequest request) {
        return service.analyze(request);
    }

    @PostMapping("/approve")
    public AutonomousRemediationActionResponse approve(
            @Valid @RequestBody AutonomousRemediationApproveRequest request) {
        return service.approve(request);
    }

    @PostMapping("/execute")
    public AutonomousRemediationActionResponse execute(
            @Valid @RequestBody AutonomousRemediationExecuteRequest request) {
        return service.execute(request);
    }

    @PostMapping("/rollback")
    public AutonomousRemediationActionResponse rollback(
            @Valid @RequestBody AutonomousRemediationRollbackRequest request) {
        return service.rollback(request);
    }

    @GetMapping("/history")
    public Page<AutonomousRemediationHistory> history(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return service.history(page, size);
    }

    @GetMapping("/applications/{application}/history")
    public Page<AutonomousRemediationHistory> historyByApplication(
            @PathVariable String application,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return service.historyByApplication(application, page, size);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> notFound(NoSuchElementException ex) {
        return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> invalidState(IllegalStateException ex) {
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }
}
