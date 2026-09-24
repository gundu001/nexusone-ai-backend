package ai.nexusone.repository;

import ai.nexusone.entity.ExecutiveAdvisory;
import ai.nexusone.enums.ExecutiveAdvisoryStatus;
import ai.nexusone.enums.ExecutivePriority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutiveAdvisoryRepository extends JpaRepository<ExecutiveAdvisory, Long> {
    long countByStatus(ExecutiveAdvisoryStatus status);
    Page<ExecutiveAdvisory> findByStatus(ExecutiveAdvisoryStatus status, Pageable pageable);
    Page<ExecutiveAdvisory> findByPriority(ExecutivePriority priority, Pageable pageable);
    Page<ExecutiveAdvisory> findByTitleContainingIgnoreCaseOrStrategicObjectiveContainingIgnoreCaseOrExecutiveRecommendationContainingIgnoreCase(
            String title, String objective, String recommendation, Pageable pageable);
}
