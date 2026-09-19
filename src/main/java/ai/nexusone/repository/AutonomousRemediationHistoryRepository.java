package ai.nexusone.repository;

import ai.nexusone.entity.AutonomousRemediationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutonomousRemediationHistoryRepository
        extends JpaRepository<AutonomousRemediationHistory, Long> {

    Page<AutonomousRemediationHistory> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<AutonomousRemediationHistory> findByApplicationOrderByCreatedAtDesc(
            String application, Pageable pageable);
    long countByActionTypeAndStatus(String actionType, String status);
    long countByActionType(String actionType);
}
