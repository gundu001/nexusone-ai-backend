package ai.nexusone.repository;

import ai.nexusone.entity.AutonomousEnterpriseBusinessStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AutonomousEnterpriseBusinessStrategyRepository
        extends JpaRepository<AutonomousEnterpriseBusinessStrategy, Long>,
        JpaSpecificationExecutor<AutonomousEnterpriseBusinessStrategy> {
}
