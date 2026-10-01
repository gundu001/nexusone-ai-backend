package ai.nexusone.repository;

import ai.nexusone.entity.DigitalBoardMemberIntelligenceReport;
import ai.nexusone.enums.DigitalBoardMemberPriority;
import ai.nexusone.enums.DigitalBoardMemberStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DigitalBoardMemberIntelligenceRepository
        extends JpaRepository<DigitalBoardMemberIntelligenceReport, Long> {
    long countByStatus(DigitalBoardMemberStatus status);
    Page<DigitalBoardMemberIntelligenceReport> findByStatus(DigitalBoardMemberStatus status, Pageable pageable);
    Page<DigitalBoardMemberIntelligenceReport> findByPriority(DigitalBoardMemberPriority priority, Pageable pageable);
    Page<DigitalBoardMemberIntelligenceReport>
    findByTitleContainingIgnoreCaseOrBoardAgendaContainingIgnoreCaseOrBoardRecommendationsContainingIgnoreCase(
            String title, String agenda, String recommendations, Pageable pageable);
}
