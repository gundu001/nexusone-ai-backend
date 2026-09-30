package ai.nexusone.repository;

import ai.nexusone.entity.GlobalEconomicIntelligenceReport;
import ai.nexusone.enums.GlobalEconomicPriority;
import ai.nexusone.enums.GlobalEconomicStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalEconomicIntelligenceRepository
        extends JpaRepository<GlobalEconomicIntelligenceReport, Long> {

    long countByStatus(GlobalEconomicStatus status);

    Page<GlobalEconomicIntelligenceReport> findByStatus(
            GlobalEconomicStatus status, Pageable pageable);

    Page<GlobalEconomicIntelligenceReport> findByPriority(
            GlobalEconomicPriority priority, Pageable pageable);

    Page<GlobalEconomicIntelligenceReport>
            findByTitleContainingIgnoreCaseOrGlobalEconomicOutlookContainingIgnoreCaseOrStrategicRecommendationsContainingIgnoreCase(
                    String title, String outlook, String recommendations, Pageable pageable);
}
