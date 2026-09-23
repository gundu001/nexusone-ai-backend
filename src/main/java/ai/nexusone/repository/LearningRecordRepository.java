package ai.nexusone.repository;
import ai.nexusone.entity.LearningRecord; import ai.nexusone.enums.LearningStatus; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface LearningRecordRepository extends JpaRepository<LearningRecord,Long>{ List<LearningRecord> findByOutcomeIdOrderByCreatedAtDesc(Long outcomeId); long countByStatus(LearningStatus status); }
