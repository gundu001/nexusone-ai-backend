package ai.nexusone.repository;

import ai.nexusone.entity.AgentExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgentExecutionRepository extends JpaRepository<AgentExecution, Long> {
    long countByExecutionStatus(String executionStatus);
    long countByApprovalStatus(String approvalStatus);
    long countByAutomatedTrue();
    List<AgentExecution> findTop10ByOrderByCreatedAtDesc();
}
