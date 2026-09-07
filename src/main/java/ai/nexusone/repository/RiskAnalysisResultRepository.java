package ai.nexusone.repository;

import ai.nexusone.entity.RiskAnalysisResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskAnalysisResultRepository
        extends JpaRepository<RiskAnalysisResultEntity, Long> {

    long countBySeverity(String severity);
}