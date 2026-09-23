package ai.nexusone.repository;
import ai.nexusone.entity.EnterpriseMemoryRecord;
import ai.nexusone.enums.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EnterpriseMemoryRepository extends JpaRepository<EnterpriseMemoryRecord,Long>{
 long countByStatus(MemoryStatus status);
 Page<EnterpriseMemoryRecord> findByTitleContainingIgnoreCaseOrContextContainingIgnoreCaseOrLearnedPatternContainingIgnoreCaseOrReasoningRuleContainingIgnoreCase(String a,String b,String c,String d,Pageable p);
 Page<EnterpriseMemoryRecord> findByMemoryType(MemoryType type,Pageable p);
 Page<EnterpriseMemoryRecord> findByStatus(MemoryStatus status,Pageable p);
 List<EnterpriseMemoryRecord> findTop10ByStatusNotOrderByImportanceScoreDescConfidenceScoreDesc(MemoryStatus status);
}
