package ai.nexusone.service;

import ai.nexusone.dto.request.DeliveryCopilotQueryRequest;
import ai.nexusone.dto.response.DeliveryCopilotAnswerResponse;
import ai.nexusone.dto.response.DeliveryCopilotOverviewResponse;
import ai.nexusone.dto.response.DeliverySignalResponse;
import ai.nexusone.entity.DeliveryCopilotAnalysis;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeliveryCopilotAnalysisRepository;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryIntelligenceCopilotService {
    private final DeploymentExecutionRepository executionRepository;
    private final DeliveryCopilotAnalysisRepository historyRepository;

    public DeliveryIntelligenceCopilotService(
            DeploymentExecutionRepository executionRepository,
            DeliveryCopilotAnalysisRepository historyRepository) {
        this.executionRepository = executionRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "deliveryCopilotOverview")
    public DeliveryCopilotOverviewResponse overview() {
        return toOverview(executionRepository.findAllByOrderByStartedAtDesc());
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "deliveryCopilotSignals")
    public List<DeliverySignalResponse> signals() {
        DeliveryCopilotOverviewResponse value = overview();
        List<DeliverySignalResponse> signals = new ArrayList<>();

        if (value.totalDeployments() == 0) {
            signals.add(new DeliverySignalResponse("NO_DATA", "INFO", "No delivery data",
                    "No deployment execution records are available.",
                    "Run or import deployment executions before requesting delivery intelligence."));
            return List.copyOf(signals);
        }
        if (value.failedDeployments() > 0) {
            signals.add(new DeliverySignalResponse("DELIVERY_FAILURES", "HIGH", "Deployment failures detected",
                    value.failedDeployments() + " deployment execution(s) are marked FAILED.",
                    "Review deployment RCA and validate corrective actions before promotion."));
        }
        if (value.activeDeployments() > 0) {
            signals.add(new DeliverySignalResponse("ACTIVE_DELIVERIES", "MEDIUM", "Deployments still active",
                    value.activeDeployments() + " deployment execution(s) are pending, queued, or running.",
                    "Monitor active executions until they reach a terminal state."));
        }
        if (value.successRate() < 80.0) {
            signals.add(new DeliverySignalResponse("SUCCESS_RATE_LOW", "HIGH", "Success rate below target",
                    "Current delivery success rate is " + value.successRate() + "%.",
                    "Investigate repeated failures and strengthen release gates."));
        }
        if (signals.isEmpty()) {
            signals.add(new DeliverySignalResponse("DELIVERY_STABLE", "LOW", "Delivery signals are stable",
                    "No immediate delivery exception was detected.",
                    "Continue monitored releases and routine validation."));
        }
        return List.copyOf(signals);
    }

    @Transactional
    @CacheEvict(value = "deliveryCopilotHistory", allEntries = true)
    public DeliveryCopilotAnswerResponse ask(DeliveryCopilotQueryRequest query) {
        String repositoryName = normalizeRepository(query.repositoryName());
        List<DeploymentExecutionEntity> executions = "ALL".equals(repositoryName)
                ? executionRepository.findAllByOrderByStartedAtDesc()
                : executionRepository.findByRepositoryNameIgnoreCaseOrderByStartedAtDesc(repositoryName);

        DeliveryCopilotOverviewResponse value = toOverview(executions);
        String recommendation = recommendation(value);
        String answer = ("Delivery scope %s contains %d deployment(s): %d successful, %d failed, " +
                "%d active and %d aborted. Success rate is %.2f%%, failure rate is %.2f%%, " +
                "and average completed duration is %.2f seconds. Current health is %s. Recommendation: %s")
                .formatted(repositoryName, value.totalDeployments(), value.successfulDeployments(),
                        value.failedDeployments(), value.activeDeployments(), value.abortedDeployments(),
                        value.successRate(), value.failureRate(), value.averageDurationSeconds(),
                        value.healthStatus(), recommendation);

        double confidence = value.totalDeployments() == 0 ? 0.40 : Math.min(0.97, 0.70 + value.totalDeployments() * 0.01);
        historyRepository.save(new DeliveryCopilotAnalysis(repositoryName, query.question(), answer, confidence));

        return new DeliveryCopilotAnswerResponse(repositoryName, query.question(), answer, confidence,
                List.of("deployment-executions", "delivery-status", "deployment-duration"), Instant.now());
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "deliveryCopilotHistory", key = "#page + '-' + #size")
    public Page<DeliveryCopilotAnalysis> history(int page, int size) {
        return historyRepository.findAllByOrderByCreatedAtDesc(pageable(page, size));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "deliveryCopilotHistory", key = "#repositoryName + '-' + #page + '-' + #size")
    public Page<DeliveryCopilotAnalysis> historyByRepository(String repositoryName, int page, int size) {
        return historyRepository.findByRepositoryNameIgnoreCaseOrderByCreatedAtDesc(
                repositoryName, pageable(page, size));
    }

    private DeliveryCopilotOverviewResponse toOverview(List<DeploymentExecutionEntity> executions) {
        long total = executions.size();
        long successful = count(executions, DeploymentStatus.SUCCESS);
        long failed = count(executions, DeploymentStatus.FAILED);
        long aborted = count(executions, DeploymentStatus.ABORTED);
        long active = executions.stream().filter(item -> item.getStatus() == DeploymentStatus.PENDING
                || item.getStatus() == DeploymentStatus.QUEUED
                || item.getStatus() == DeploymentStatus.RUNNING).count();
        double averageDuration = executions.stream()
                .filter(item -> item.getStartedAt() != null && item.getCompletedAt() != null)
                .mapToLong(item -> Math.max(0L, Duration.between(item.getStartedAt(), item.getCompletedAt()).getSeconds()))
                .average().orElse(0.0);
        double successRate = percentage(successful, total);
        double failureRate = percentage(failed, total);
        String health = total == 0 ? "NO_DATA" : successRate >= 90 ? "HEALTHY" : successRate >= 70 ? "STABLE" : "ATTENTION_REQUIRED";
        return new DeliveryCopilotOverviewResponse(total, successful, failed, active, aborted,
                successRate, failureRate, round(averageDuration), health, LocalDateTime.now());
    }

    private long count(List<DeploymentExecutionEntity> executions, DeploymentStatus status) {
        return executions.stream().filter(item -> item.getStatus() == status).count();
    }

    private String recommendation(DeliveryCopilotOverviewResponse value) {
        if (value.totalDeployments() == 0) return "Load deployment execution data before making a delivery decision.";
        if (value.failedDeployments() > 0 || value.successRate() < 70.0) return "Pause promotion, review RCA, and validate rollback readiness.";
        if (value.activeDeployments() > 0) return "Monitor active deployments and defer final promotion until completion.";
        return "Proceed with monitored delivery and verify post-deployment health.";
    }

    private String normalizeRepository(String value) {
        return value == null || value.isBlank() ? "ALL" : value.trim();
    }

    private PageRequest pageable(int page, int size) {
        return PageRequest.of(Math.max(0, page), Math.min(100, Math.max(1, size)), Sort.by("createdAt").descending());
    }

    private static double percentage(long value, long total) {
        return total == 0 ? 0.0 : round(value * 100.0 / total);
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
