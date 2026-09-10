package ai.nexusone.service;

import ai.nexusone.dto.DeploymentRecommendationResult;
import ai.nexusone.entity.DeploymentRecommendationEntity;
import ai.nexusone.repository.DeploymentRecommendationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeploymentRecommendationPersistenceService {

    private final DeploymentRecommendationService
            recommendationService;

    private final DeploymentRecommendationRepository
            recommendationRepository;

    public DeploymentRecommendationPersistenceService(
            DeploymentRecommendationService
                    recommendationService,
            DeploymentRecommendationRepository
                    recommendationRepository
    ) {

        this.recommendationService =
                recommendationService;

        this.recommendationRepository =
                recommendationRepository;
    }

    @Transactional
    public DeploymentRecommendationResult
    generateAndSaveRecommendation(
            String repositoryName
    ) throws Exception {

        validateRepositoryName(
                repositoryName
        );

        String normalizedRepositoryName =
                repositoryName.trim();

        DeploymentRecommendationResult result =
                recommendationService
                        .getRecommendation(
                                normalizedRepositoryName
                        );

        validateRecommendationResult(
                result
        );

        DeploymentRecommendationEntity entity =
                mapToEntity(
                        result
                );

        recommendationRepository.save(
                entity
        );

        return result;
    }

    @Transactional(readOnly = true)
    public List<DeploymentRecommendationEntity>
    getRecommendationHistory(
            String repositoryName
    ) {

        validateRepositoryName(
                repositoryName
        );

        return recommendationRepository
                .findByRepositoryNameOrderByCreatedAtDesc(
                        repositoryName.trim()
                );
    }

    @Transactional(readOnly = true)
    public DeploymentRecommendationEntity
    getLatestRecommendation(
            String repositoryName
    ) {

        validateRepositoryName(
                repositoryName
        );

        return recommendationRepository
                .findTopByRepositoryNameOrderByCreatedAtDesc(
                        repositoryName.trim()
                )
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "No persisted recommendation was found "
                                        + "for repository: "
                                        + repositoryName
                        )
                );
    }

    @Transactional(readOnly = true)
    public List<DeploymentRecommendationEntity>
    getRecentRecommendations() {

        return recommendationRepository
                .findTop10ByOrderByCreatedAtDesc();
    }

    private DeploymentRecommendationEntity
    mapToEntity(
            DeploymentRecommendationResult result
    ) {

        DeploymentRecommendationEntity entity =
                new DeploymentRecommendationEntity();

        entity.setRepositoryName(
                result.getRepositoryName()
        );

        entity.setRiskScore(
                result.getRiskScore()
        );

        entity.setSeverity(
                result.getSeverity()
        );

        entity.setRecommendation(
                result.getRecommendation()
        );

        entity.setMessage(
                result.getMessage()
        );

        entity.setCreatedAt(
                LocalDateTime.now()
        );

        return entity;
    }

    private void validateRepositoryName(
            String repositoryName
    ) {

        if (
                repositoryName == null
                        || repositoryName.isBlank()
        ) {

            throw new IllegalArgumentException(
                    "Repository name is required."
            );
        }
    }

    private void validateRecommendationResult(
            DeploymentRecommendationResult result
    ) {

        if (result == null) {

            throw new IllegalStateException(
                    "Recommendation engine returned no result."
            );
        }

        if (
                result.getRepositoryName() == null
                        || result.getRepositoryName().isBlank()
        ) {

            throw new IllegalStateException(
                    "Recommendation result does not contain "
                            + "a repository name."
            );
        }

        if (result.getRiskScore() == null) {

            throw new IllegalStateException(
                    "Recommendation result does not contain "
                            + "a risk score."
            );
        }

        if (
                result.getSeverity() == null
                        || result.getSeverity().isBlank()
        ) {

            throw new IllegalStateException(
                    "Recommendation result does not contain "
                            + "a severity."
            );
        }

        if (
                result.getRecommendation() == null
                        || result.getRecommendation().isBlank()
        ) {

            throw new IllegalStateException(
                    "Recommendation result does not contain "
                            + "a recommendation."
            );
        }
    }
}