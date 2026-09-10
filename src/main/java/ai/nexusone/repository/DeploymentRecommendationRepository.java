package ai.nexusone.repository;

import ai.nexusone.entity.DeploymentRecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeploymentRecommendationRepository
        extends JpaRepository<
        DeploymentRecommendationEntity,
        Long> {

    List<DeploymentRecommendationEntity>
    findByRepositoryNameOrderByCreatedAtDesc(
            String repositoryName
    );

    Optional<DeploymentRecommendationEntity>
    findTopByRepositoryNameOrderByCreatedAtDesc(
            String repositoryName
    );

    List<DeploymentRecommendationEntity>
    findTop10ByOrderByCreatedAtDesc();

    long countByRecommendation(
            String recommendation
    );

    long countBySeverity(
            String severity
    );

    long count();

}