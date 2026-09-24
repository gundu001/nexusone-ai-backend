package ai.nexusone.repository;

import ai.nexusone.entity.CeoIntelligenceReport;
import ai.nexusone.enums.CeoIntelligenceStatus;
import ai.nexusone.enums.CeoPriority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CeoIntelligenceRepository extends JpaRepository<CeoIntelligenceReport, Long> {

    long countByStatus(CeoIntelligenceStatus status);

    Page<CeoIntelligenceReport> findByStatus(
            CeoIntelligenceStatus status,
            Pageable pageable
    );

    Page<CeoIntelligenceReport> findByPriority(
            CeoPriority priority,
            Pageable pageable
    );

    Page<CeoIntelligenceReport> findByTitleContainingIgnoreCaseOrExecutiveSummaryContainingIgnoreCaseOrCeoRecommendationContainingIgnoreCase(
            String title,
            String executiveSummary,
            String ceoRecommendation,
            Pageable pageable
    );
}
