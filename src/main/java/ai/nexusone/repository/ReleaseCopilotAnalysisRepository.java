package ai.nexusone.repository;
import ai.nexusone.entity.ReleaseCopilotAnalysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReleaseCopilotAnalysisRepository extends JpaRepository<ReleaseCopilotAnalysis,Long>{
 Page<ReleaseCopilotAnalysis> findByReleaseIdOrderByCreatedAtDesc(Long releaseId,Pageable pageable);
}
