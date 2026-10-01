package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.DigitalBoardMemberIntelligenceReport;
import ai.nexusone.enums.DigitalBoardMemberPriority;
import ai.nexusone.enums.DigitalBoardMemberStatus;
import ai.nexusone.exception.DigitalBoardMemberIntelligenceNotFoundException;
import ai.nexusone.repository.DigitalBoardMemberIntelligenceRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.ToDoubleFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DigitalBoardMemberIntelligenceService {
    private final DigitalBoardMemberIntelligenceRepository repository;

    public DigitalBoardMemberIntelligenceService(DigitalBoardMemberIntelligenceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DigitalBoardMemberIntelligenceResponse generate(DigitalBoardMemberIntelligenceRequest request) {
        DigitalBoardMemberIntelligenceReport report = new DigitalBoardMemberIntelligenceReport();
        report.setTitle(request.title());
        report.setBoardAgenda(request.boardAgenda());
        report.setStrategicOversight(request.strategicOversight());
        report.setFinancialStewardship(request.financialStewardship());
        report.setRiskGovernance(request.riskGovernance());
        report.setTechnologyOversight(request.technologyOversight());
        report.setAiGovernance(request.aiGovernance());
        report.setCybersecurityOversight(request.cybersecurityOversight());
        report.setStakeholderAlignment(request.stakeholderAlignment());
        report.setExecutiveAccountability(request.executiveAccountability());
        report.setBoardRecommendations(request.boardRecommendations());
        report.setBoardAgendaScore(request.boardAgendaScore());
        report.setStrategicOversightScore(request.strategicOversightScore());
        report.setFinancialStewardshipScore(request.financialStewardshipScore());
        report.setRiskGovernanceScore(request.riskGovernanceScore());
        report.setTechnologyOversightScore(request.technologyOversightScore());
        report.setAiGovernanceScore(request.aiGovernanceScore());
        report.setCybersecurityOversightScore(request.cybersecurityOversightScore());
        report.setStakeholderAlignmentScore(request.stakeholderAlignmentScore());
        report.setExecutiveAccountabilityScore(request.executiveAccountabilityScore());
        report.setDigitalBoardMemberIntelligenceScore(score(request));
        report.setPriority(request.priority());
        report.setStatus(DigitalBoardMemberStatus.GENERATED);
        report.setCreatedBy(request.createdBy());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public DigitalBoardMemberIntelligenceResponse get(Long id) { return map(find(id)); }

    @Transactional(readOnly = true)
    public Page<DigitalBoardMemberIntelligenceResponse> history(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<DigitalBoardMemberIntelligenceResponse> search(String keyword,
            DigitalBoardMemberPriority priority, DigitalBoardMemberStatus status, Pageable pageable) {
        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .findByTitleContainingIgnoreCaseOrBoardAgendaContainingIgnoreCaseOrBoardRecommendationsContainingIgnoreCase(
                            keyword, keyword, keyword, pageable)
                    .map(this::map);
        }
        if (priority != null) return repository.findByPriority(priority, pageable).map(this::map);
        if (status != null) return repository.findByStatus(status, pageable).map(this::map);
        return history(pageable);
    }

    @Transactional
    public DigitalBoardMemberIntelligenceResponse review(Long id, DigitalBoardMemberActionRequest request) {
        DigitalBoardMemberIntelligenceReport report = find(id);
        require(report, DigitalBoardMemberStatus.GENERATED, "Only GENERATED reports can be reviewed");
        report.setStatus(DigitalBoardMemberStatus.REVIEWED);
        report.setReviewedBy(request.actionBy());
        report.setReviewedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public DigitalBoardMemberIntelligenceResponse approve(Long id, DigitalBoardMemberActionRequest request) {
        DigitalBoardMemberIntelligenceReport report = find(id);
        require(report, DigitalBoardMemberStatus.REVIEWED, "Only REVIEWED reports can be approved");
        report.setStatus(DigitalBoardMemberStatus.APPROVED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public DigitalBoardMemberIntelligenceResponse reject(Long id, DigitalBoardMemberActionRequest request) {
        DigitalBoardMemberIntelligenceReport report = find(id);
        if (report.getStatus() != DigitalBoardMemberStatus.GENERATED
                && report.getStatus() != DigitalBoardMemberStatus.REVIEWED) {
            throw new IllegalArgumentException("Only GENERATED or REVIEWED reports can be rejected");
        }
        report.setStatus(DigitalBoardMemberStatus.REJECTED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public DigitalBoardMemberIntelligenceResponse publish(Long id, DigitalBoardMemberActionRequest request) {
        DigitalBoardMemberIntelligenceReport report = find(id);
        require(report, DigitalBoardMemberStatus.APPROVED, "Only APPROVED reports can be published");
        report.setStatus(DigitalBoardMemberStatus.PUBLISHED);
        report.setPublishedBy(request.actionBy());
        report.setPublishedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public DigitalBoardMemberAnalyticsResponse analytics() {
        List<DigitalBoardMemberIntelligenceReport> reports = repository.findAll();
        return new DigitalBoardMemberAnalyticsResponse(
                reports.size(), count(DigitalBoardMemberStatus.GENERATED),
                count(DigitalBoardMemberStatus.REVIEWED), count(DigitalBoardMemberStatus.APPROVED),
                count(DigitalBoardMemberStatus.REJECTED), count(DigitalBoardMemberStatus.PUBLISHED),
                average(reports, DigitalBoardMemberIntelligenceReport::getBoardAgendaScore),
                average(reports, DigitalBoardMemberIntelligenceReport::getStrategicOversightScore),
                average(reports, DigitalBoardMemberIntelligenceReport::getFinancialStewardshipScore),
                average(reports, DigitalBoardMemberIntelligenceReport::getRiskGovernanceScore),
                average(reports, DigitalBoardMemberIntelligenceReport::getTechnologyOversightScore),
                average(reports, DigitalBoardMemberIntelligenceReport::getAiGovernanceScore),
                average(reports, DigitalBoardMemberIntelligenceReport::getCybersecurityOversightScore),
                average(reports, DigitalBoardMemberIntelligenceReport::getStakeholderAlignmentScore),
                average(reports, DigitalBoardMemberIntelligenceReport::getExecutiveAccountabilityScore),
                average(reports, DigitalBoardMemberIntelligenceReport::getDigitalBoardMemberIntelligenceScore));
    }

    private double score(DigitalBoardMemberIntelligenceRequest request) {
        return round(request.boardAgendaScore() * 0.10
                + request.strategicOversightScore() * 0.15
                + request.financialStewardshipScore() * 0.12
                + request.riskGovernanceScore() * 0.13
                + request.technologyOversightScore() * 0.10
                + request.aiGovernanceScore() * 0.12
                + request.cybersecurityOversightScore() * 0.10
                + request.stakeholderAlignmentScore() * 0.08
                + request.executiveAccountabilityScore() * 0.10);
    }

    private double average(List<DigitalBoardMemberIntelligenceReport> reports,
            ToDoubleFunction<DigitalBoardMemberIntelligenceReport> field) {
        return round(reports.stream().mapToDouble(field).average().orElse(0));
    }

    private long count(DigitalBoardMemberStatus status) { return repository.countByStatus(status); }
    private double round(double value) { return Math.round(value * 100.0) / 100.0; }
    private void require(DigitalBoardMemberIntelligenceReport report,
            DigitalBoardMemberStatus expected, String message) {
        if (report.getStatus() != expected) throw new IllegalArgumentException(message);
    }
    private DigitalBoardMemberIntelligenceReport find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DigitalBoardMemberIntelligenceNotFoundException(id));
    }

    private DigitalBoardMemberIntelligenceResponse map(DigitalBoardMemberIntelligenceReport report) {
        return new DigitalBoardMemberIntelligenceResponse(
                report.getId(), report.getTitle(), report.getBoardAgenda(), report.getStrategicOversight(),
                report.getFinancialStewardship(), report.getRiskGovernance(), report.getTechnologyOversight(),
                report.getAiGovernance(), report.getCybersecurityOversight(), report.getStakeholderAlignment(),
                report.getExecutiveAccountability(), report.getBoardRecommendations(), report.getBoardAgendaScore(),
                report.getStrategicOversightScore(), report.getFinancialStewardshipScore(), report.getRiskGovernanceScore(),
                report.getTechnologyOversightScore(), report.getAiGovernanceScore(), report.getCybersecurityOversightScore(),
                report.getStakeholderAlignmentScore(), report.getExecutiveAccountabilityScore(),
                report.getDigitalBoardMemberIntelligenceScore(), report.getPriority(), report.getStatus(),
                report.getCreatedBy(), report.getReviewedBy(), report.getReviewedAt(), report.getDecidedBy(),
                report.getDecidedAt(), report.getPublishedBy(), report.getPublishedAt(),
                report.getCreatedAt(), report.getUpdatedAt());
    }
}
