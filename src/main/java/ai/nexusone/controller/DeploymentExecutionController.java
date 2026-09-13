package ai.nexusone.controller;

import ai.nexusone.dto.DeploymentExecutionRequest;
import ai.nexusone.dto.DeploymentExecutionResponse;
import ai.nexusone.dto.DeploymentMetricsResponse;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.service.DeploymentExecutionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/executions")
public class DeploymentExecutionController {

    private final DeploymentExecutionService service;

    public DeploymentExecutionController(
            DeploymentExecutionService service) {
        this.service = service;
    }

    @PostMapping("/execute")
    public ResponseEntity<DeploymentExecutionResponse> execute(
            @Valid @RequestBody DeploymentExecutionRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.executeDeployment(request));
    }

    @GetMapping("/history")
    public ResponseEntity<List<DeploymentExecutionResponse>> history(
            @RequestParam(required = false)
            String repositoryName) {

        return ResponseEntity.ok(
                service.getHistory(repositoryName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeploymentExecutionResponse>
    getExecution(@PathVariable Long id) {

        return ResponseEntity.ok(
                service.getExecution(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DeploymentExecutionResponse>
    updateStatus(
            @PathVariable Long id,
            @RequestParam DeploymentStatus status) {

        return ResponseEntity.ok(
                service.updateStatus(id, status));
    }

    // ===== Phase 4.7 =====

    @GetMapping("/metrics")
    public ResponseEntity<DeploymentMetricsResponse>
    getMetrics() {

        return ResponseEntity.ok(
                service.getMetrics());
    }

    @GetMapping("/recent")
    public ResponseEntity<List<DeploymentExecutionResponse>>
    getRecentExecutions() {

        return ResponseEntity.ok(
                service.getRecentExecutions());
    }
}
