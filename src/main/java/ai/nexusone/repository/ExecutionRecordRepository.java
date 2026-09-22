package ai.nexusone.repository;

import ai.nexusone.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExecutionRecordRepository extends JpaRepository<ExecutionRecord, Long> {
    Optional<ExecutionRecord> findByIdempotencyKey(String idempotencyKey);
    long countByStatus(ExecutionStatus status);
}
