package ai.nexusone.service;

import ai.nexusone.dto.RecommendationDashboardSummary;
import ai.nexusone.repository.DeploymentRecommendationRepository;
import org.springframework.stereotype.Service;

@Service
public class RecommendationDashboardService {

    private final DeploymentRecommendationRepository repository;

    public RecommendationDashboardService(
            DeploymentRecommendationRepository repository
    ) {
        this.repository = repository;
    }

    public RecommendationDashboardSummary getSummary() {

        RecommendationDashboardSummary summary =
                new RecommendationDashboardSummary();

        summary.setSafeToDeploy(
                repository.countByRecommendation(
                        "SAFE_TO_DEPLOY"
                )
        );

        summary.setDeployWithCaution(
                repository.countByRecommendation(
                        "DEPLOY_WITH_CAUTION"
                )
        );

        summary.setManualReviewRequired(
                repository.countByRecommendation(
                        "MANUAL_REVIEW_REQUIRED"
                )
        );

        return summary;
    }
}