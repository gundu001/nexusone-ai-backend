package ai.nexusone.repository;
import ai.nexusone.entity.AutonomousEnterpriseSustainability;
import org.springframework.data.jpa.repository.*;
public interface AutonomousEnterpriseSustainabilityRepository extends JpaRepository<AutonomousEnterpriseSustainability,Long>,JpaSpecificationExecutor<AutonomousEnterpriseSustainability> {}
