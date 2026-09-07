package ai.nexusone.controller;

import ai.nexusone.dto.DeploymentRequest;
import ai.nexusone.dto.DeploymentResponse;
import ai.nexusone.service.DeploymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/deployments")
@CrossOrigin(origins = "http://localhost:5173")
public class DeploymentController {

    private final DeploymentService deploymentService;

    public DeploymentController(DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @PostMapping
    public ResponseEntity<DeploymentResponse> createDeployment(
            @Valid @RequestBody DeploymentRequest request) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deploymentService.createDeployment(request));
    }

    @GetMapping("/{deploymentId}")
    public ResponseEntity<DeploymentResponse> getDeployment(@PathVariable Long deploymentId) {
        return ResponseEntity.ok(deploymentService.getDeployment(deploymentId));
    }

    @GetMapping
    public ResponseEntity<List<DeploymentResponse>> getDeployments(
            @RequestParam(name = "repositoryName", required = false) String repositoryName) {
        if (repositoryName == null || repositoryName.isBlank()) {
            return ResponseEntity.ok(deploymentService.getAllDeployments());
        }
        return ResponseEntity.ok(deploymentService.getDeploymentsByRepository(repositoryName));
    }

    @PatchMapping("/{deploymentId}/status")
    public ResponseEntity<DeploymentResponse> updateStatus(
            @PathVariable Long deploymentId,
            @RequestBody Map<String, String> request) {
        return ResponseEntity.ok(
                deploymentService.updateDeploymentStatus(deploymentId, request.get("status"))
        );
    }
}
