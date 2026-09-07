package ai.nexusone.repository;

import ai.nexusone.entity.DeploymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeploymentRepository extends JpaRepository<DeploymentEntity, Long> {
    List<DeploymentEntity> findByRepositoryNameOrderByRequestedAtDesc(String repositoryName);
    List<DeploymentEntity> findAllByOrderByRequestedAtDesc();
    long countByStatus(String status);
}
