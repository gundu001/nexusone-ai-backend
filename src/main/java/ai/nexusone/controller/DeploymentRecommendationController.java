package ai.nexusone.controller;

import ai.nexusone.dto.DeploymentRecommendationResult;
import ai.nexusone.service.DeploymentRecommendationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/repositories")
@CrossOrigin("*")
public class DeploymentRecommendationController {

    private final DeploymentRecommendationService service;

    public DeploymentRecommendationController(
            DeploymentRecommendationService service) {

        this.service = service;
    }

    @GetMapping("/recommendation")
    public DeploymentRecommendationResult recommendation(
            @RequestParam String repoName)
            throws Exception {

        return service.getRecommendation(repoName);
    }
}