package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.IndustryEcosystemIntelligenceReport;
import ai.nexusone.enums.IndustryEcosystemIntelligenceStatus;
import ai.nexusone.enums.IndustryPriority;
import ai.nexusone.exception.IndustryEcosystemIntelligenceNotFoundException;
import ai.nexusone.repository.IndustryEcosystemIntelligenceRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndustryEcosystemIntelligenceService {
    private final IndustryEcosystemIntelligenceRepository repository;

    public IndustryEcosystemIntelligenceService(IndustryEcosystemIntelligenceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public IndustryEcosystemIntelligenceResponse generate(IndustryEcosystemIntelligenceRequest request) {
        IndustryEcosystemIntelligenceReport report = new IndustryEcosystemIntelligenceReport();
        report.setTitle(request.title());
        report.setIndustryNarrative(request.industryLandscape());
        report.setFinancialOutlook(request.ecosystemAnalysis());
        report.setGrowthStrategy(request.partnerIntelligence());
        report.setCapitalAllocation(request.supplierIntelligence());
        report.setEcosystemValueProposition(request.strategicRecommendations());
        report.setRevenueConfidenceScore(request.industryGrowthScore());
        report.setProfitabilityConfidenceScore(request.ecosystemStrengthScore());
        report.setGrowthPotentialScore(request.partnerHealthScore());
        report.setCapitalEfficiencyScore(request.supplierResilienceScore());
        report.setGovernanceConfidenceScore(request.regulatoryPreparednessScore());
        report.setMarketRiskExposure(request.innovationVelocityScore());
        report.setIndustryConfidenceScore(score(request));
        report.setPriority(request.priority());
        report.setStatus(IndustryEcosystemIntelligenceStatus.GENERATED);
        report.setCreatedBy(request.createdBy());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public IndustryEcosystemIntelligenceResponse get(Long id) { return map(find(id)); }

    @Transactional(readOnly = true)
    public Page<IndustryEcosystemIntelligenceResponse> history(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<IndustryEcosystemIntelligenceResponse> search(String keyword, IndustryPriority priority,
            IndustryEcosystemIntelligenceStatus status, Pageable pageable) {
        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .findByTitleContainingIgnoreCaseOrIndustryLandscapeContainingIgnoreCaseOrStrategicRecommendationsContainingIgnoreCase(
                            keyword, keyword, keyword, pageable)
                    .map(this::map);
        }
        if (priority != null) return repository.findByPriority(priority, pageable).map(this::map);
        if (status != null) return repository.findByStatus(status, pageable).map(this::map);
        return history(pageable);
    }

    @Transactional
    public IndustryEcosystemIntelligenceResponse review(Long id, IndustryActionRequest request) {
        IndustryEcosystemIntelligenceReport report = find(id);
        require(report, IndustryEcosystemIntelligenceStatus.GENERATED, "Only GENERATED reports can be reviewed");
        report.setStatus(IndustryEcosystemIntelligenceStatus.REVIEWED);
        report.setReviewedBy(request.actionBy());
        report.setReviewedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public IndustryEcosystemIntelligenceResponse approve(Long id, IndustryActionRequest request) {
        IndustryEcosystemIntelligenceReport report = find(id);
        require(report, IndustryEcosystemIntelligenceStatus.REVIEWED, "Only REVIEWED reports can be approved");
        report.setStatus(IndustryEcosystemIntelligenceStatus.APPROVED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public IndustryEcosystemIntelligenceResponse reject(Long id, IndustryActionRequest request) {
        IndustryEcosystemIntelligenceReport report = find(id);
        if (report.getStatus() != IndustryEcosystemIntelligenceStatus.GENERATED
                && report.getStatus() != IndustryEcosystemIntelligenceStatus.REVIEWED) {
            throw new IllegalArgumentException("Only GENERATED or REVIEWED reports can be rejected");
        }
        report.setStatus(IndustryEcosystemIntelligenceStatus.REJECTED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public IndustryEcosystemIntelligenceResponse publish(Long id, IndustryActionRequest request) {
        IndustryEcosystemIntelligenceReport report = find(id);
        require(report, IndustryEcosystemIntelligenceStatus.APPROVED, "Only APPROVED reports can be published");
        report.setStatus(IndustryEcosystemIntelligenceStatus.PUBLISHED);
        report.setPublishedBy(request.actionBy());
        report.setPublishedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public IndustryAnalyticsResponse analytics() {
        List<IndustryEcosystemIntelligenceReport> reports = repository.findAll();
        return new IndustryAnalyticsResponse(
                reports.size(), count(IndustryEcosystemIntelligenceStatus.GENERATED),
                count(IndustryEcosystemIntelligenceStatus.REVIEWED), count(IndustryEcosystemIntelligenceStatus.APPROVED),
                count(IndustryEcosystemIntelligenceStatus.REJECTED), count(IndustryEcosystemIntelligenceStatus.PUBLISHED),
                average(reports, 0), average(reports, 1), average(reports, 2), average(reports, 3),
                average(reports, 4), average(reports, 5),
                round(reports.stream().mapToDouble(IndustryEcosystemIntelligenceReport::getIndustryConfidenceScore)
                        .average().orElse(0)));
    }

    private double score(IndustryEcosystemIntelligenceRequest request) {
        return round(request.industryGrowthScore() * .20
                + request.ecosystemStrengthScore() * .20
                + request.partnerHealthScore() * .20
                + request.supplierResilienceScore() * .15
                + request.regulatoryPreparednessScore() * .15
                + request.innovationVelocityScore() * .10);
    }

    private double average(List<IndustryEcosystemIntelligenceReport> reports, int field) {
        return round(reports.stream().mapToDouble(report -> switch (field) {
            case 0 -> report.getRevenueConfidenceScore();
            case 1 -> report.getProfitabilityConfidenceScore();
            case 2 -> report.getGrowthPotentialScore();
            case 3 -> report.getCapitalEfficiencyScore();
            case 4 -> report.getGovernanceConfidenceScore();
            default -> report.getMarketRiskExposure();
        }).average().orElse(0));
    }

    private long count(IndustryEcosystemIntelligenceStatus status) { return repository.countByStatus(status); }
    private double round(double value) { return Math.round(value * 100.0) / 100.0; }
    private void require(IndustryEcosystemIntelligenceReport report, IndustryEcosystemIntelligenceStatus status, String message) {
        if (report.getStatus() != status) throw new IllegalArgumentException(message);
    }
    private IndustryEcosystemIntelligenceReport find(Long id) {
        return repository.findById(id).orElseThrow(() -> new IndustryEcosystemIntelligenceNotFoundException(id));
    }

    private IndustryEcosystemIntelligenceResponse map(IndustryEcosystemIntelligenceReport report) {
        return new IndustryEcosystemIntelligenceResponse(report.getId(), report.getTitle(), report.getIndustryNarrative(),
                report.getFinancialOutlook(), report.getGrowthStrategy(), report.getCapitalAllocation(),
                report.getEcosystemValueProposition(), report.getRevenueConfidenceScore(),
                report.getProfitabilityConfidenceScore(), report.getGrowthPotentialScore(),
                report.getCapitalEfficiencyScore(), report.getGovernanceConfidenceScore(),
                report.getMarketRiskExposure(), report.getIndustryConfidenceScore(), report.getPriority(),
                report.getStatus(), report.getCreatedBy(), report.getReviewedBy(), report.getReviewedAt(),
                report.getDecidedBy(), report.getDecidedAt(), report.getPublishedBy(), report.getPublishedAt(),
                report.getCreatedAt(), report.getUpdatedAt());
    }
}
