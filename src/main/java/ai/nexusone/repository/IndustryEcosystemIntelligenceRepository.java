package ai.nexusone.repository;

import ai.nexusone.entity.IndustryEcosystemIntelligenceReport;
import ai.nexusone.enums.IndustryEcosystemIntelligenceStatus;
import ai.nexusone.enums.IndustryPriority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryEcosystemIntelligenceRepository extends JpaRepository<IndustryEcosystemIntelligenceReport, Long> {
    long countByStatus(IndustryEcosystemIntelligenceStatus status);
    Page<IndustryEcosystemIntelligenceReport> findByStatus(IndustryEcosystemIntelligenceStatus status, Pageable pageable);
    Page<IndustryEcosystemIntelligenceReport> findByPriority(IndustryPriority priority, Pageable pageable);
    Page<IndustryEcosystemIntelligenceReport> findByTitleContainingIgnoreCaseOrIndustryLandscapeContainingIgnoreCaseOrStrategicRecommendationsContainingIgnoreCase(
            String title, String narrative, String ecosystemValue, Pageable pageable);
}
