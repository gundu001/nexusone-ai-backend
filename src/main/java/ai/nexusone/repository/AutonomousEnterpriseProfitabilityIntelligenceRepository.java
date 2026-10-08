package ai.nexusone.repository;

import ai.nexusone.entity.AutonomousEnterpriseProfitabilityIntelligence;
import org.springframework.data.jpa.repository.*;

public interface AutonomousEnterpriseProfitabilityIntelligenceRepository
        extends JpaRepository<AutonomousEnterpriseProfitabilityIntelligence, Long>, JpaSpecificationExecutor<AutonomousEnterpriseProfitabilityIntelligence> {
}
