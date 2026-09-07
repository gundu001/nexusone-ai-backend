package ai.nexusone.repository;

import ai.nexusone.entity.DeploymentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeploymentRepository
        extends JpaRepository<DeploymentEntity, String> {

}