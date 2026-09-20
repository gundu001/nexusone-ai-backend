package ai.nexusone.repository;

import ai.nexusone.entity.PlatformEngineeringAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlatformEngineeringAssessmentRepository extends JpaRepository<PlatformEngineeringAssessment, Long> {
    long countByStatus(String status);
    List<PlatformEngineeringAssessment> findTop10ByOrderByAssessedAtDesc();
}
