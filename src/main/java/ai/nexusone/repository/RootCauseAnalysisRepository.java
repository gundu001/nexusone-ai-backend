package ai.nexusone.repository;

import ai.nexusone.entity.RootCauseAnalysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootCauseAnalysisRepository extends JpaRepository<RootCauseAnalysis, Long> {
    Page<RootCauseAnalysis> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<RootCauseAnalysis> findByIncidentIdOrderByCreatedAtDesc(String incidentId, Pageable pageable);
    long countByConfidenceGreaterThanEqual(double confidence);
}
