package ai.nexusone.repository;
import ai.nexusone.entity.StrategicEnterpriseBrainReport;
import ai.nexusone.enums.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StrategicEnterpriseBrainRepository extends JpaRepository<StrategicEnterpriseBrainReport,Long>{
 long countByStatus(StrategicEnterpriseBrainStatus status);
 Page<StrategicEnterpriseBrainReport> findByStatus(StrategicEnterpriseBrainStatus status,Pageable pageable);
 Page<StrategicEnterpriseBrainReport> findByPriority(StrategicEnterpriseBrainPriority priority,Pageable pageable);
 Page<StrategicEnterpriseBrainReport> findByTitleContainingIgnoreCaseOrStrategicVisionContainingIgnoreCaseOrStrategicRecommendationsContainingIgnoreCase(String a,String b,String c,Pageable p);
}
