package ai.nexusone.repository;

import ai.nexusone.entity.AutonomousEnterpriseRevenueIntelligence;
import org.springframework.data.jpa.repository.*;

public interface AutonomousEnterpriseRevenueIntelligenceRepository extends JpaRepository<AutonomousEnterpriseRevenueIntelligence, Long>, JpaSpecificationExecutor<AutonomousEnterpriseRevenueIntelligence> {
}
