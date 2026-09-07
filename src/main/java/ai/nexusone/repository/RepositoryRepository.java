package ai.nexusone.repository;

import ai.nexusone.entity.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository
        extends JpaRepository<RepositoryEntity, Long> {
}