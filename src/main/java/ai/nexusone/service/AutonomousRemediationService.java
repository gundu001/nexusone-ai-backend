package ai.nexusone.service;

import ai.nexusone.dto.request.*;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.AutonomousRemediationHistory;
import ai.nexusone.repository.AutonomousRemediationHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AutonomousRemediationService {
    private final AutonomousRemediationHistoryRepository repository;
    private final Map<String, AutonomousRemediationCandidateResponse> candidates;
    private final Map<String, String> workflowStatus = new ConcurrentHashMap<>();
    private final Map<String, String> approvers = new ConcurrentHashMap<>();

    public AutonomousRemediationService(AutonomousRemediationHistoryRepository repository) {
        this.repository = repository;
        this.candidates = seed();
        candidates.keySet().forEach(app -> workflowStatus.put(app, "PLAN_READY"));
    }

    public AutonomousRemediationOverviewResponse overview() {
        long approvals = repository.countByActionTypeAndStatus("APPROVAL", "APPROVED");
        long successful = repository.countByActionTypeAndStatus("EXECUTION", "SIMULATED_SUCCESS");
        long rollbacks = repository.countByActionTypeAndStatus("ROLLBACK", "ROLLED_BACK");
        long executions = repository.countByActionType("EXECUTION");
        double successRate = executions == 0 ? 0.0 : Math.round((successful * 10000.0) / executions) / 100.0;
        return new AutonomousRemediationOverviewResponse(
                candidates.size(), approvals, successful, rollbacks, successRate, repository.count());
    }

    public List<AutonomousRemediationCandidateResponse> candidates() {
        return candidates.values().stream().map(this::withCurrentStatus).toList();
    }

    public AutonomousRemediationPlanResponse plan(String application) {
        return build(candidate(application), "Generate a controlled autonomous remediation plan.");
    }

    public AutonomousRemediationPlanResponse analyze(AutonomousRemediationAnalyzeRequest request) {
        workflowStatus.put(request.application(), "PENDING_APPROVAL");
        return build(candidate(request.application()), request.question());
    }

    public AutonomousRemediationActionResponse approve(AutonomousRemediationApproveRequest request) {
        AutonomousRemediationCandidateResponse c = candidate(request.application());
        String current = workflowStatus.getOrDefault(request.application(), "PLAN_READY");
        if (!Set.of("PLAN_READY", "PENDING_APPROVAL").contains(current)) {
            throw new IllegalStateException("Plan cannot be approved from status: " + current);
        }
        workflowStatus.put(request.application(), "APPROVED");
        approvers.put(request.application(), request.approvedBy());
        return save(c, "APPROVAL", "APPROVED", false, request.approvedBy(), null,
                "Remediation plan approved for dry-run validation.");
    }

    public AutonomousRemediationActionResponse execute(AutonomousRemediationExecuteRequest request) {
        AutonomousRemediationCandidateResponse c = candidate(request.application());
        String current = workflowStatus.getOrDefault(request.application(), "PLAN_READY");
        if (!"APPROVED".equals(current) && !"DRY_RUN_VALIDATED".equals(current)) {
            throw new IllegalStateException("Execution requires approval. Current status: " + current);
        }
        String approvedBy = approvers.get(request.application());
        if (request.dryRun()) {
            workflowStatus.put(request.application(), "DRY_RUN_VALIDATED");
            return save(c, "EXECUTION", "DRY_RUN_VALIDATED", true, approvedBy, null,
                    "All simulated prechecks, execution steps, validation checks, and rollback checks passed. No infrastructure change was executed.");
        }
        if (!"DRY_RUN_VALIDATED".equals(current)) {
            throw new IllegalStateException("Demo execution requires a successful dry run first.");
        }
        workflowStatus.put(request.application(), "SIMULATED_SUCCESS");
        return save(c, "EXECUTION", "SIMULATED_SUCCESS", false, approvedBy, null,
                "Demo remediation completed and target-state validation passed. No external infrastructure API was called.");
    }

    public AutonomousRemediationActionResponse rollback(AutonomousRemediationRollbackRequest request) {
        AutonomousRemediationCandidateResponse c = candidate(request.application());
        String current = workflowStatus.getOrDefault(request.application(), "PLAN_READY");
        if (!"SIMULATED_SUCCESS".equals(current)) {
            throw new IllegalStateException("Rollback is available only after simulated execution. Current status: " + current);
        }
        workflowStatus.put(request.application(), "ROLLED_BACK");
        return save(c, "ROLLBACK", "ROLLED_BACK", false,
                approvers.get(request.application()), request.requestedBy(),
                "Demo rollback completed. Reason: " + request.reason());
    }

    public Page<AutonomousRemediationHistory> history(int page, int size) {
        return repository.findAllByOrderByCreatedAtDesc(pageable(page, size));
    }

    public Page<AutonomousRemediationHistory> historyByApplication(String application, int page, int size) {
        candidate(application);
        return repository.findByApplicationOrderByCreatedAtDesc(application, pageable(page, size));
    }

    private AutonomousRemediationActionResponse save(AutonomousRemediationCandidateResponse c,
            String actionType, String status, boolean dryRun, String approvedBy,
            String requestedBy, String result) {
        AutonomousRemediationHistory saved = repository.save(
                new AutonomousRemediationHistory(c.application(), actionType,
                        c.recommendedAction(), status, dryRun, approvedBy, requestedBy, result));
        return new AutonomousRemediationActionResponse(saved.getId(), saved.getApplication(),
                saved.getActionType(), saved.getStatus(), saved.isDryRun(), saved.getApprovedBy(),
                saved.getRequestedBy(), saved.getResult(), saved.getCreatedAt());
    }

    private AutonomousRemediationCandidateResponse candidate(String application) {
        AutonomousRemediationCandidateResponse value = candidates.get(application);
        if (value == null) throw new NoSuchElementException("Application not found: " + application);
        return value;
    }

    private AutonomousRemediationCandidateResponse withCurrentStatus(AutonomousRemediationCandidateResponse c) {
        return new AutonomousRemediationCandidateResponse(c.candidateId(), c.application(), c.environment(),
                c.issue(), c.recommendedAction(), c.confidence(), c.riskLevel(),
                workflowStatus.getOrDefault(c.application(), c.status()), c.approvalRequired(),
                c.detectedAt(), c.evidence());
    }

    private PageRequest pageable(int page, int size) {
        return PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 100),
                Sort.by("createdAt").descending());
    }

    private AutonomousRemediationPlanResponse build(
            AutonomousRemediationCandidateResponse c, String question) {
        String status = workflowStatus.getOrDefault(c.application(), "PLAN_READY");
        if ("checkout-service".equals(c.application())) {
            return response(c, question, "Database connection demand exceeds the safe pool capacity.",
                    "Stable checkout traffic with database pool utilization below 75% and HTTP 5xx below 2%.",
                    List.of("DB pool utilization is 94%", "HTTP 5xx is 18%", "Retry volume increased 38%"),
                    List.of("Enable guarded request throttling", "Reduce retry concurrency", "Monitor pool recovery"),
                    List.of("Pool utilization below 75%", "HTTP 5xx below 2%", "Checkout smoke test passes"),
                    List.of("Remove temporary throttle", "Restore previous retry policy", "Escalate to database owner"), status);
        }
        if ("order-worker".equals(c.application())) {
            return response(c, question, "Worker capacity cannot match the current queue arrival rate.",
                    "Queue lag returns below threshold while CPU remains below 70%.",
                    List.of("Queue lag is rising", "CPU is 82%", "HPA is near maximum replicas"),
                    List.of("Raise approved replica ceiling", "Scale by two worker replicas", "Monitor backlog drain"),
                    List.of("Queue lag decreases", "CPU below 70%", "Processing error rate remains stable"),
                    List.of("Restore previous replica ceiling", "Scale down added replicas", "Escalate capacity review"), status);
        }
        return response(c, question, "Required staging configuration is missing.",
                "All customer-api pods are ready and configuration drift is cleared.",
                List.of("Required key is absent", "Readiness probe failed", "Manifest drift detected"),
                List.of("Restore validated configuration", "Restart only unhealthy pods", "Run readiness checks"),
                List.of("All pods ready", "Smoke tests pass", "Configuration drift cleared"),
                List.of("Restore previous manifest", "Roll back deployment", "Open configuration incident"), status);
    }

    private AutonomousRemediationPlanResponse response(AutonomousRemediationCandidateResponse c,
            String question, String diagnosis, String targetState, List<String> evidence,
            List<String> executionSteps, List<String> validationChecks,
            List<String> rollbackSteps, String status) {
        return new AutonomousRemediationPlanResponse(c.application(), question, diagnosis,
                targetState, c.recommendedAction(), c.confidence(), c.riskLevel(), status,
                c.approvalRequired(), evidence, executionSteps, validationChecks,
                rollbackSteps, Instant.now());
    }

    private Map<String, AutonomousRemediationCandidateResponse> seed() {
        Map<String, AutonomousRemediationCandidateResponse> values = new LinkedHashMap<>();
        values.put("checkout-service", new AutonomousRemediationCandidateResponse(
                "REM-6901", "checkout-service", "production", "Database pool saturation",
                "Apply guarded traffic throttling", 0.94, "CRITICAL", "PLAN_READY", true,
                Instant.parse("2026-09-19T06:15:00Z"),
                List.of("DB pool 94%", "HTTP 5xx 18%", "Retry volume +38%")));
        values.put("order-worker", new AutonomousRemediationCandidateResponse(
                "REM-6902", "order-worker", "production", "Queue backlog growth",
                "Scale workers by two replicas", 0.88, "HIGH", "PLAN_READY", true,
                Instant.parse("2026-09-19T06:10:00Z"),
                List.of("Queue lag rising", "CPU 82%", "HPA near maximum")));
        values.put("customer-api", new AutonomousRemediationCandidateResponse(
                "REM-6903", "customer-api", "staging", "Readiness configuration drift",
                "Restore validated configuration and restart", 0.96, "CRITICAL", "PLAN_READY", true,
                Instant.parse("2026-09-19T06:05:00Z"),
                List.of("Required key missing", "Probe failed", "Manifest drift detected")));
        return values;
    }
}
