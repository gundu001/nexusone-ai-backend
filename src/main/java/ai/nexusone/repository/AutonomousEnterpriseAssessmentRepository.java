package ai.nexusone.repository;
import ai.nexusone.entity.AutonomousEnterpriseAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AutonomousEnterpriseAssessmentRepository extends JpaRepository<AutonomousEnterpriseAssessment,Long>{
 long countByRiskLevel(String riskLevel);
 long countByStatus(String status);
 List<AutonomousEnterpriseAssessment> findTop10ByOrderByCreatedAtDesc();
}
