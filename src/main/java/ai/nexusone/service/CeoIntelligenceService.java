package ai.nexusone.service;

import ai.nexusone.dto.CeoActionRequest;
import ai.nexusone.dto.CeoAnalyticsResponse;
import ai.nexusone.dto.CeoIntelligenceRequest;
import ai.nexusone.dto.CeoIntelligenceResponse;
import ai.nexusone.entity.CeoIntelligenceReport;
import ai.nexusone.enums.CeoIntelligenceStatus;
import ai.nexusone.enums.CeoPriority;
import ai.nexusone.exception.CeoIntelligenceNotFoundException;
import ai.nexusone.repository.CeoIntelligenceRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CeoIntelligenceService {

    private final CeoIntelligenceRepository repository;

    public CeoIntelligenceService(CeoIntelligenceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CeoIntelligenceResponse generate(CeoIntelligenceRequest request) {
        CeoIntelligenceReport report = new CeoIntelligenceReport();
        report.setTitle(request.title());
        report.setExecutiveSummary(request.executiveSummary());
        report.setStrategicPriority(request.strategicPriority());
        report.setCeoRecommendation(request.ceoRecommendation());
        report.setEnterpriseHealthScore(request.enterpriseHealthScore());
        report.setTransformationScore(request.transformationScore());
        report.setFinancialScore(request.financialScore());
        report.setOperationalScore(request.operationalScore());
        report.setRiskExposure(request.riskExposure());
        report.setInnovationScore(request.innovationScore());
        report.setOverallCeoScore(calculateScore(request));
        report.setPriority(request.priority());
        report.setStatus(CeoIntelligenceStatus.GENERATED);
        report.setCreatedBy(request.createdBy());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public CeoIntelligenceResponse get(Long id) {
        return map(find(id));
    }

    @Transactional(readOnly = true)
    public Page<CeoIntelligenceResponse> history(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<CeoIntelligenceResponse> search(
            String keyword,
            CeoPriority priority,
            CeoIntelligenceStatus status,
            Pageable pageable
    ) {
        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .findByTitleContainingIgnoreCaseOrExecutiveSummaryContainingIgnoreCaseOrCeoRecommendationContainingIgnoreCase(
                            keyword,
                            keyword,
                            keyword,
                            pageable
                    )
                    .map(this::map);
        }
        if (priority != null) {
            return repository.findByPriority(priority, pageable).map(this::map);
        }
        if (status != null) {
            return repository.findByStatus(status, pageable).map(this::map);
        }
        return history(pageable);
    }

    @Transactional
    public CeoIntelligenceResponse review(Long id, CeoActionRequest request) {
        CeoIntelligenceReport report = find(id);
        requireStatus(
                report,
                CeoIntelligenceStatus.GENERATED,
                "Only GENERATED CEO intelligence reports can be reviewed"
        );
        report.setStatus(CeoIntelligenceStatus.REVIEWED);
        report.setReviewedBy(request.actionBy());
        report.setReviewedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public CeoIntelligenceResponse approve(Long id, CeoActionRequest request) {
        CeoIntelligenceReport report = find(id);
        requireStatus(
                report,
                CeoIntelligenceStatus.REVIEWED,
                "Only REVIEWED CEO intelligence reports can be approved"
        );
        report.setStatus(CeoIntelligenceStatus.APPROVED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public CeoIntelligenceResponse reject(Long id, CeoActionRequest request) {
        CeoIntelligenceReport report = find(id);
        if (report.getStatus() != CeoIntelligenceStatus.GENERATED
                && report.getStatus() != CeoIntelligenceStatus.REVIEWED) {
            throw new IllegalArgumentException(
                    "Only GENERATED or REVIEWED CEO intelligence reports can be rejected"
            );
        }
        report.setStatus(CeoIntelligenceStatus.REJECTED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public CeoIntelligenceResponse execute(Long id, CeoActionRequest request) {
        CeoIntelligenceReport report = find(id);
        requireStatus(
                report,
                CeoIntelligenceStatus.APPROVED,
                "Only APPROVED CEO intelligence reports can be executed"
        );
        report.setStatus(CeoIntelligenceStatus.EXECUTED);
        report.setExecutedBy(request.actionBy());
        report.setExecutedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public CeoAnalyticsResponse analytics() {
        List<CeoIntelligenceReport> reports = repository.findAll();
        return new CeoAnalyticsResponse(
                reports.size(),
                count(CeoIntelligenceStatus.GENERATED),
                count(CeoIntelligenceStatus.REVIEWED),
                count(CeoIntelligenceStatus.APPROVED),
                count(CeoIntelligenceStatus.REJECTED),
                count(CeoIntelligenceStatus.EXECUTED),
                average(reports, Metric.ENTERPRISE_HEALTH),
                average(reports, Metric.TRANSFORMATION),
                average(reports, Metric.FINANCIAL),
                average(reports, Metric.OPERATIONAL),
                average(reports, Metric.RISK),
                average(reports, Metric.INNOVATION),
                round(reports.stream()
                        .mapToDouble(CeoIntelligenceReport::getOverallCeoScore)
                        .average()
                        .orElse(0))
        );
    }

    private double calculateScore(CeoIntelligenceRequest request) {
        return round(
                request.enterpriseHealthScore() * 0.25
                        + request.transformationScore() * 0.20
                        + request.financialScore() * 0.20
                        + request.operationalScore() * 0.15
                        + request.innovationScore() * 0.10
                        + (100 - request.riskExposure()) * 0.10
        );
    }

    private double average(List<CeoIntelligenceReport> reports, Metric metric) {
        return round(reports.stream().mapToDouble(report -> switch (metric) {
            case ENTERPRISE_HEALTH -> report.getEnterpriseHealthScore();
            case TRANSFORMATION -> report.getTransformationScore();
            case FINANCIAL -> report.getFinancialScore();
            case OPERATIONAL -> report.getOperationalScore();
            case RISK -> report.getRiskExposure();
            case INNOVATION -> report.getInnovationScore();
        }).average().orElse(0));
    }

    private long count(CeoIntelligenceStatus status) {
        return repository.countByStatus(status);
    }

    private void requireStatus(
            CeoIntelligenceReport report,
            CeoIntelligenceStatus expected,
            String message
    ) {
        if (report.getStatus() != expected) {
            throw new IllegalArgumentException(message);
        }
    }

    private CeoIntelligenceReport find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CeoIntelligenceNotFoundException(id));
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private CeoIntelligenceResponse map(CeoIntelligenceReport report) {
        return new CeoIntelligenceResponse(
                report.getId(),
                report.getTitle(),
                report.getExecutiveSummary(),
                report.getStrategicPriority(),
                report.getCeoRecommendation(),
                report.getEnterpriseHealthScore(),
                report.getTransformationScore(),
                report.getFinancialScore(),
                report.getOperationalScore(),
                report.getRiskExposure(),
                report.getInnovationScore(),
                report.getOverallCeoScore(),
                report.getPriority(),
                report.getStatus(),
                report.getCreatedBy(),
                report.getReviewedBy(),
                report.getReviewedAt(),
                report.getDecidedBy(),
                report.getDecidedAt(),
                report.getExecutedBy(),
                report.getExecutedAt(),
                report.getCreatedAt(),
                report.getUpdatedAt()
        );
    }

    private enum Metric {
        ENTERPRISE_HEALTH,
        TRANSFORMATION,
        FINANCIAL,
        OPERATIONAL,
        RISK,
        INNOVATION
    }
}
