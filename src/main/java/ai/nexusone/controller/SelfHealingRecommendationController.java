package ai.nexusone.controller;

import ai.nexusone.dto.SelfHealingRecommendationResponse;
import ai.nexusone.service.SelfHealingRecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/self-healing")
public class SelfHealingRecommendationController {

    private final SelfHealingRecommendationService service;

    public SelfHealingRecommendationController(
            SelfHealingRecommendationService service) {
        this.service = service;
    }

    @GetMapping("/recommendations/{executionId}")
    public ResponseEntity<SelfHealingRecommendationResponse> recommend(
            @PathVariable Long executionId) {

        return ResponseEntity.ok(
                service.recommend(executionId)
        );
    }
}