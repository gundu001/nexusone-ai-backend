package ai.nexusone.repository;
import ai.nexusone.entity.IntelligenceMeshEvent;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IntelligenceMeshEventRepository extends JpaRepository<IntelligenceMeshEvent,Long>{
 long countByStatus(String status);
 long countByApprovalRequiredTrueAndStatus(String status);
}
