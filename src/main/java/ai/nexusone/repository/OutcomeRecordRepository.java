package ai.nexusone.repository;

import ai.nexusone.entity.OutcomeRecord;
import ai.nexusone.enums.OutcomeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OutcomeRecordRepository extends JpaRepository<OutcomeRecord, Long> {
    List<OutcomeRecord> findByExecutionIdOrderByCreatedAtDesc(Long executionId);
    long countByStatus(OutcomeStatus status);
}
