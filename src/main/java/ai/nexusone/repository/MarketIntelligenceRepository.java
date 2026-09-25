package ai.nexusone.repository;
import ai.nexusone.entity.MarketIntelligenceReport;
import ai.nexusone.enums.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MarketIntelligenceRepository extends JpaRepository<MarketIntelligenceReport,Long>{
 long countByStatus(MarketIntelligenceStatus status);
 Page<MarketIntelligenceReport> findByStatus(MarketIntelligenceStatus status,Pageable pageable);
 Page<MarketIntelligenceReport> findByPriority(MarketPriority priority,Pageable pageable);
 Page<MarketIntelligenceReport> findByTitleContainingIgnoreCaseOrMarketLandscapeContainingIgnoreCaseOrCompetitorAnalysisContainingIgnoreCase(String a,String b,String c,Pageable p);
}
