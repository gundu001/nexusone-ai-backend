package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.EnterpriseRiskIntelligenceReport;
import ai.nexusone.enums.EnterpriseRiskPriority;
import ai.nexusone.enums.EnterpriseRiskStatus;
import ai.nexusone.exception.EnterpriseRiskIntelligenceNotFoundException;
import ai.nexusone.repository.EnterpriseRiskIntelligenceRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnterpriseRiskIntelligenceService {
    private final EnterpriseRiskIntelligenceRepository repository;

    public EnterpriseRiskIntelligenceService(EnterpriseRiskIntelligenceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public EnterpriseRiskIntelligenceResponse generate(EnterpriseRiskIntelligenceRequest request) {
        EnterpriseRiskIntelligenceReport report = new EnterpriseRiskIntelligenceReport();
        report.setTitle(request.title());
        report.setEnterpriseRiskOutlook(request.enterpriseRiskOutlook());
        report.setOperationalRiskAnalysis(request.operationalRiskAnalysis());
        report.setFinancialRiskAnalysis(request.financialRiskAnalysis());
        report.setCyberSecurityRiskAnalysis(request.cyberSecurityRiskAnalysis());
        report.setRegulatoryComplianceRiskAnalysis(request.regulatoryComplianceRiskAnalysis());
        report.setStrategicRiskAnalysis(request.strategicRiskAnalysis());
        report.setThirdPartyRiskAnalysis(request.thirdPartyRiskAnalysis());
        report.setBusinessContinuityAnalysis(request.businessContinuityAnalysis());
        report.setRiskMitigationRecommendations(request.riskMitigationRecommendations());
        report.setOperationalRiskScore(request.operationalRiskScore());
        report.setFinancialRiskScore(request.financialRiskScore());
        report.setCyberSecurityRiskScore(request.cyberSecurityRiskScore());
        report.setRegulatoryComplianceRiskScore(request.regulatoryComplianceRiskScore());
        report.setStrategicRiskScore(request.strategicRiskScore());
        report.setThirdPartyRiskScore(request.thirdPartyRiskScore());
        report.setBusinessContinuityRiskScore(request.businessContinuityRiskScore());
        report.setEnterpriseRiskIntelligenceScore(score(request));
        report.setPriority(request.priority());
        report.setStatus(EnterpriseRiskStatus.GENERATED);
        report.setCreatedBy(request.createdBy());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public EnterpriseRiskIntelligenceResponse get(Long id) {
        return map(find(id));
    }

    @Transactional(readOnly = true)
    public Page<EnterpriseRiskIntelligenceResponse> history(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<EnterpriseRiskIntelligenceResponse> search(
            String keyword,
            EnterpriseRiskPriority priority,
            EnterpriseRiskStatus status,
            Pageable pageable) {
        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .findByTitleContainingIgnoreCaseOrEnterpriseRiskOutlookContainingIgnoreCaseOrRiskMitigationRecommendationsContainingIgnoreCase(
                            keyword, keyword, keyword, pageable)
                    .map(this::map);
        }
        if (priority != null) return repository.findByPriority(priority, pageable).map(this::map);
        if (status != null) return repository.findByStatus(status, pageable).map(this::map);
        return history(pageable);
    }

    @Transactional
    public EnterpriseRiskIntelligenceResponse review(Long id, EnterpriseRiskActionRequest request) {
        EnterpriseRiskIntelligenceReport report = find(id);
        require(report, EnterpriseRiskStatus.GENERATED, "Only GENERATED reports can be reviewed");
        report.setStatus(EnterpriseRiskStatus.REVIEWED);
        report.setReviewedBy(request.actionBy());
        report.setReviewedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public EnterpriseRiskIntelligenceResponse approve(Long id, EnterpriseRiskActionRequest request) {
        EnterpriseRiskIntelligenceReport report = find(id);
        require(report, EnterpriseRiskStatus.REVIEWED, "Only REVIEWED reports can be approved");
        report.setStatus(EnterpriseRiskStatus.APPROVED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public EnterpriseRiskIntelligenceResponse reject(Long id, EnterpriseRiskActionRequest request) {
        EnterpriseRiskIntelligenceReport report = find(id);
        if (report.getStatus() != EnterpriseRiskStatus.GENERATED
                && report.getStatus() != EnterpriseRiskStatus.REVIEWED) {
            throw new IllegalArgumentException("Only GENERATED or REVIEWED reports can be rejected");
        }
        report.setStatus(EnterpriseRiskStatus.REJECTED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public EnterpriseRiskIntelligenceResponse publish(Long id, EnterpriseRiskActionRequest request) {
        EnterpriseRiskIntelligenceReport report = find(id);
        require(report, EnterpriseRiskStatus.APPROVED, "Only APPROVED reports can be published");
        report.setStatus(EnterpriseRiskStatus.PUBLISHED);
        report.setPublishedBy(request.actionBy());
        report.setPublishedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public EnterpriseRiskAnalyticsResponse analytics() {
        List<EnterpriseRiskIntelligenceReport> reports = repository.findAll();
        return new EnterpriseRiskAnalyticsResponse(
                reports.size(),
                count(EnterpriseRiskStatus.GENERATED),
                count(EnterpriseRiskStatus.REVIEWED),
                count(EnterpriseRiskStatus.APPROVED),
                count(EnterpriseRiskStatus.REJECTED),
                count(EnterpriseRiskStatus.PUBLISHED),
                average(reports, 0), average(reports, 1), average(reports, 2),
                average(reports, 3), average(reports, 4), average(reports, 5),
                average(reports, 6),
                round(reports.stream()
                        .mapToDouble(EnterpriseRiskIntelligenceReport::getEnterpriseRiskIntelligenceScore)
                        .average().orElse(0)));
    }

    private double score(EnterpriseRiskIntelligenceRequest request) {
        return round(request.operationalRiskScore() * .15
                + request.financialRiskScore() * .15
                + request.cyberSecurityRiskScore() * .20
                + request.regulatoryComplianceRiskScore() * .15
                + request.strategicRiskScore() * .15
                + request.thirdPartyRiskScore() * .10
                + request.businessContinuityRiskScore() * .10);
    }

    private double average(List<EnterpriseRiskIntelligenceReport> reports, int field) {
        return round(reports.stream().mapToDouble(report -> switch (field) {
            case 0 -> report.getOperationalRiskScore();
            case 1 -> report.getFinancialRiskScore();
            case 2 -> report.getCyberSecurityRiskScore();
            case 3 -> report.getRegulatoryComplianceRiskScore();
            case 4 -> report.getStrategicRiskScore();
            case 5 -> report.getThirdPartyRiskScore();
            default -> report.getBusinessContinuityRiskScore();
        }).average().orElse(0));
    }

    private long count(EnterpriseRiskStatus status) {
        return repository.countByStatus(status);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private void require(
            EnterpriseRiskIntelligenceReport report,
            EnterpriseRiskStatus status,
            String message) {
        if (report.getStatus() != status) throw new IllegalArgumentException(message);
    }

    private EnterpriseRiskIntelligenceReport find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EnterpriseRiskIntelligenceNotFoundException(id));
    }

    private EnterpriseRiskIntelligenceResponse map(EnterpriseRiskIntelligenceReport report) {
        return new EnterpriseRiskIntelligenceResponse(
                report.getId(), report.getTitle(), report.getEnterpriseRiskOutlook(),
                report.getOperationalRiskAnalysis(), report.getFinancialRiskAnalysis(),
                report.getCyberSecurityRiskAnalysis(), report.getRegulatoryComplianceRiskAnalysis(),
                report.getStrategicRiskAnalysis(), report.getThirdPartyRiskAnalysis(),
                report.getBusinessContinuityAnalysis(), report.getRiskMitigationRecommendations(),
                report.getOperationalRiskScore(), report.getFinancialRiskScore(),
                report.getCyberSecurityRiskScore(), report.getRegulatoryComplianceRiskScore(),
                report.getStrategicRiskScore(), report.getThirdPartyRiskScore(),
                report.getBusinessContinuityRiskScore(), report.getEnterpriseRiskIntelligenceScore(),
                report.getPriority(), report.getStatus(), report.getCreatedBy(),
                report.getReviewedBy(), report.getReviewedAt(), report.getDecidedBy(),
                report.getDecidedAt(), report.getPublishedBy(), report.getPublishedAt(),
                report.getCreatedAt(), report.getUpdatedAt());
    }
}
