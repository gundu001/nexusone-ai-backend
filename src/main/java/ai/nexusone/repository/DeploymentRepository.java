package ai.nexusone.repository;
import ai.nexusone.entity.DeploymentEntity; import org.springframework.data.jpa.repository.JpaRepository;
public interface DeploymentRepository extends JpaRepository<DeploymentEntity,String>{}
