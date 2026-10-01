package ai.nexusone.repository;

import ai.nexusone.entity.ChiefAiOfficerIntelligenceReport;
import ai.nexusone.enums.ChiefAiOfficerPriority;
import ai.nexusone.enums.ChiefAiOfficerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiefAiOfficerIntelligenceRepository
        extends JpaRepository<ChiefAiOfficerIntelligenceReport, Long> {

    long countByStatus(ChiefAiOfficerStatus status);
    Page<ChiefAiOfficerIntelligenceReport> findByStatus(ChiefAiOfficerStatus status, Pageable pageable);
    Page<ChiefAiOfficerIntelligenceReport> findByPriority(ChiefAiOfficerPriority priority, Pageable pageable);
    Page<ChiefAiOfficerIntelligenceReport>
    findByTitleContainingIgnoreCaseOrAiStrategyContainingIgnoreCaseOrStrategicRecommendationsContainingIgnoreCase(
            String title, String strategy, String recommendations, Pageable pageable);
}
