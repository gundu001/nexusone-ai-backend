package ai.nexusone.repository;

import ai.nexusone.entity.DeliveryCopilotAnalysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryCopilotAnalysisRepository extends JpaRepository<DeliveryCopilotAnalysis, Long> {
    Page<DeliveryCopilotAnalysis> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<DeliveryCopilotAnalysis> findByRepositoryNameIgnoreCaseOrderByCreatedAtDesc(
            String repositoryName, Pageable pageable);
}
