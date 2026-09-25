package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.InvestorIntelligenceReport;
import ai.nexusone.enums.InvestorIntelligenceStatus;
import ai.nexusone.enums.InvestorPriority;
import ai.nexusone.exception.InvestorIntelligenceNotFoundException;
import ai.nexusone.repository.InvestorIntelligenceRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvestorIntelligenceService {
    private final InvestorIntelligenceRepository repository;

    public InvestorIntelligenceService(InvestorIntelligenceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public InvestorIntelligenceResponse generate(InvestorIntelligenceRequest request) {
        InvestorIntelligenceReport report = new InvestorIntelligenceReport();
        report.setTitle(request.title());
        report.setInvestorNarrative(request.investorNarrative());
        report.setFinancialOutlook(request.financialOutlook());
        report.setGrowthStrategy(request.growthStrategy());
        report.setCapitalAllocation(request.capitalAllocation());
        report.setShareholderValueProposition(request.shareholderValueProposition());
        report.setRevenueConfidenceScore(request.revenueConfidenceScore());
        report.setProfitabilityConfidenceScore(request.profitabilityConfidenceScore());
        report.setGrowthPotentialScore(request.growthPotentialScore());
        report.setCapitalEfficiencyScore(request.capitalEfficiencyScore());
        report.setGovernanceConfidenceScore(request.governanceConfidenceScore());
        report.setMarketRiskExposure(request.marketRiskExposure());
        report.setInvestorConfidenceScore(score(request));
        report.setPriority(request.priority());
        report.setStatus(InvestorIntelligenceStatus.GENERATED);
        report.setCreatedBy(request.createdBy());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public InvestorIntelligenceResponse get(Long id) { return map(find(id)); }

    @Transactional(readOnly = true)
    public Page<InvestorIntelligenceResponse> history(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<InvestorIntelligenceResponse> search(String keyword, InvestorPriority priority,
            InvestorIntelligenceStatus status, Pageable pageable) {
        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .findByTitleContainingIgnoreCaseOrInvestorNarrativeContainingIgnoreCaseOrShareholderValuePropositionContainingIgnoreCase(
                            keyword, keyword, keyword, pageable)
                    .map(this::map);
        }
        if (priority != null) return repository.findByPriority(priority, pageable).map(this::map);
        if (status != null) return repository.findByStatus(status, pageable).map(this::map);
        return history(pageable);
    }

    @Transactional
    public InvestorIntelligenceResponse review(Long id, InvestorActionRequest request) {
        InvestorIntelligenceReport report = find(id);
        require(report, InvestorIntelligenceStatus.GENERATED, "Only GENERATED reports can be reviewed");
        report.setStatus(InvestorIntelligenceStatus.REVIEWED);
        report.setReviewedBy(request.actionBy());
        report.setReviewedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public InvestorIntelligenceResponse approve(Long id, InvestorActionRequest request) {
        InvestorIntelligenceReport report = find(id);
        require(report, InvestorIntelligenceStatus.REVIEWED, "Only REVIEWED reports can be approved");
        report.setStatus(InvestorIntelligenceStatus.APPROVED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public InvestorIntelligenceResponse reject(Long id, InvestorActionRequest request) {
        InvestorIntelligenceReport report = find(id);
        if (report.getStatus() != InvestorIntelligenceStatus.GENERATED
                && report.getStatus() != InvestorIntelligenceStatus.REVIEWED) {
            throw new IllegalArgumentException("Only GENERATED or REVIEWED reports can be rejected");
        }
        report.setStatus(InvestorIntelligenceStatus.REJECTED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public InvestorIntelligenceResponse publish(Long id, InvestorActionRequest request) {
        InvestorIntelligenceReport report = find(id);
        require(report, InvestorIntelligenceStatus.APPROVED, "Only APPROVED reports can be published");
        report.setStatus(InvestorIntelligenceStatus.PUBLISHED);
        report.setPublishedBy(request.actionBy());
        report.setPublishedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public InvestorAnalyticsResponse analytics() {
        List<InvestorIntelligenceReport> reports = repository.findAll();
        return new InvestorAnalyticsResponse(
                reports.size(), count(InvestorIntelligenceStatus.GENERATED),
                count(InvestorIntelligenceStatus.REVIEWED), count(InvestorIntelligenceStatus.APPROVED),
                count(InvestorIntelligenceStatus.REJECTED), count(InvestorIntelligenceStatus.PUBLISHED),
                average(reports, 0), average(reports, 1), average(reports, 2), average(reports, 3),
                average(reports, 4), average(reports, 5),
                round(reports.stream().mapToDouble(InvestorIntelligenceReport::getInvestorConfidenceScore)
                        .average().orElse(0)));
    }

    private double score(InvestorIntelligenceRequest request) {
        return round(request.revenueConfidenceScore() * .20
                + request.profitabilityConfidenceScore() * .20
                + request.growthPotentialScore() * .20
                + request.capitalEfficiencyScore() * .15
                + request.governanceConfidenceScore() * .15
                + (100 - request.marketRiskExposure()) * .10);
    }

    private double average(List<InvestorIntelligenceReport> reports, int field) {
        return round(reports.stream().mapToDouble(report -> switch (field) {
            case 0 -> report.getRevenueConfidenceScore();
            case 1 -> report.getProfitabilityConfidenceScore();
            case 2 -> report.getGrowthPotentialScore();
            case 3 -> report.getCapitalEfficiencyScore();
            case 4 -> report.getGovernanceConfidenceScore();
            default -> report.getMarketRiskExposure();
        }).average().orElse(0));
    }

    private long count(InvestorIntelligenceStatus status) { return repository.countByStatus(status); }
    private double round(double value) { return Math.round(value * 100.0) / 100.0; }
    private void require(InvestorIntelligenceReport report, InvestorIntelligenceStatus status, String message) {
        if (report.getStatus() != status) throw new IllegalArgumentException(message);
    }
    private InvestorIntelligenceReport find(Long id) {
        return repository.findById(id).orElseThrow(() -> new InvestorIntelligenceNotFoundException(id));
    }

    private InvestorIntelligenceResponse map(InvestorIntelligenceReport report) {
        return new InvestorIntelligenceResponse(report.getId(), report.getTitle(), report.getInvestorNarrative(),
                report.getFinancialOutlook(), report.getGrowthStrategy(), report.getCapitalAllocation(),
                report.getShareholderValueProposition(), report.getRevenueConfidenceScore(),
                report.getProfitabilityConfidenceScore(), report.getGrowthPotentialScore(),
                report.getCapitalEfficiencyScore(), report.getGovernanceConfidenceScore(),
                report.getMarketRiskExposure(), report.getInvestorConfidenceScore(), report.getPriority(),
                report.getStatus(), report.getCreatedBy(), report.getReviewedBy(), report.getReviewedAt(),
                report.getDecidedBy(), report.getDecidedAt(), report.getPublishedBy(), report.getPublishedAt(),
                report.getCreatedAt(), report.getUpdatedAt());
    }
}
