package ai.nexusone.repository;

import ai.nexusone.entity.DeploymentReadinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeploymentReadinessRepository extends JpaRepository<DeploymentReadinessEntity, Long> {
    Optional<DeploymentReadinessEntity> findTopByRepositoryNameIgnoreCaseOrderByGeneratedAtDesc(String repositoryName);
    List<DeploymentReadinessEntity> findTop20ByOrderByGeneratedAtDesc();
    List<DeploymentReadinessEntity> findTop20ByRepositoryNameIgnoreCaseOrderByGeneratedAtDesc(String repositoryName);
}
