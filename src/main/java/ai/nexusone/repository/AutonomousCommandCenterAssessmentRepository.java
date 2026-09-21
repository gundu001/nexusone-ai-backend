package ai.nexusone.repository;

import ai.nexusone.entity.AutonomousCommandCenterAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutonomousCommandCenterAssessmentRepository extends JpaRepository<AutonomousCommandCenterAssessment, Long> {
    long countByRiskLevel(String riskLevel);
    long countByStatus(String status);
    List<AutonomousCommandCenterAssessment> findTop10ByOrderByCreatedAtDesc();
}
