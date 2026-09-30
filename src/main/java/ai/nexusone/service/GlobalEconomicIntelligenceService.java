package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.GlobalEconomicIntelligenceReport;
import ai.nexusone.enums.GlobalEconomicPriority;
import ai.nexusone.enums.GlobalEconomicStatus;
import ai.nexusone.exception.GlobalEconomicIntelligenceNotFoundException;
import ai.nexusone.repository.GlobalEconomicIntelligenceRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GlobalEconomicIntelligenceService {
    private final GlobalEconomicIntelligenceRepository repository;

    public GlobalEconomicIntelligenceService(GlobalEconomicIntelligenceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public GlobalEconomicIntelligenceResponse generate(GlobalEconomicIntelligenceRequest request) {
        GlobalEconomicIntelligenceReport report = new GlobalEconomicIntelligenceReport();
        report.setTitle(request.title());
        report.setGlobalEconomicOutlook(request.globalEconomicOutlook());
        report.setInflationAnalysis(request.inflationAnalysis());
        report.setInterestRateAnalysis(request.interestRateAnalysis());
        report.setCurrencyVolatilityAnalysis(request.currencyVolatilityAnalysis());
        report.setTradeMarketAnalysis(request.tradeMarketAnalysis());
        report.setStrategicRecommendations(request.strategicRecommendations());
        report.setEconomicGrowthScore(request.economicGrowthScore());
        report.setInflationStabilityScore(request.inflationStabilityScore());
        report.setInterestRateStabilityScore(request.interestRateStabilityScore());
        report.setCurrencyStabilityScore(request.currencyStabilityScore());
        report.setTradeResilienceScore(request.tradeResilienceScore());
        report.setLaborMarketStrengthScore(request.laborMarketStrengthScore());
        report.setSupplyChainResilienceScore(request.supplyChainResilienceScore());
        report.setGlobalEconomicIntelligenceScore(score(request));
        report.setPriority(request.priority());
        report.setStatus(GlobalEconomicStatus.GENERATED);
        report.setCreatedBy(request.createdBy());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public GlobalEconomicIntelligenceResponse get(Long id) {
        return map(find(id));
    }

    @Transactional(readOnly = true)
    public Page<GlobalEconomicIntelligenceResponse> history(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<GlobalEconomicIntelligenceResponse> search(
            String keyword,
            GlobalEconomicPriority priority,
            GlobalEconomicStatus status,
            Pageable pageable) {
        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .findByTitleContainingIgnoreCaseOrGlobalEconomicOutlookContainingIgnoreCaseOrStrategicRecommendationsContainingIgnoreCase(
                            keyword, keyword, keyword, pageable)
                    .map(this::map);
        }
        if (priority != null) return repository.findByPriority(priority, pageable).map(this::map);
        if (status != null) return repository.findByStatus(status, pageable).map(this::map);
        return history(pageable);
    }

    @Transactional
    public GlobalEconomicIntelligenceResponse review(Long id, GlobalEconomicActionRequest request) {
        GlobalEconomicIntelligenceReport report = find(id);
        require(report, GlobalEconomicStatus.GENERATED, "Only GENERATED reports can be reviewed");
        report.setStatus(GlobalEconomicStatus.REVIEWED);
        report.setReviewedBy(request.actionBy());
        report.setReviewedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public GlobalEconomicIntelligenceResponse approve(Long id, GlobalEconomicActionRequest request) {
        GlobalEconomicIntelligenceReport report = find(id);
        require(report, GlobalEconomicStatus.REVIEWED, "Only REVIEWED reports can be approved");
        report.setStatus(GlobalEconomicStatus.APPROVED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public GlobalEconomicIntelligenceResponse reject(Long id, GlobalEconomicActionRequest request) {
        GlobalEconomicIntelligenceReport report = find(id);
        if (report.getStatus() != GlobalEconomicStatus.GENERATED
                && report.getStatus() != GlobalEconomicStatus.REVIEWED) {
            throw new IllegalArgumentException("Only GENERATED or REVIEWED reports can be rejected");
        }
        report.setStatus(GlobalEconomicStatus.REJECTED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public GlobalEconomicIntelligenceResponse publish(Long id, GlobalEconomicActionRequest request) {
        GlobalEconomicIntelligenceReport report = find(id);
        require(report, GlobalEconomicStatus.APPROVED, "Only APPROVED reports can be published");
        report.setStatus(GlobalEconomicStatus.PUBLISHED);
        report.setPublishedBy(request.actionBy());
        report.setPublishedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public GlobalEconomicAnalyticsResponse analytics() {
        List<GlobalEconomicIntelligenceReport> reports = repository.findAll();
        return new GlobalEconomicAnalyticsResponse(
                reports.size(),
                count(GlobalEconomicStatus.GENERATED),
                count(GlobalEconomicStatus.REVIEWED),
                count(GlobalEconomicStatus.APPROVED),
                count(GlobalEconomicStatus.REJECTED),
                count(GlobalEconomicStatus.PUBLISHED),
                average(reports, 0), average(reports, 1), average(reports, 2), average(reports, 3),
                average(reports, 4), average(reports, 5), average(reports, 6),
                round(reports.stream()
                        .mapToDouble(GlobalEconomicIntelligenceReport::getGlobalEconomicIntelligenceScore)
                        .average().orElse(0)));
    }

    private double score(GlobalEconomicIntelligenceRequest request) {
        return round(request.economicGrowthScore() * .20
                + request.inflationStabilityScore() * .15
                + request.interestRateStabilityScore() * .15
                + request.currencyStabilityScore() * .15
                + request.tradeResilienceScore() * .10
                + request.laborMarketStrengthScore() * .10
                + request.supplyChainResilienceScore() * .15);
    }

    private double average(List<GlobalEconomicIntelligenceReport> reports, int field) {
        return round(reports.stream().mapToDouble(report -> switch (field) {
            case 0 -> report.getEconomicGrowthScore();
            case 1 -> report.getInflationStabilityScore();
            case 2 -> report.getInterestRateStabilityScore();
            case 3 -> report.getCurrencyStabilityScore();
            case 4 -> report.getTradeResilienceScore();
            case 5 -> report.getLaborMarketStrengthScore();
            default -> report.getSupplyChainResilienceScore();
        }).average().orElse(0));
    }

    private long count(GlobalEconomicStatus status) {
        return repository.countByStatus(status);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private void require(
            GlobalEconomicIntelligenceReport report,
            GlobalEconomicStatus status,
            String message) {
        if (report.getStatus() != status) throw new IllegalArgumentException(message);
    }

    private GlobalEconomicIntelligenceReport find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GlobalEconomicIntelligenceNotFoundException(id));
    }

    private GlobalEconomicIntelligenceResponse map(GlobalEconomicIntelligenceReport report) {
        return new GlobalEconomicIntelligenceResponse(
                report.getId(), report.getTitle(), report.getGlobalEconomicOutlook(),
                report.getInflationAnalysis(), report.getInterestRateAnalysis(),
                report.getCurrencyVolatilityAnalysis(), report.getTradeMarketAnalysis(),
                report.getStrategicRecommendations(), report.getEconomicGrowthScore(),
                report.getInflationStabilityScore(), report.getInterestRateStabilityScore(),
                report.getCurrencyStabilityScore(), report.getTradeResilienceScore(),
                report.getLaborMarketStrengthScore(), report.getSupplyChainResilienceScore(),
                report.getGlobalEconomicIntelligenceScore(), report.getPriority(), report.getStatus(),
                report.getCreatedBy(), report.getReviewedBy(), report.getReviewedAt(),
                report.getDecidedBy(), report.getDecidedAt(), report.getPublishedBy(),
                report.getPublishedAt(), report.getCreatedAt(), report.getUpdatedAt());
    }
}
