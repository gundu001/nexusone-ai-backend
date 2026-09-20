package ai.nexusone.repository;
import ai.nexusone.entity.GovernanceSecurityAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface GovernanceSecurityAssessmentRepository extends JpaRepository<GovernanceSecurityAssessment,Long>{
 long countByStatus(String status);
 long countByRiskLevel(String riskLevel);
 List<GovernanceSecurityAssessment> findTop10ByOrderByAssessedAtDesc();
}
