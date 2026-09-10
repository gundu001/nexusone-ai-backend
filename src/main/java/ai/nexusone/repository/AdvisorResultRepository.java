package ai.nexusone.repository;
import ai.nexusone.entity.AdvisorResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface AdvisorResultRepository extends JpaRepository<AdvisorResultEntity, Long> {
    List<AdvisorResultEntity> findTop20ByOrderByGeneratedAtDesc();
    List<AdvisorResultEntity> findTop20ByRepositoryNameIgnoreCaseOrderByGeneratedAtDesc(String repositoryName);
    Optional<AdvisorResultEntity> findTopByRepositoryNameIgnoreCaseOrderByGeneratedAtDesc(String repositoryName);
}
