package ai.nexusone.repository;

import ai.nexusone.entity.EnterpriseDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnterpriseDecisionRepository extends JpaRepository<EnterpriseDecision, Long> {
    long countByStatus(String status);
    List<EnterpriseDecision> findByStatusOrderByCreatedAtDesc(String status);
}
