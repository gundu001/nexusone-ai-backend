package ai.nexusone.repository;
import ai.nexusone.entity.FinOpsAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FinOpsAssessmentRepository extends JpaRepository<FinOpsAssessment,Long>{
 long countByBudgetStatus(String status);
 long countByRiskLevel(String riskLevel);
 List<FinOpsAssessment> findTop10ByOrderByAssessedAtDesc();
}
