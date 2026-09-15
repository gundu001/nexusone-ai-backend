package ai.nexusone.repository;

import ai.nexusone.entity.IncidentAnalysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentAnalysisRepository
        extends JpaRepository<IncidentAnalysis, Long> {

    Page<IncidentAnalysis>
    findAllByOrderByCreatedAtDesc(
            Pageable pageable);

    Page<IncidentAnalysis>
    findByIncidentIdOrderByCreatedAtDesc(
            String incidentId,
            Pageable pageable);
}