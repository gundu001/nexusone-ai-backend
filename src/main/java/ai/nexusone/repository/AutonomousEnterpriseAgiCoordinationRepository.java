package ai.nexusone.repository;

import ai.nexusone.entity.AutonomousEnterpriseAgiCoordinationReport;
import ai.nexusone.enums.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutonomousEnterpriseAgiCoordinationRepository extends JpaRepository<AutonomousEnterpriseAgiCoordinationReport, Long> {
    long countByStatus(AutonomousEnterpriseAgiCoordinationStatus status);
    Page<AutonomousEnterpriseAgiCoordinationReport> findByStatus(AutonomousEnterpriseAgiCoordinationStatus status, Pageable pageable);
    Page<AutonomousEnterpriseAgiCoordinationReport> findByPriority(AutonomousEnterpriseAgiCoordinationPriority priority, Pageable pageable);
    Page<AutonomousEnterpriseAgiCoordinationReport> findByTitleContainingIgnoreCaseOrAgentCoordinationContainingIgnoreCaseOrCoordinationRecommendationsContainingIgnoreCase(
            String title, String coordination, String recommendations, Pageable pageable);
}
