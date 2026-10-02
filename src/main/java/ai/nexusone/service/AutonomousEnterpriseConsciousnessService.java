package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.AutonomousEnterpriseConsciousnessReport;
import ai.nexusone.enums.*;
import ai.nexusone.exception.AutonomousEnterpriseConsciousnessNotFoundException;
import ai.nexusone.repository.AutonomousEnterpriseConsciousnessRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.ToDoubleFunction;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutonomousEnterpriseConsciousnessService {
    private final AutonomousEnterpriseConsciousnessRepository repository;

    public AutonomousEnterpriseConsciousnessService(
            AutonomousEnterpriseConsciousnessRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AutonomousEnterpriseConsciousnessResponse generate(
            AutonomousEnterpriseConsciousnessRequest request) {
        AutonomousEnterpriseConsciousnessReport report =
                new AutonomousEnterpriseConsciousnessReport();
        report.setTitle(request.title());
        report.setEnterpriseAwareness(request.enterpriseAwareness());
        report.setEnterpriseAwarenessScore(request.enterpriseAwarenessScore());
        report.setContextUnderstanding(request.contextUnderstanding());
        report.setContextUnderstandingScore(request.contextUnderstandingScore());
        report.setDecisionMemory(request.decisionMemory());
        report.setDecisionMemoryScore(request.decisionMemoryScore());
        report.setReasoningIntelligence(request.reasoningIntelligence());
        report.setReasoningIntelligenceScore(request.reasoningIntelligenceScore());
        report.setAdaptiveLearning(request.adaptiveLearning());
        report.setAdaptiveLearningScore(request.adaptiveLearningScore());
        report.setPredictiveAwareness(request.predictiveAwareness());
        report.setPredictiveAwarenessScore(request.predictiveAwarenessScore());
        report.setSelfOptimization(request.selfOptimization());
        report.setSelfOptimizationScore(request.selfOptimizationScore());
        report.setGoalAlignment(request.goalAlignment());
        report.setGoalAlignmentScore(request.goalAlignmentScore());
        report.setStrategicConsciousness(request.strategicConsciousness());
        report.setStrategicConsciousnessScore(request.strategicConsciousnessScore());
        report.setConsciousnessRecommendations(request.consciousnessRecommendations());
        report.setAutonomousEnterpriseConsciousnessScore(calculateScore(request));
        report.setPriority(request.priority());
        report.setStatus(AutonomousEnterpriseConsciousnessStatus.GENERATED);
        report.setCreatedBy(request.createdBy());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public AutonomousEnterpriseConsciousnessResponse get(Long id) { return map(find(id)); }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseConsciousnessResponse> history(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseConsciousnessResponse> search(
            String keyword,
            AutonomousEnterpriseConsciousnessPriority priority,
            AutonomousEnterpriseConsciousnessStatus status,
            Pageable pageable) {
        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .findByTitleContainingIgnoreCaseOrEnterpriseAwarenessContainingIgnoreCaseOrConsciousnessRecommendationsContainingIgnoreCase(
                            keyword, keyword, keyword, pageable)
                    .map(this::map);
        }
        if (priority != null) return repository.findByPriority(priority, pageable).map(this::map);
        if (status != null) return repository.findByStatus(status, pageable).map(this::map);
        return history(pageable);
    }

    @Transactional
    public AutonomousEnterpriseConsciousnessResponse review(
            Long id, AutonomousEnterpriseConsciousnessActionRequest request) {
        var report = find(id);
        require(report, AutonomousEnterpriseConsciousnessStatus.GENERATED,
                "Only GENERATED reports can be reviewed");
        report.setStatus(AutonomousEnterpriseConsciousnessStatus.REVIEWED);
        report.setReviewedBy(request.actionBy());
        report.setReviewedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public AutonomousEnterpriseConsciousnessResponse approve(
            Long id, AutonomousEnterpriseConsciousnessActionRequest request) {
        var report = find(id);
        require(report, AutonomousEnterpriseConsciousnessStatus.REVIEWED,
                "Only REVIEWED reports can be approved");
        report.setStatus(AutonomousEnterpriseConsciousnessStatus.APPROVED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public AutonomousEnterpriseConsciousnessResponse reject(
            Long id, AutonomousEnterpriseConsciousnessActionRequest request) {
        var report = find(id);
        if (report.getStatus() != AutonomousEnterpriseConsciousnessStatus.GENERATED
                && report.getStatus() != AutonomousEnterpriseConsciousnessStatus.REVIEWED) {
            throw new IllegalArgumentException(
                    "Only GENERATED or REVIEWED reports can be rejected");
        }
        report.setStatus(AutonomousEnterpriseConsciousnessStatus.REJECTED);
        report.setDecidedBy(request.actionBy());
        report.setDecidedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional
    public AutonomousEnterpriseConsciousnessResponse publish(
            Long id, AutonomousEnterpriseConsciousnessActionRequest request) {
        var report = find(id);
        require(report, AutonomousEnterpriseConsciousnessStatus.APPROVED,
                "Only APPROVED reports can be published");
        report.setStatus(AutonomousEnterpriseConsciousnessStatus.PUBLISHED);
        report.setPublishedBy(request.actionBy());
        report.setPublishedAt(LocalDateTime.now());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public AutonomousEnterpriseConsciousnessAnalyticsResponse analytics() {
        List<AutonomousEnterpriseConsciousnessReport> reports = repository.findAll();
        return new AutonomousEnterpriseConsciousnessAnalyticsResponse(
                reports.size(),
                count(AutonomousEnterpriseConsciousnessStatus.GENERATED),
                count(AutonomousEnterpriseConsciousnessStatus.REVIEWED),
                count(AutonomousEnterpriseConsciousnessStatus.APPROVED),
                count(AutonomousEnterpriseConsciousnessStatus.REJECTED),
                count(AutonomousEnterpriseConsciousnessStatus.PUBLISHED),
                average(reports, AutonomousEnterpriseConsciousnessReport::getEnterpriseAwarenessScore),
                average(reports, AutonomousEnterpriseConsciousnessReport::getContextUnderstandingScore),
                average(reports, AutonomousEnterpriseConsciousnessReport::getDecisionMemoryScore),
                average(reports, AutonomousEnterpriseConsciousnessReport::getReasoningIntelligenceScore),
                average(reports, AutonomousEnterpriseConsciousnessReport::getAdaptiveLearningScore),
                average(reports, AutonomousEnterpriseConsciousnessReport::getPredictiveAwarenessScore),
                average(reports, AutonomousEnterpriseConsciousnessReport::getSelfOptimizationScore),
                average(reports, AutonomousEnterpriseConsciousnessReport::getGoalAlignmentScore),
                average(reports, AutonomousEnterpriseConsciousnessReport::getStrategicConsciousnessScore),
                average(reports, AutonomousEnterpriseConsciousnessReport::getAutonomousEnterpriseConsciousnessScore));
    }

    private double calculateScore(AutonomousEnterpriseConsciousnessRequest request) {
        return round(request.enterpriseAwarenessScore() * 0.14
                + request.contextUnderstandingScore() * 0.13
                + request.decisionMemoryScore() * 0.12
                + request.reasoningIntelligenceScore() * 0.12
                + request.adaptiveLearningScore() * 0.11
                + request.predictiveAwarenessScore() * 0.11
                + request.selfOptimizationScore() * 0.10
                + request.goalAlignmentScore() * 0.09
                + request.strategicConsciousnessScore() * 0.08);
    }

    private double average(List<AutonomousEnterpriseConsciousnessReport> reports,
            ToDoubleFunction<AutonomousEnterpriseConsciousnessReport> field) {
        return round(reports.stream().mapToDouble(field).average().orElse(0));
    }

    private long count(AutonomousEnterpriseConsciousnessStatus status) {
        return repository.countByStatus(status);
    }

    private double round(double value) { return Math.round(value * 100.0) / 100.0; }

    private void require(AutonomousEnterpriseConsciousnessReport report,
            AutonomousEnterpriseConsciousnessStatus expected, String message) {
        if (report.getStatus() != expected) throw new IllegalArgumentException(message);
    }

    private AutonomousEnterpriseConsciousnessReport find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AutonomousEnterpriseConsciousnessNotFoundException(id));
    }

    private AutonomousEnterpriseConsciousnessResponse map(
            AutonomousEnterpriseConsciousnessReport report) {
        return new AutonomousEnterpriseConsciousnessResponse(
                report.getId(),
                report.getTitle(),
                report.getEnterpriseAwareness(),
                report.getContextUnderstanding(),
                report.getDecisionMemory(),
                report.getReasoningIntelligence(),
                report.getAdaptiveLearning(),
                report.getPredictiveAwareness(),
                report.getSelfOptimization(),
                report.getGoalAlignment(),
                report.getStrategicConsciousness(),
                report.getConsciousnessRecommendations(),
                report.getEnterpriseAwarenessScore(),
                report.getContextUnderstandingScore(),
                report.getDecisionMemoryScore(),
                report.getReasoningIntelligenceScore(),
                report.getAdaptiveLearningScore(),
                report.getPredictiveAwarenessScore(),
                report.getSelfOptimizationScore(),
                report.getGoalAlignmentScore(),
                report.getStrategicConsciousnessScore(),
                report.getAutonomousEnterpriseConsciousnessScore(),
                report.getPriority(),
                report.getStatus(),
                report.getCreatedBy(),
                report.getReviewedBy(),
                report.getReviewedAt(),
                report.getDecidedBy(),
                report.getDecidedAt(),
                report.getPublishedBy(),
                report.getPublishedAt(),
                report.getCreatedAt(),
                report.getUpdatedAt());
    }
}
