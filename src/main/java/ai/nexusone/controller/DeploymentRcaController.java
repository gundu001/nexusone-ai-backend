package ai.nexusone.controller;

import ai.nexusone.dto.DeploymentRcaResponse;
import ai.nexusone.service.DeploymentRcaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deployment-rca")
public class DeploymentRcaController {

    private final DeploymentRcaService service;

    public DeploymentRcaController(
            DeploymentRcaService service) {
        this.service = service;
    }

    @GetMapping("/{executionId}")
    public ResponseEntity<DeploymentRcaResponse> analyze(
            @PathVariable Long executionId) {
        return ResponseEntity.ok(service.analyze(executionId));
    }
}
