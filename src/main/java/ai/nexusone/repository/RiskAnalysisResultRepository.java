package ai.nexusone.repository;

import ai.nexusone.entity.RiskAnalysisResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RiskAnalysisResultRepository
        extends JpaRepository<
        RiskAnalysisResultEntity,
        Long> {

    long countBySeverity(
            String severity);

    long countByRecommendation(
            String recommendation);

    Optional<RiskAnalysisResultEntity>
    findTopByOrderByAnalysisTimeDesc();

    List<RiskAnalysisResultEntity>
    findTop10ByOrderByAnalysisTimeDesc();

    @Query("""
            SELECT COALESCE(
                   AVG(r.riskScore),
                   0
            )
            FROM RiskAnalysisResultEntity r
            """)
    Double calculateAverageRiskScore();
}