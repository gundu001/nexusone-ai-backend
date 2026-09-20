package ai.nexusone.repository;

import ai.nexusone.entity.ExecutiveIntelligenceAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExecutiveIntelligenceAssessmentRepository extends JpaRepository<ExecutiveIntelligenceAssessment,Long>{
 long countByRiskLevel(String riskLevel);
 long countByStatus(String status);
 List<ExecutiveIntelligenceAssessment> findTop10ByOrderByGeneratedAtDesc();
}
