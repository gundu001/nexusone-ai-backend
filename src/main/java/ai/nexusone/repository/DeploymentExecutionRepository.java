package ai.nexusone.repository;

import ai.nexusone.entity.DeploymentExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeploymentExecutionRepository extends JpaRepository<DeploymentExecutionEntity, Long> {
    List<DeploymentExecutionEntity> findAllByOrderByStartedAtDesc();
    List<DeploymentExecutionEntity> findByRepositoryNameIgnoreCaseOrderByStartedAtDesc(String repositoryName);
}
