package ai.nexusone.repository;
import ai.nexusone.entity.AutonomousEnterpriseResilience;
import org.springframework.data.jpa.repository.*;
public interface AutonomousEnterpriseResilienceRepository extends JpaRepository<AutonomousEnterpriseResilience,Long>,JpaSpecificationExecutor<AutonomousEnterpriseResilience> {}
