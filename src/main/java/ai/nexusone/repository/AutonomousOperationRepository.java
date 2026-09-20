package ai.nexusone.repository;

import ai.nexusone.entity.AutonomousOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutonomousOperationRepository extends JpaRepository<AutonomousOperation, Long> {
    long countByStatus(String status);
}
