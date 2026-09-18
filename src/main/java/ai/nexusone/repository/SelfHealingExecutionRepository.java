package ai.nexusone.repository;

import ai.nexusone.entity.SelfHealingExecution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelfHealingExecutionRepository extends JpaRepository<SelfHealingExecution, Long> {
    Page<SelfHealingExecution> findAllByOrderByExecutedAtDesc(Pageable pageable);
    Page<SelfHealingExecution> findByApplicationOrderByExecutedAtDesc(String application, Pageable pageable);
}
