package ai.nexusone.service;

import ai.nexusone.dto.ChiefAiOfficerActionRequest;
import ai.nexusone.dto.ChiefAiOfficerAnalyticsResponse;
import ai.nexusone.dto.ChiefAiOfficerIntelligenceRequest;
import ai.nexusone.dto.ChiefAiOfficerIntelligenceResponse;
import ai.nexusone.entity.ChiefAiOfficerIntelligenceReport;
import ai.nexusone.enums.ChiefAiOfficerPriority;
import ai.nexusone.enums.ChiefAiOfficerStatus;
import ai.nexusone.exception.ChiefAiOfficerIntelligenceNotFoundException;
import ai.nexusone.repository.ChiefAiOfficerIntelligenceRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.ToDoubleFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChiefAiOfficerIntelligenceService {
    private final ChiefAiOfficerIntelligenceRepository repository;

    public ChiefAiOfficerIntelligenceService(ChiefAiOfficerIntelligenceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ChiefAiOfficerIntelligenceResponse generate(ChiefAiOfficerIntelligenceRequest request) {
        ChiefAiOfficerIntelligenceReport report = new ChiefAiOfficerIntelligenceReport();
        report.setTitle(request.title());
        report.setAiStrategy(request.aiStrategy());
        report.setGovernanceOutlook(request.governanceOutlook());
        report.setResponsibleAiPlan(request.responsibleAiPlan());
        report.setPlatformModernizationPlan(request.platformModernizationPlan());
        report.setAdoptionRoadmap(request.adoptionRoadmap());
        report.setWorkforceTransformationPlan(request.workforceTransformationPlan());
        report.setDataReadinessAssessment(request.dataReadinessAssessment());
        report.setAiRiskAssessment(request.aiRiskAssessment());
        report.setValueRealizationPlan(request.valueRealizationPlan());
        report.setStrategicRecommendations(request.strategicRecommendations());
        report.setAiStrategyScore(request.aiStrategyScore());
        report.setGovernanceScore(request.governanceScore());
        report.setResponsibleAiScore(request.responsibleAiScore());
        report.setPlatformMaturityScore(request.platformMaturityScore());
        report.setAdoptionScore(request.adoptionScore());
        report.setWorkforceReadinessScore(request.workforceReadinessScore());
        report.setDataReadinessScore(request.dataReadinessScore());
        report.setAiRiskManagementScore(request.aiRiskManagementScore());
        report.setValueRealizationScore(request.valueRealizationScore());
        report.setChiefAiOfficerIntelligenceScore(score(request));
        report.setPriority(request.priority());
        report.setStatus(ChiefAiOfficerStatus.GENERATED);
        report.setCreatedBy(request.createdBy());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public ChiefAiOfficerIntelligenceResponse get(Long id) { return map(find(id)); }

    @Transactional(readOnly = true)
    public Page<ChiefAiOfficerIntelligenceResponse> history(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<ChiefAiOfficerIntelligenceResponse> search(
            String keyword, ChiefAiOfficerPriority priority, ChiefAiOfficerStatus status, Pageable pageable) {
        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .findByTitleContainingIgnoreCaseOrAiStrategyContainingIgnoreCaseOrStrategicRecommendationsContainingIgnoreCase(
                            keyword, keyword, keyword, pageable)
                    .map(this::map);
        }
        if (priority != null) return repository.findByPriority(priority, pageable).map(this::map);
        if (status != null) return repository.findByStatus(status, pageable).map(this::map);
        return history(pageable);
    }

    @Transactional
    public ChiefAiOfficerIntelligenceResponse review(Long id, ChiefAiOfficerActionRequest request) {
        ChiefAiOfficerIntelligenceReport report = find(id);
        require(report, ChiefAiOfficerStatus.GENERATED, "Only GENERATED reports can be reviewed");
        report.setStatus(ChiefAiOfficerStatus.REVIEWED);
        report.setReviewedBy(request.actionBy());
        report.setReviewedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public ChiefAiOfficerIntelligenceResponse approve(Long id, ChiefAiOfficerActionRequest request) {
        ChiefAiOfficerIntelligenceReport report = find(id);
        require(report, ChiefAiOfficerStatus.REVIEWED, "Only REVIEWED reports can be approved");
        report.setStatus(ChiefAiOfficerStatus.APPROVED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public ChiefAiOfficerIntelligenceResponse reject(Long id, ChiefAiOfficerActionRequest request) {
        ChiefAiOfficerIntelligenceReport report = find(id);
        if (report.getStatus() != ChiefAiOfficerStatus.GENERATED
                && report.getStatus() != ChiefAiOfficerStatus.REVIEWED) {
            throw new IllegalArgumentException("Only GENERATED or REVIEWED reports can be rejected");
        }
        report.setStatus(ChiefAiOfficerStatus.REJECTED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public ChiefAiOfficerIntelligenceResponse publish(Long id, ChiefAiOfficerActionRequest request) {
        ChiefAiOfficerIntelligenceReport report = find(id);
        require(report, ChiefAiOfficerStatus.APPROVED, "Only APPROVED reports can be published");
        report.setStatus(ChiefAiOfficerStatus.PUBLISHED);
        report.setPublishedBy(request.actionBy());
        report.setPublishedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public ChiefAiOfficerAnalyticsResponse analytics() {
        List<ChiefAiOfficerIntelligenceReport> reports = repository.findAll();
        return new ChiefAiOfficerAnalyticsResponse(
                reports.size(),
                count(ChiefAiOfficerStatus.GENERATED),
                count(ChiefAiOfficerStatus.REVIEWED),
                count(ChiefAiOfficerStatus.APPROVED),
                count(ChiefAiOfficerStatus.REJECTED),
                count(ChiefAiOfficerStatus.PUBLISHED),
                average(reports, ChiefAiOfficerIntelligenceReport::getAiStrategyScore),
                average(reports, ChiefAiOfficerIntelligenceReport::getGovernanceScore),
                average(reports, ChiefAiOfficerIntelligenceReport::getResponsibleAiScore),
                average(reports, ChiefAiOfficerIntelligenceReport::getPlatformMaturityScore),
                average(reports, ChiefAiOfficerIntelligenceReport::getAdoptionScore),
                average(reports, ChiefAiOfficerIntelligenceReport::getWorkforceReadinessScore),
                average(reports, ChiefAiOfficerIntelligenceReport::getDataReadinessScore),
                average(reports, ChiefAiOfficerIntelligenceReport::getAiRiskManagementScore),
                average(reports, ChiefAiOfficerIntelligenceReport::getValueRealizationScore),
                average(reports, ChiefAiOfficerIntelligenceReport::getChiefAiOfficerIntelligenceScore));
    }

    private double score(ChiefAiOfficerIntelligenceRequest request) {
        return round(
                request.aiStrategyScore() * 0.15
                        + request.governanceScore() * 0.14
                        + request.responsibleAiScore() * 0.12
                        + request.platformMaturityScore() * 0.11
                        + request.adoptionScore() * 0.10
                        + request.workforceReadinessScore() * 0.10
                        + request.dataReadinessScore() * 0.10
                        + request.aiRiskManagementScore() * 0.10
                        + request.valueRealizationScore() * 0.08);
    }

    private double average(
            List<ChiefAiOfficerIntelligenceReport> reports,
            ToDoubleFunction<ChiefAiOfficerIntelligenceReport> field) {
        return round(reports.stream().mapToDouble(field).average().orElse(0));
    }

    private long count(ChiefAiOfficerStatus status) { return repository.countByStatus(status); }
    private double round(double value) { return Math.round(value * 100.0) / 100.0; }

    private void require(
            ChiefAiOfficerIntelligenceReport report, ChiefAiOfficerStatus expected, String message) {
        if (report.getStatus() != expected) throw new IllegalArgumentException(message);
    }

    private ChiefAiOfficerIntelligenceReport find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChiefAiOfficerIntelligenceNotFoundException(id));
    }

    private ChiefAiOfficerIntelligenceResponse map(ChiefAiOfficerIntelligenceReport report) {
        return new ChiefAiOfficerIntelligenceResponse(
                report.getId(), report.getTitle(), report.getAiStrategy(), report.getGovernanceOutlook(),
                report.getResponsibleAiPlan(), report.getPlatformModernizationPlan(), report.getAdoptionRoadmap(),
                report.getWorkforceTransformationPlan(), report.getDataReadinessAssessment(),
                report.getAiRiskAssessment(), report.getValueRealizationPlan(), report.getStrategicRecommendations(),
                report.getAiStrategyScore(), report.getGovernanceScore(), report.getResponsibleAiScore(),
                report.getPlatformMaturityScore(), report.getAdoptionScore(), report.getWorkforceReadinessScore(),
                report.getDataReadinessScore(), report.getAiRiskManagementScore(), report.getValueRealizationScore(),
                report.getChiefAiOfficerIntelligenceScore(), report.getPriority(), report.getStatus(),
                report.getCreatedBy(), report.getReviewedBy(), report.getReviewedAt(), report.getDecidedBy(),
                report.getDecidedAt(), report.getPublishedBy(), report.getPublishedAt(),
                report.getCreatedAt(), report.getUpdatedAt());
    }
}
