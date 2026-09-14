package ai.nexusone.controller;

import ai.nexusone.dto.response.ReleaseGateResponse;
import ai.nexusone.dto.response.ReleaseReadinessOverviewResponse;
import ai.nexusone.dto.response.ReleaseReadinessRecommendationResponse;
import ai.nexusone.dto.response.ReleaseReadinessScoreResponse;
import ai.nexusone.dto.response.ReleaseReadinessTrendResponse;
import ai.nexusone.service.ReleaseReadinessScoringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/release-readiness")
public class ReleaseReadinessScoringController {

    private final ReleaseReadinessScoringService service;

    public ReleaseReadinessScoringController(ReleaseReadinessScoringService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public ResponseEntity<ReleaseReadinessOverviewResponse> overview() {
        return ResponseEntity.ok(service.getOverview());
    }

    @GetMapping("/scores")
    public ResponseEntity<List<ReleaseReadinessScoreResponse>> scores() {
        return ResponseEntity.ok(service.getScores());
    }

    @GetMapping("/gates")
    public ResponseEntity<List<ReleaseGateResponse>> gates() {
        return ResponseEntity.ok(service.getGates());
    }

    @GetMapping("/trends")
    public ResponseEntity<ReleaseReadinessTrendResponse> trends() {
        return ResponseEntity.ok(service.getTrends());
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<ReleaseReadinessRecommendationResponse>> recommendations() {
        return ResponseEntity.ok(service.getRecommendations());
    }
}
