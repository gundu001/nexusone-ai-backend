package ai.nexusone.repository;
import ai.nexusone.entity.FuturePredictionIntelligenceReport;
import ai.nexusone.enums.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FuturePredictionIntelligenceRepository extends JpaRepository<FuturePredictionIntelligenceReport,Long>{
 long countByStatus(FuturePredictionStatus status);
 Page<FuturePredictionIntelligenceReport> findByStatus(FuturePredictionStatus status,Pageable pageable);
 Page<FuturePredictionIntelligenceReport> findByPriority(FuturePredictionPriority priority,Pageable pageable);
 Page<FuturePredictionIntelligenceReport> findByTitleContainingIgnoreCaseOrFutureOutlookContainingIgnoreCaseOrStrategicRecommendationsContainingIgnoreCase(String a,String b,String c,Pageable pageable);
}
