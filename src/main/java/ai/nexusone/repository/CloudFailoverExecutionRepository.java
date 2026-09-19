package ai.nexusone.repository;
import ai.nexusone.entity.CloudFailoverExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CloudFailoverExecutionRepository extends JpaRepository<CloudFailoverExecution, Long> {
    List<CloudFailoverExecution> findTop20ByOrderByStartedAtDesc();
}
