package ai.nexusone.repository;

import ai.nexusone.entity.ChangeRiskCopilotAnalysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeRiskCopilotAnalysisRepository
        extends JpaRepository<ChangeRiskCopilotAnalysis, Long> {
    Page<ChangeRiskCopilotAnalysis> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<ChangeRiskCopilotAnalysis> findByChangeIdOrderByCreatedAtDesc(Long changeId, Pageable pageable);
}
