package ai.nexusone.repository;

import ai.nexusone.entity.InvestorIntelligenceReport;
import ai.nexusone.enums.InvestorIntelligenceStatus;
import ai.nexusone.enums.InvestorPriority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorIntelligenceRepository extends JpaRepository<InvestorIntelligenceReport, Long> {
    long countByStatus(InvestorIntelligenceStatus status);
    Page<InvestorIntelligenceReport> findByStatus(InvestorIntelligenceStatus status, Pageable pageable);
    Page<InvestorIntelligenceReport> findByPriority(InvestorPriority priority, Pageable pageable);
    Page<InvestorIntelligenceReport> findByTitleContainingIgnoreCaseOrInvestorNarrativeContainingIgnoreCaseOrShareholderValuePropositionContainingIgnoreCase(
            String title, String narrative, String shareholderValue, Pageable pageable);
}
