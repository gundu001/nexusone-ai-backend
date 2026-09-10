package ai.nexusone.controller;

import ai.nexusone.dto.AdvisorHistoryResponse;
import ai.nexusone.dto.AdvisorResult;
import ai.nexusone.service.AdvisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisor")
public class AdvisorController {

    private final AdvisorService service;

    public AdvisorController(AdvisorService service) {
        this.service = service;
    }

    /**
     * Generate AI deployment recommendation
     */
    @PostMapping("/deployment")
    public ResponseEntity<AdvisorResult> generate(
            @RequestParam String repoName) {

        return ResponseEntity.ok(
                service.generate(repoName)
        );
    }

    /**
     * Phase 4.5.2 - Deployment Simulation Endpoint
     */
    @PostMapping("/simulate")
    public ResponseEntity<AdvisorResult> simulate(
            @RequestParam String repoName) {

        return ResponseEntity.ok(
                service.simulate(repoName)
        );
    }

    /**
     * Get latest advisor recommendation
     */
    @GetMapping("/latest")
    public ResponseEntity<AdvisorResult> latest(
            @RequestParam String repoName) {

        return ResponseEntity.ok(
                service.latest(repoName)
        );
    }

    /**
     * Get advisor history
     */
    @GetMapping("/history")
    public ResponseEntity<List<AdvisorHistoryResponse>> history(
            @RequestParam(required = false) String repoName) {

        if (repoName == null || repoName.isBlank()) {
            return ResponseEntity.ok(
                    service.history()
            );
        }

        return ResponseEntity.ok(
                service.history(repoName)
        );
    }
}