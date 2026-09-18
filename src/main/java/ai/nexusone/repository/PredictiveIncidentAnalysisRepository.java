package ai.nexusone.repository;

import ai.nexusone.entity.PredictiveIncidentAnalysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictiveIncidentAnalysisRepository extends JpaRepository<PredictiveIncidentAnalysis, Long> {
    Page<PredictiveIncidentAnalysis> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<PredictiveIncidentAnalysis> findByApplicationOrderByCreatedAtDesc(String application, Pageable pageable);
}
