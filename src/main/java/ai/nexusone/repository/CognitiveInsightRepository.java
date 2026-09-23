package ai.nexusone.repository;

import ai.nexusone.entity.CognitiveInsight;
import ai.nexusone.enums.InsightStatus;
import ai.nexusone.enums.InsightType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CognitiveInsightRepository extends JpaRepository<CognitiveInsight, Long> {

    long countByStatus(InsightStatus status);

    long countByInsightType(InsightType insightType);

    Page<CognitiveInsight> findByInsightType(
            InsightType insightType,
            Pageable pageable
    );

    Page<CognitiveInsight> findByStatus(
            InsightStatus status,
            Pageable pageable
    );

    @Query("""
       SELECT c
       FROM CognitiveInsight c
       WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
       """)
    Page<CognitiveInsight> searchByKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );


}
