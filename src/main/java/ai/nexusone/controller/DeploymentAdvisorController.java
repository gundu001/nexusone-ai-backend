package ai.nexusone.controller;

import ai.nexusone.dto.DeploymentAdvisorResponse;
import ai.nexusone.service.DeploymentAdvisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deployment-advisor")
public class DeploymentAdvisorController {

    private final DeploymentAdvisorService service;

    public DeploymentAdvisorController(DeploymentAdvisorService service) {
        this.service = service;
    }

    @GetMapping("/{executionId}")
    public ResponseEntity<DeploymentAdvisorResponse> getInsight(
            @PathVariable Long executionId) {
        return ResponseEntity.ok(service.getInsight(executionId));
    }
}
