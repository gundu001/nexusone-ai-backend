package ai.nexusone.repository;

import ai.nexusone.entity.ReleaseImpactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReleaseImpactRepository extends JpaRepository<ReleaseImpactEntity,Long> {
    Optional<ReleaseImpactEntity> findTopByRepositoryNameIgnoreCaseOrderByAnalyzedAtDesc(String repositoryName);
    List<ReleaseImpactEntity> findTop20ByOrderByAnalyzedAtDesc();
    List<ReleaseImpactEntity> findTop20ByRepositoryNameIgnoreCaseOrderByAnalyzedAtDesc(String repositoryName);
}
