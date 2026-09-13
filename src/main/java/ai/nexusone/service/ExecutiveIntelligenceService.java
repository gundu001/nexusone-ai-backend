package ai.nexusone.service;

import ai.nexusone.dto.ExecutiveBusinessKpiResponse;
import ai.nexusone.dto.ExecutiveInsightResponse;
import ai.nexusone.dto.ExecutiveOverviewResponse;
import ai.nexusone.dto.ExecutivePlatformHealthResponse;
import ai.nexusone.dto.ExecutiveReliabilityResponse;
import ai.nexusone.dto.ExecutiveTrendResponse;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExecutiveIntelligenceService {

    private final DeploymentExecutionRepository repository;

    public ExecutiveIntelligenceService(DeploymentExecutionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ExecutiveOverviewResponse getOverview() {
        List<DeploymentExecutionEntity> all = loadExecutions();
        long total = all.size();
        long successful = count(all, DeploymentStatus.SUCCESS);
        long failed = count(all, DeploymentStatus.FAILED);
        long active = all.stream().filter(item -> isActive(item.getStatus())).count();
        long aborted = count(all, DeploymentStatus.ABORTED);

        double successRate = percentage(successful, total);
        double failureRate = percentage(failed, total);
        double averageDuration = averageDurationSeconds(all);
        double platformHealth = calculatePlatformHealthScore(total, successful, failed, active, aborted);
        double governance = calculateGovernanceScore(total, failed, active, aborted);
        double selfHealing = calculateSelfHealingEffectiveness(total, failed, aborted);
        String status = healthStatus(platformHealth);

        return new ExecutiveOverviewResponse(
                total,
                successful,
                failed,
                active,
                successRate,
                failureRate,
                averageDuration,
                platformHealth,
                governance,
                selfHealing,
                status,
                LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public ExecutiveBusinessKpiResponse getBusinessKpis() {
        List<DeploymentExecutionEntity> all = loadExecutions();
        long total = all.size();
        long successful = count(all, DeploymentStatus.SUCCESS);
        long failed = count(all, DeploymentStatus.FAILED);
        long aborted = count(all, DeploymentStatus.ABORTED);
        long active = all.stream().filter(item -> isActive(item.getStatus())).count();
        long completed = successful + failed + aborted;

        double successRate = percentage(successful, total);
        double failureRate = percentage(failed, total);
        double completionRate = percentage(completed, total);
        double throughput = calculateReleaseThroughputPerDay(all);

        String rating;
        if (total == 0) {
            rating = "NO_DATA";
        } else if (successRate >= 95.0) {
            rating = "EXCELLENT";
        } else if (successRate >= 80.0) {
            rating = "GOOD";
        } else if (successRate >= 60.0) {
            rating = "FAIR";
        } else {
            rating = "NEEDS_IMPROVEMENT";
        }

        return new ExecutiveBusinessKpiResponse(
                total,
                completed,
                successful,
                failed,
                active,
                aborted,
                successRate,
                failureRate,
                completionRate,
                averageDurationSeconds(all),
                throughput,
                rating,
                LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public ExecutivePlatformHealthResponse getPlatformHealth() {
        List<DeploymentExecutionEntity> all = loadExecutions();
        long total = all.size();
        long successful = count(all, DeploymentStatus.SUCCESS);
        long failed = count(all, DeploymentStatus.FAILED);
        long aborted = count(all, DeploymentStatus.ABORTED);
        long unknown = count(all, DeploymentStatus.UNKNOWN);
        long active = all.stream().filter(item -> isActive(item.getStatus())).count();
        long unhealthy = failed + aborted + unknown;
        double score = calculatePlatformHealthScore(total, successful, failed, active, aborted);

        List<String> signals = new ArrayList<>();
        if (total == 0) {
            signals.add("No deployment execution data is available.");
        } else {
            signals.add(successful + " deployment(s) completed successfully.");
            if (active > 0) {
                signals.add(active + " deployment(s) are currently active.");
            }
            if (unhealthy > 0) {
                signals.add(unhealthy + " deployment(s) require operational review.");
            }
            if (failed == 0) {
                signals.add("No failed deployment is currently recorded.");
            }
        }

        return new ExecutivePlatformHealthResponse(
                score,
                healthStatus(score),
                total,
                successful,
                active,
                unhealthy,
                percentage(successful, total),
                percentage(active, total),
                percentage(failed, total),
                List.copyOf(signals),
                LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public ExecutiveReliabilityResponse getReliability() {
        List<DeploymentExecutionEntity> all = loadExecutions();
        long successful = count(all, DeploymentStatus.SUCCESS);
        long failed = count(all, DeploymentStatus.FAILED);
        long completed = successful + failed + count(all, DeploymentStatus.ABORTED);
        double successRate = percentage(successful, all.size());
        double failureRate = percentage(failed, all.size());
        double meanTimeToRecovery = all.stream()
                .filter(item -> item.getStatus() == DeploymentStatus.SUCCESS)
                .filter(item -> item.getStartedAt() != null && item.getCompletedAt() != null)
                .mapToLong(item -> Math.max(0L,
                        Duration.between(item.getStartedAt(), item.getCompletedAt()).getSeconds()))
                .average()
                .orElse(0.0);

        String rating;
        if (all.isEmpty()) {
            rating = "NO_DATA";
        } else if (successRate >= 95.0) {
            rating = "EXCELLENT";
        } else if (successRate >= 80.0) {
            rating = "GOOD";
        } else if (successRate >= 60.0) {
            rating = "FAIR";
        } else {
            rating = "NEEDS_IMPROVEMENT";
        }

        return new ExecutiveReliabilityResponse(
                successRate,
                failureRate,
                averageDurationSeconds(all),
                round(meanTimeToRecovery),
                completed,
                failed,
                successful,
                rating);
    }

    @Transactional(readOnly = true)
    public List<ExecutiveTrendResponse> getTrends() {
        Map<LocalDate, TrendBucket> buckets = new LinkedHashMap<>();
        loadExecutions().stream()
                .filter(item -> item.getStartedAt() != null)
                .sorted(Comparator.comparing(DeploymentExecutionEntity::getStartedAt))
                .forEach(item -> buckets
                        .computeIfAbsent(item.getStartedAt().toLocalDate(), ignored -> new TrendBucket())
                        .add(item.getStatus()));

        return buckets.entrySet().stream()
                .map(entry -> entry.getValue().toResponse(entry.getKey()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ExecutiveInsightResponse> getAiInsights() {
        ExecutiveOverviewResponse overview = getOverview();
        List<ExecutiveInsightResponse> insights = new ArrayList<>();

        if (overview.activeDeployments() > 0) {
            insights.add(new ExecutiveInsightResponse(
                    "ACTIVE_PIPELINES",
                    "OPERATIONS",
                    "INFO",
                    "Deployments are still active",
                    overview.activeDeployments() + " deployment(s) are pending, queued, or running.",
                    "Monitor active pipelines until they reach a terminal state."));
        }
        if (overview.failedDeployments() > 0) {
            insights.add(new ExecutiveInsightResponse(
                    "FAILURES_DETECTED",
                    "RELIABILITY",
                    "HIGH",
                    "Deployment failures require attention",
                    overview.failedDeployments() + " failed deployment(s) were detected.",
                    "Review RCA and self-healing recommendations before the next promotion."));
        }
        if (overview.totalDeployments() > 0 && overview.successRate() < 80.0) {
            insights.add(new ExecutiveInsightResponse(
                    "SUCCESS_RATE_BELOW_TARGET",
                    "GOVERNANCE",
                    "MEDIUM",
                    "Success rate needs improvement",
                    "The current deployment success rate is " + overview.successRate() + "%.",
                    "Complete active executions and investigate failed or aborted releases."));
        }
        if (insights.isEmpty()) {
            insights.add(new ExecutiveInsightResponse(
                    "PLATFORM_STABLE",
                    "PLATFORM",
                    "LOW",
                    "Platform signals are stable",
                    "No immediate executive deployment exception was detected.",
                    "Continue controlled releases and routine monitoring."));
        }
        return List.copyOf(insights);
    }

    private List<DeploymentExecutionEntity> loadExecutions() {
        return repository.findAllByOrderByStartedAtDesc();
    }

    private long count(List<DeploymentExecutionEntity> all, DeploymentStatus status) {
        return all.stream().filter(item -> item.getStatus() == status).count();
    }

    private boolean isActive(DeploymentStatus status) {
        return status == DeploymentStatus.PENDING
                || status == DeploymentStatus.QUEUED
                || status == DeploymentStatus.RUNNING;
    }

    private double averageDurationSeconds(List<DeploymentExecutionEntity> all) {
        return round(all.stream()
                .filter(item -> item.getStartedAt() != null && item.getCompletedAt() != null)
                .mapToLong(item -> Math.max(0L,
                        Duration.between(item.getStartedAt(), item.getCompletedAt()).getSeconds()))
                .average()
                .orElse(0.0));
    }

    private double calculateReleaseThroughputPerDay(List<DeploymentExecutionEntity> all) {
        List<LocalDate> dates = all.stream()
                .filter(item -> item.getStartedAt() != null)
                .map(item -> item.getStartedAt().toLocalDate())
                .sorted()
                .toList();
        if (dates.isEmpty()) {
            return 0.0;
        }
        long inclusiveDays = Math.max(1L,
                ChronoUnit.DAYS.between(dates.get(0), dates.get(dates.size() - 1)) + 1L);
        return round(all.size() * 1.0 / inclusiveDays);
    }

    private double calculatePlatformHealthScore(
            long total, long successful, long failed, long active, long aborted) {
        if (total == 0) {
            return 0.0;
        }
        double successContribution = percentage(successful, total) * 0.80;
        double activeContribution = percentage(active, total) * 0.10;
        double stabilityContribution = Math.max(0.0,
                100.0 - percentage(failed + aborted, total)) * 0.10;
        return clamp(successContribution + activeContribution + stabilityContribution);
    }

    private double calculateGovernanceScore(long total, long failed, long active, long aborted) {
        if (total == 0) {
            return 0.0;
        }
        return clamp(100.0
                - percentage(failed, total)
                - percentage(aborted, total)
                - percentage(active, total) * 0.25);
    }

    private double calculateSelfHealingEffectiveness(long total, long failed, long aborted) {
        if (total == 0) {
            return 0.0;
        }
        return clamp(100.0 - percentage(failed + aborted, total));
    }

    private String healthStatus(double score) {
        if (score >= 90.0) {
            return "HEALTHY";
        }
        if (score >= 70.0) {
            return "STABLE";
        }
        return "ATTENTION_REQUIRED";
    }

    private static double percentage(long value, long total) {
        return total == 0 ? 0.0 : round(value * 100.0 / total);
    }

    private static double clamp(double value) {
        return round(Math.max(0.0, Math.min(100.0, value)));
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private static final class TrendBucket {
        private long total;
        private long successful;
        private long failed;
        private long active;

        private void add(DeploymentStatus status) {
            total++;
            if (status == DeploymentStatus.SUCCESS) {
                successful++;
            } else if (status == DeploymentStatus.FAILED) {
                failed++;
            } else if (status == DeploymentStatus.PENDING
                    || status == DeploymentStatus.QUEUED
                    || status == DeploymentStatus.RUNNING) {
                active++;
            }
        }

        private ExecutiveTrendResponse toResponse(LocalDate date) {
            return new ExecutiveTrendResponse(
                    date,
                    total,
                    successful,
                    failed,
                    active,
                    percentage(successful, total));
        }
    }
}
