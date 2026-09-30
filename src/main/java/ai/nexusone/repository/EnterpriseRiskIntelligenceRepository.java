package ai.nexusone.repository;

import ai.nexusone.entity.EnterpriseRiskIntelligenceReport;
import ai.nexusone.enums.EnterpriseRiskPriority;
import ai.nexusone.enums.EnterpriseRiskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRiskIntelligenceRepository
        extends JpaRepository<EnterpriseRiskIntelligenceReport, Long> {

    long countByStatus(EnterpriseRiskStatus status);

    Page<EnterpriseRiskIntelligenceReport> findByStatus(
            EnterpriseRiskStatus status, Pageable pageable);

    Page<EnterpriseRiskIntelligenceReport> findByPriority(
            EnterpriseRiskPriority priority, Pageable pageable);

    Page<EnterpriseRiskIntelligenceReport>
            findByTitleContainingIgnoreCaseOrEnterpriseRiskOutlookContainingIgnoreCaseOrRiskMitigationRecommendationsContainingIgnoreCase(
                    String title, String outlook, String recommendations, Pageable pageable);
}
