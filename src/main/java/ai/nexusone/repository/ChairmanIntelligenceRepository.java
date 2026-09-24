package ai.nexusone.repository;
import ai.nexusone.entity.ChairmanIntelligenceReport; import ai.nexusone.enums.*;
import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.JpaRepository;
public interface ChairmanIntelligenceRepository extends JpaRepository<ChairmanIntelligenceReport,Long>{
 long countByStatus(ChairmanIntelligenceStatus status);
 Page<ChairmanIntelligenceReport> findByStatus(ChairmanIntelligenceStatus status,Pageable p);
 Page<ChairmanIntelligenceReport> findByPriority(ChairmanPriority priority,Pageable p);
 Page<ChairmanIntelligenceReport> findByTitleContainingIgnoreCaseOrExecutiveSummaryContainingIgnoreCaseOrInvestmentRecommendationContainingIgnoreCase(String a,String b,String c,Pageable p);
}
