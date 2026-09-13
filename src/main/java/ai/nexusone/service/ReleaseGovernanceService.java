package ai.nexusone.service;

import ai.nexusone.dto.CabRecommendationResponse;
import ai.nexusone.dto.ComplianceResponse;
import ai.nexusone.dto.GoNoGoDecisionResponse;
import ai.nexusone.dto.GovernanceOverviewResponse;
import ai.nexusone.dto.PolicyViolationResponse;
import ai.nexusone.dto.ReleaseReadinessResponse;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReleaseGovernanceService {
    private final DeploymentExecutionRepository repository;

    public ReleaseGovernanceService(DeploymentExecutionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public GovernanceOverviewResponse getOverview() {
        List<DeploymentExecutionEntity> all = repository.findAllByOrderByStartedAtDesc();
        long ready = all.stream().filter(this::isReady).count();
        long blocked = all.stream().filter(this::isBlocked).count();
        long review = all.size() - ready - blocked;
        double averageReadiness = all.stream().mapToDouble(this::readinessScore).average().orElse(0.0);
        long compliant = all.stream().filter(item -> violations(item).isEmpty()).count();
        long violationCount = all.stream().mapToLong(item -> violations(item).size()).sum();
        double complianceRate = percentage(compliant, all.size());
        String decision = blocked > 0 ? "NO_GO" : review > 0 ? "CONDITIONAL_GO" : all.isEmpty() ? "NO_DATA" : "GO";
        String risk = blocked > 0 ? "HIGH" : review > 0 ? "MEDIUM" : all.isEmpty() ? "UNKNOWN" : "LOW";
        return new GovernanceOverviewResponse(all.size(), ready, blocked, review, round(averageReadiness),
                complianceRate, violationCount, decision, risk, LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public ReleaseReadinessResponse getReleaseReadiness(Long executionId) {
        DeploymentExecutionEntity item = find(executionId);
        List<String> passed = new ArrayList<>();
        List<String> blocked = new ArrayList<>();
        check(item.getStatus() == DeploymentStatus.SUCCESS, "Execution completed successfully", "Execution must complete successfully", passed, blocked);
        check(item.getBuildNumber() != null, "Jenkins build number is available", "Jenkins build number is missing", passed, blocked);
        check(notBlank(item.getRepositoryName()), "Repository is identified", "Repository name is missing", passed, blocked);
        check(notBlank(item.getJobName()), "Jenkins job is identified", "Jenkins job name is missing", passed, blocked);
        check(item.getCompletedAt() != null, "Completion timestamp is available", "Completion timestamp is missing", passed, blocked);
        double score = percentage(passed.size(), passed.size() + blocked.size());
        String level = score >= 90 ? "READY" : score >= 60 ? "REVIEW_REQUIRED" : "NOT_READY";
        return new ReleaseReadinessResponse(item.getId(), item.getRepositoryName(), item.getJobName(), name(item.getStatus()),
                score, level, blocked.isEmpty(), List.copyOf(passed), List.copyOf(blocked), LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public ComplianceResponse getCompliance(Long executionId) {
        DeploymentExecutionEntity item = find(executionId);
        List<String> passed = new ArrayList<>();
        List<String> failed = new ArrayList<>();
        check(notBlank(item.getTriggeredBy()), "Release initiator is recorded", "Release initiator must be recorded", passed, failed);
        check(notBlank(item.getRepositoryName()), "Repository traceability is present", "Repository traceability is missing", passed, failed);
        check(notBlank(item.getJobName()), "Pipeline traceability is present", "Pipeline traceability is missing", passed, failed);
        check(item.getBuildNumber() != null, "Build evidence is present", "Build evidence is missing", passed, failed);
        check(item.getStatus() == DeploymentStatus.SUCCESS, "Release has a successful terminal result", "Release does not have a successful terminal result", passed, failed);
        double score = percentage(passed.size(), passed.size() + failed.size());
        return new ComplianceResponse(item.getId(), failed.isEmpty(), score, passed.size(), failed.size(),
                List.copyOf(passed), List.copyOf(failed), LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<PolicyViolationResponse> getPolicyViolations(Long executionId) {
        return List.copyOf(violations(find(executionId)));
    }

    @Transactional(readOnly = true)
    public GoNoGoDecisionResponse getGoNoGoDecision(Long executionId) {
        DeploymentExecutionEntity item = find(executionId);
        List<PolicyViolationResponse> violations = violations(item);
        boolean blocking = violations.stream().anyMatch(PolicyViolationResponse::blocking);
        String decision;
        String risk;
        boolean approval;
        double confidence;
        if (item.getStatus() == DeploymentStatus.SUCCESS && violations.isEmpty()) {
            decision = "GO"; risk = "LOW"; approval = false; confidence = 95;
        } else if (blocking) {
            decision = "NO_GO"; risk = "HIGH"; approval = true; confidence = 95;
        } else {
            decision = "CONDITIONAL_GO"; risk = "MEDIUM"; approval = true; confidence = 80;
        }
        List<String> conditions = violations.stream().map(PolicyViolationResponse::remediation).distinct().toList();
        String rationale = decision.equals("GO") ? "All configured governance checks passed."
                : violations.size() + " governance policy violation(s) require resolution or approval.";
        return new GoNoGoDecisionResponse(item.getId(), decision, confidence, risk, approval, rationale,
                conditions, LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public CabRecommendationResponse getCabRecommendation(Long executionId) {
        DeploymentExecutionEntity item = find(executionId);
        GoNoGoDecisionResponse decision = getGoNoGoDecision(executionId);
        boolean review = !"GO".equals(decision.decision());
        String recommendation = review ? "CAB_REVIEW_REQUIRED" : "STANDARD_APPROVAL_PATH";
        String priority = "HIGH".equals(decision.riskLevel()) ? "URGENT" : review ? "NORMAL" : "LOW";
        List<String> topics = new ArrayList<>();
        topics.add("Go/No-Go decision: " + decision.decision());
        topics.add("Execution status: " + name(item.getStatus()));
        if (!decision.conditions().isEmpty()) topics.add("Open governance conditions: " + decision.conditions().size());
        List<String> evidence = new ArrayList<>();
        evidence.add("Repository: " + safe(item.getRepositoryName()));
        evidence.add("Jenkins job: " + safe(item.getJobName()));
        evidence.add("Build number: " + (item.getBuildNumber() == null ? "not available" : item.getBuildNumber()));
        evidence.add("Triggered by: " + safe(item.getTriggeredBy()));
        return new CabRecommendationResponse(item.getId(), recommendation, priority, review,
                review ? "CAB review is recommended before release promotion." : "The release can use the standard approval path.",
                List.copyOf(topics), List.copyOf(evidence), LocalDateTime.now());
    }

    private DeploymentExecutionEntity find(Long id) {
        if (id == null) throw new IllegalArgumentException("Execution ID is required");
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Deployment execution not found for id: " + id));
    }

    private List<PolicyViolationResponse> violations(DeploymentExecutionEntity item) {
        List<PolicyViolationResponse> result = new ArrayList<>();
        if (item.getStatus() != DeploymentStatus.SUCCESS) result.add(violation(item, "GOV-001", "Successful execution required", "HIGH", "Promotion requires a successful terminal execution.", "Wait for success or resolve the execution failure before promotion.", true));
        if (item.getBuildNumber() == null) result.add(violation(item, "GOV-002", "Build evidence required", "MEDIUM", "No Jenkins build number is recorded.", "Refresh the execution until the Jenkins build number is available.", true));
        if (!notBlank(item.getTriggeredBy())) result.add(violation(item, "GOV-003", "Release initiator required", "MEDIUM", "The release initiator is not recorded.", "Record the authenticated release initiator.", true));
        if (!notBlank(item.getRepositoryName()) || !notBlank(item.getJobName())) result.add(violation(item, "GOV-004", "Traceability required", "HIGH", "Repository or pipeline traceability is incomplete.", "Provide valid repository and Jenkins job identifiers.", true));
        if (item.getCompletedAt() == null && !isActive(item.getStatus())) result.add(violation(item, "GOV-005", "Completion evidence required", "MEDIUM", "Terminal execution has no completion timestamp.", "Synchronize the terminal execution details from Jenkins.", false));
        return result;
    }

    private PolicyViolationResponse violation(DeploymentExecutionEntity item, String code, String policy, String severity, String description, String remediation, boolean blocking) {
        return new PolicyViolationResponse(item.getId(), code, policy, severity, description, remediation, blocking);
    }
    private boolean isReady(DeploymentExecutionEntity item) { return item.getStatus() == DeploymentStatus.SUCCESS && violations(item).isEmpty(); }
    private boolean isBlocked(DeploymentExecutionEntity item) { return violations(item).stream().anyMatch(PolicyViolationResponse::blocking); }
    private boolean isActive(DeploymentStatus status) { return status == DeploymentStatus.PENDING || status == DeploymentStatus.QUEUED || status == DeploymentStatus.RUNNING; }
    private double readinessScore(DeploymentExecutionEntity item) { int passed=0; if(item.getStatus()==DeploymentStatus.SUCCESS)passed++; if(item.getBuildNumber()!=null)passed++; if(notBlank(item.getRepositoryName()))passed++; if(notBlank(item.getJobName()))passed++; if(item.getCompletedAt()!=null)passed++; return percentage(passed,5); }
    private void check(boolean condition, String pass, String fail, List<String> passed, List<String> failed) { if(condition) passed.add(pass); else failed.add(fail); }
    private boolean notBlank(String value) { return value != null && !value.isBlank(); }
    private String safe(String value) { return notBlank(value) ? value : "not available"; }
    private String name(DeploymentStatus status) { return status == null ? "UNKNOWN" : status.name(); }
    private static double percentage(long value,long total){ return total==0?0.0:round(value*100.0/total); }
    private static double round(double value){ return Math.round(value*100.0)/100.0; }
}
