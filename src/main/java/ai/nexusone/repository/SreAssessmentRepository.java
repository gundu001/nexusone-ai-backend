package ai.nexusone.repository;

import ai.nexusone.entity.SreAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SreAssessmentRepository extends JpaRepository<SreAssessment, Long> {
    List<SreAssessment> findTop10ByOrderByAnalyzedAtDesc();
}
