package ai.nexusone.repository;

import ai.nexusone.entity.AutonomousEnterpriseConsciousnessReport;
import ai.nexusone.enums.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutonomousEnterpriseConsciousnessRepository
        extends JpaRepository<AutonomousEnterpriseConsciousnessReport, Long> {
    long countByStatus(AutonomousEnterpriseConsciousnessStatus status);
    Page<AutonomousEnterpriseConsciousnessReport> findByStatus(
            AutonomousEnterpriseConsciousnessStatus status, Pageable pageable);
    Page<AutonomousEnterpriseConsciousnessReport> findByPriority(
            AutonomousEnterpriseConsciousnessPriority priority, Pageable pageable);
    Page<AutonomousEnterpriseConsciousnessReport>
    findByTitleContainingIgnoreCaseOrEnterpriseAwarenessContainingIgnoreCaseOrConsciousnessRecommendationsContainingIgnoreCase(
            String title, String awareness, String recommendations, Pageable pageable);
}
