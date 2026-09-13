package ai.nexusone.service;

import ai.nexusone.dto.DeploymentAdvisorResponse;
import ai.nexusone.dto.DeploymentOverviewResponse;
import ai.nexusone.dto.DeploymentRcaResponse;
import ai.nexusone.dto.DeploymentTrendResponse;
import ai.nexusone.dto.RcaInsightResponse;
import ai.nexusone.dto.RiskTrendResponse;
import ai.nexusone.dto.SelfHealingInsightResponse;
import ai.nexusone.dto.SelfHealingRecommendationResponse;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class DeploymentIntelligenceService {

    private static final EnumSet<DeploymentStatus> IN_PROGRESS_STATUSES =
            EnumSet.of(DeploymentStatus.PENDING, DeploymentStatus.QUEUED, DeploymentStatus.RUNNING);

    private final DeploymentExecutionRepository repository;
    private final DeploymentAdvisorService advisorService;
    private final DeploymentRcaService rcaService;
    private final SelfHealingRecommendationService selfHealingService;

    public DeploymentIntelligenceService(
            DeploymentExecutionRepository repository,
            DeploymentAdvisorService advisorService,
            DeploymentRcaService rcaService,
            SelfHealingRecommendationService selfHealingService) {
        this.repository = repository;
        this.advisorService = advisorService;
        this.rcaService = rcaService;
        this.selfHealingService = selfHealingService;
    }

    @Transactional(readOnly = true)
    public DeploymentOverviewResponse getOverview() {
        List<DeploymentExecutionEntity> executions = repository.findAllByOrderByStartedAtDesc();
        long total = executions.size();
        long successful = countStatus(executions, DeploymentStatus.SUCCESS);
        long failed = countStatus(executions, DeploymentStatus.FAILED);
        long aborted = countStatus(executions, DeploymentStatus.ABORTED);
        long inProgress = executions.stream()
                .filter(item -> item.getStatus() != null && IN_PROGRESS_STATUSES.contains(item.getStatus()))
                .count();

        double averageDuration = executions.stream()
                .filter(item -> item.getStartedAt() != null && item.getCompletedAt() != null)
                .mapToLong(item -> Math.max(0L,
                        Duration.between(item.getStartedAt(), item.getCompletedAt()).getSeconds()))
                .average()
                .orElse(0.0);

        return new DeploymentOverviewResponse(
                total,
                successful,
                failed,
                inProgress,
                aborted,
                percentage(successful, total),
                percentage(failed, total),
                round(averageDuration),
                LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<DeploymentTrendResponse> getDeploymentTrends() {
        Map<LocalDate, TrendAccumulator> grouped = new LinkedHashMap<>();
        repository.findAllByOrderByStartedAtDesc().stream()
                .filter(item -> item.getStartedAt() != null)
                .sorted(Comparator.comparing(DeploymentExecutionEntity::getStartedAt))
                .forEach(item -> grouped.computeIfAbsent(
                                item.getStartedAt().toLocalDate(), ignored -> new TrendAccumulator())
                        .add(item.getStatus()));

        return grouped.entrySet().stream()
                .map(entry -> entry.getValue().toResponse(entry.getKey()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RiskTrendResponse> getRiskTrends() {
        Map<String, Long> counts = new LinkedHashMap<>();
        counts.put("CRITICAL", 0L);
        counts.put("HIGH", 0L);
        counts.put("MEDIUM", 0L);
        counts.put("LOW", 0L);
        counts.put("UNKNOWN", 0L);

        for (DeploymentExecutionEntity execution : repository.findAllByOrderByStartedAtDesc()) {
            DeploymentAdvisorResponse response = advisorService.getInsight(execution.getId());
            String risk = normalize(response.riskLevel());
            counts.merge(counts.containsKey(risk) ? risk : "UNKNOWN", 1L, Long::sum);
        }

        long total = counts.values().stream().mapToLong(Long::longValue).sum();
        return counts.entrySet().stream()
                .map(entry -> new RiskTrendResponse(
                        entry.getKey(), entry.getValue(), percentage(entry.getValue(), total)))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RcaInsightResponse> getRcaInsights() {
        Map<String, RcaAccumulator> grouped = new LinkedHashMap<>();
        for (DeploymentExecutionEntity execution : repository.findAllByOrderByStartedAtDesc()) {
            DeploymentRcaResponse response = rcaService.analyze(execution.getId());
            String code = normalize(response.rootCauseCode());
            grouped.computeIfAbsent(code, ignored -> new RcaAccumulator(
                            safe(response.rootCause()), normalize(response.severity())))
                    .add(response.confidenceScore());
        }

        long total = grouped.values().stream().mapToLong(item -> item.count).sum();
        return grouped.entrySet().stream()
                .map(entry -> entry.getValue().toResponse(entry.getKey(), total))
                .sorted(Comparator.comparingLong(RcaInsightResponse::occurrences).reversed()
                        .thenComparing(RcaInsightResponse::rootCauseCode))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SelfHealingInsightResponse> getSelfHealingInsights() {
        Map<String, SelfHealingAccumulator> grouped = new LinkedHashMap<>();
        for (DeploymentExecutionEntity execution : repository.findAllByOrderByStartedAtDesc()) {
            SelfHealingRecommendationResponse response = selfHealingService.recommend(execution.getId());
            String code = normalize(response.getDiagnosisCode());
            grouped.computeIfAbsent(code, ignored -> new SelfHealingAccumulator(
                            safe(response.getDiagnosis()), normalize(response.getRiskLevel())))
                    .add(response.isHealingRecommended(), response.isHumanApprovalRequired(),
                            response.getConfidenceScore());
        }

        return grouped.entrySet().stream()
                .map(entry -> entry.getValue().toResponse(entry.getKey()))
                .sorted(Comparator.comparingLong(SelfHealingInsightResponse::occurrences).reversed()
                        .thenComparing(SelfHealingInsightResponse::diagnosisCode))
                .toList();
    }

    private long countStatus(List<DeploymentExecutionEntity> executions, DeploymentStatus status) {
        return executions.stream().filter(item -> item.getStatus() == status).count();
    }

    private static String normalize(String value) {
        return value == null || value.isBlank() ? "UNKNOWN" : value.trim().toUpperCase(Locale.ROOT);
    }

    private static String safe(String value) {
        return value == null || value.isBlank() ? "Not available" : value.trim();
    }

    private static double percentage(long value, long total) {
        return total == 0 ? 0.0 : round(value * 100.0 / total);
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private static final class TrendAccumulator {
        private long total;
        private long successful;
        private long failed;
        private long inProgress;
        private long aborted;

        private void add(DeploymentStatus status) {
            total++;
            if (status == DeploymentStatus.SUCCESS) successful++;
            else if (status == DeploymentStatus.FAILED) failed++;
            else if (status == DeploymentStatus.ABORTED) aborted++;
            else if (status != null && IN_PROGRESS_STATUSES.contains(status)) inProgress++;
        }

        private DeploymentTrendResponse toResponse(LocalDate date) {
            return new DeploymentTrendResponse(
                    date, total, successful, failed, inProgress, aborted,
                    percentage(successful, total));
        }
    }

    private static final class RcaAccumulator {
        private final String rootCause;
        private final String severity;
        private long count;
        private long confidenceTotal;

        private RcaAccumulator(String rootCause, String severity) {
            this.rootCause = rootCause;
            this.severity = severity;
        }

        private void add(int confidence) {
            count++;
            confidenceTotal += confidence;
        }

        private RcaInsightResponse toResponse(String code, long total) {
            double average = count == 0 ? 0.0 : round(confidenceTotal * 1.0 / count);
            return new RcaInsightResponse(
                    code, rootCause, severity, count, percentage(count, total), average);
        }
    }

    private static final class SelfHealingAccumulator {
        private final String diagnosis;
        private final String risk;
        private long count;
        private long recommended;
        private long approvalRequired;
        private long confidenceTotal;

        private SelfHealingAccumulator(String diagnosis, String risk) {
            this.diagnosis = diagnosis;
            this.risk = risk;
        }

        private void add(boolean healingRecommended, boolean humanApprovalRequired, int confidence) {
            count++;
            if (healingRecommended) recommended++;
            if (humanApprovalRequired) approvalRequired++;
            confidenceTotal += confidence;
        }

        private SelfHealingInsightResponse toResponse(String code) {
            double average = count == 0 ? 0.0 : round(confidenceTotal * 1.0 / count);
            return new SelfHealingInsightResponse(
                    code, diagnosis, risk, count, recommended, approvalRequired,
                    percentage(recommended, count), average);
        }
    }
}
