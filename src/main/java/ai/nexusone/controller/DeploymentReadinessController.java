package ai.nexusone.controller;

import ai.nexusone.dto.DeploymentReadinessResult;
import ai.nexusone.service.DeploymentReadinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deployments/readiness")
public class DeploymentReadinessController {
    private final DeploymentReadinessService service;

    public DeploymentReadinessController(DeploymentReadinessService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeploymentReadinessResult> analyze(@RequestParam String repoName) {
        return ResponseEntity.ok(service.analyze(repoName));
    }

    @GetMapping("/latest")
    public ResponseEntity<DeploymentReadinessResult> latest(@RequestParam String repoName) {
        return ResponseEntity.ok(service.latest(repoName));
    }

    @GetMapping("/history")
    public ResponseEntity<List<DeploymentReadinessResult>> history(
            @RequestParam(required = false) String repoName) {
        return ResponseEntity.ok(service.history(repoName));
    }
}
