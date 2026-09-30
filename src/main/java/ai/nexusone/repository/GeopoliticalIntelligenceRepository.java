package ai.nexusone.repository;
import ai.nexusone.entity.GeopoliticalIntelligenceReport; import ai.nexusone.enums.*;
import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.JpaRepository;
public interface GeopoliticalIntelligenceRepository extends JpaRepository<GeopoliticalIntelligenceReport,Long>{
 long countByStatus(GeopoliticalStatus status); Page<GeopoliticalIntelligenceReport> findByStatus(GeopoliticalStatus status,Pageable pageable);
 Page<GeopoliticalIntelligenceReport> findByPriority(GeopoliticalPriority priority,Pageable pageable);
 Page<GeopoliticalIntelligenceReport> findByTitleContainingIgnoreCaseOrGeopoliticalOutlookContainingIgnoreCaseOrStrategicRecommendationsContainingIgnoreCase(String a,String b,String c,Pageable pageable);
}
