package ai.nexusone.controller;

import ai.nexusone.dto.DeploymentRecommendationResult;
import ai.nexusone.entity.DeploymentRecommendationEntity;
import ai.nexusone.service.DeploymentRecommendationPersistenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        "/api/repositories"
)
@CrossOrigin("*")
public class DeploymentRecommendationController {

    private final DeploymentRecommendationPersistenceService
            persistenceService;

    public DeploymentRecommendationController(
            DeploymentRecommendationPersistenceService
                    persistenceService
    ) {

        this.persistenceService =
                persistenceService;
    }

    @GetMapping(
            "/recommendation"
    )
    public ResponseEntity<
            DeploymentRecommendationResult>
    generateRecommendation(
            @RequestParam
            String repoName
    ) throws Exception {

        DeploymentRecommendationResult result =
                persistenceService
                        .generateAndSaveRecommendation(
                                repoName
                        );

        return ResponseEntity.ok(
                result
        );
    }

    @GetMapping(
            "/recommendations/history"
    )
    public ResponseEntity<
            List<DeploymentRecommendationEntity>>
    getRecommendationHistory(
            @RequestParam
            String repoName
    ) {

        List<DeploymentRecommendationEntity> history =
                persistenceService
                        .getRecommendationHistory(
                                repoName
                        );

        return ResponseEntity.ok(
                history
        );
    }

    @GetMapping(
            "/recommendations/latest"
    )
    public ResponseEntity<
            DeploymentRecommendationEntity>
    getLatestRecommendation(
            @RequestParam
            String repoName
    ) {

        DeploymentRecommendationEntity latest =
                persistenceService
                        .getLatestRecommendation(
                                repoName
                        );

        return ResponseEntity.ok(
                latest
        );
    }

    @GetMapping(
            "/recommendations/recent"
    )
    public ResponseEntity<
            List<DeploymentRecommendationEntity>>
    getRecentRecommendations() {

        List<DeploymentRecommendationEntity> results =
                persistenceService
                        .getRecentRecommendations();

        return ResponseEntity.ok(
                results
        );
    }
}