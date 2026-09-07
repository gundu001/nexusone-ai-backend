package ai.nexusone.repository;

import ai.nexusone.entity.RepositoryScanResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryScanResultRepository
        extends JpaRepository<
        RepositoryScanResultEntity,
        Long> {
}