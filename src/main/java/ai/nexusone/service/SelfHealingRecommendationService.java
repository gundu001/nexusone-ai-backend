package ai.nexusone.service;

import ai.nexusone.dto.SelfHealingAction;
import ai.nexusone.dto.SelfHealingRecommendationResponse;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SelfHealingRecommendationService {
    private final DeploymentExecutionRepository repository;

    public SelfHealingRecommendationService(DeploymentExecutionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public SelfHealingRecommendationResponse recommend(Long executionId) {
        if (executionId == null) {
            throw new IllegalArgumentException("Execution ID is required");
        }

        DeploymentExecutionEntity execution = repository.findById(executionId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Deployment execution not found for id: " + executionId));

        Recommendation recommendation = determineRecommendation(execution);
        return new SelfHealingRecommendationResponse(
                execution.getId(), execution.getRepositoryName(), execution.getJobName(),
                execution.getBuildNumber(), nameOf(execution.getStatus()),
                execution.getDeploymentResult() == null ? "UNKNOWN" : execution.getDeploymentResult().name(),
                recommendation.code(), recommendation.diagnosis(), recommendation.confidence(),
                recommendation.risk(), recommendation.healingRecommended(),
                recommendation.approvalRequired(), recommendation.summary(),
                List.copyOf(buildEvidence(execution)), List.copyOf(recommendation.actions()),
                LocalDateTime.now());
    }

    private Recommendation determineRecommendation(DeploymentExecutionEntity execution) {
        DeploymentStatus status = execution.getStatus();
        String message = normalize(execution.getJenkinsMessage());

        if (status == DeploymentStatus.SUCCESS) {
            return recommendation("NO_HEALING_REQUIRED", "Deployment completed successfully", 100,
                    "LOW", false, false,
                    "No self-healing action is required. Continue post-deployment monitoring.",
                    action("MONITOR", "Continue monitoring",
                            "Observe smoke tests and runtime health without changing the deployment.",
                            "No command required", 1, false, false));
        }

        if (status == DeploymentStatus.PENDING || status == DeploymentStatus.QUEUED
                || status == DeploymentStatus.RUNNING) {
            return recommendation("EXECUTION_IN_PROGRESS", "Execution is not in a terminal state", 100,
                    "LOW", false, false,
                    "Healing is deferred until Jenkins returns a terminal result.",
                    action("WAIT_FOR_RESULT", "Wait for terminal result",
                            "Continue monitoring and request recommendations again after completion.",
                            "GET /api/executions/" + execution.getId(), 1, false, false));
        }

        if (containsAny(message, "authentication", "unauthorized", "forbidden", "401", "403",
                "invalid token", "bad credentials")) {
            return recommendation("CREDENTIAL_REPAIR", "Jenkins credentials or permissions were rejected", 95,
                    "HIGH", true, true,
                    "Validate credentials and permissions before retrying the pipeline.",
                    action("VERIFY_CREDENTIALS", "Verify Jenkins credentials",
                            "Validate the configured username and API token. Rotate only through the approved secret process.",
                            "Check JENKINS_USERNAME and JENKINS_API_TOKEN", 1, false, true),
                    action("RETRY_PIPELINE", "Retry after credential validation",
                            "Trigger a new execution only after the authorization check succeeds.",
                            "POST /api/executions/execute", 2, false, true));
        }

        if (containsAny(message, "connection refused", "cannot connect", "timed out", "timeout",
                "unknown host", "unreachable")) {
            return recommendation("CONNECTIVITY_RECOVERY", "Jenkins connectivity failure", 93,
                    "MEDIUM", true, true,
                    "Restore Jenkins reachability, verify health, and then retry the execution.",
                    action("CHECK_JENKINS_HEALTH", "Check Jenkins health",
                            "Verify the configured base URL, port, DNS, proxy, firewall, and Jenkins service state.",
                            "Open the configured Jenkins base URL", 1, false, false),
                    action("RETRY_PIPELINE", "Retry after connectivity recovery",
                            "Create a new execution after Jenkins responds successfully.",
                            "POST /api/executions/execute", 2, false, true));
        }

        if (containsAny(message, "not found", "404", "no such job", "job does not exist")) {
            return recommendation("RESOURCE_MAPPING_REPAIR", "Jenkins resource was not found", 92,
                    "MEDIUM", true, true,
                    "Correct the job or build mapping and retry with a verified Jenkins resource.",
                    action("VERIFY_JOB", "Verify Jenkins job mapping",
                            "Confirm the stored job name and verify that the job exists in Jenkins.",
                            "Review jobName: " + safe(execution.getJobName()), 1, false, false),
                    action("USE_BUILD_URL", "Use build details when available",
                            "Prefer the assigned build number or Jenkins build URL after the queue item expires.",
                            "Review buildNumber and jenkinsUrl", 2, false, false));
        }

        if (containsAny(message, "compilation", "compile", "cannot find symbol", "maven", "gradle",
                "build failure")) {
            return recommendation("BUILD_REPAIR", "Compilation or build-tool failure", 90,
                    "HIGH", true, true,
                    "Repair the build in source control, validate locally, and then retry.",
                    action("REPRODUCE_BUILD", "Reproduce the failed build",
                            "Run the same Maven or Gradle command with the same profile and environment.",
                            "mvn clean verify or the Jenkins-equivalent command", 1, false, false),
                    action("FIX_BUILD", "Apply a reviewed source fix",
                            "Correct compilation errors, dependency conflicts, or plugin configuration.",
                            "Commit a minimal reviewed patch", 2, false, true),
                    action("RETRY_PIPELINE", "Rerun the pipeline",
                            "Retry only after local build validation succeeds.",
                            "POST /api/executions/execute", 3, false, true));
        }

        if (containsAny(message, "test failure", "tests failed", "failing test", "assertionerror",
                "junit", "surefire")) {
            return recommendation("TEST_REPAIR", "Automated test failure", 90,
                    "HIGH", true, true,
                    "Identify the first failed test, repair the regression or test data, and rerun validation.",
                    action("REPRODUCE_TEST", "Reproduce failed tests",
                            "Run the failed test suite using the same configuration and test data.",
                            "mvn test or the Jenkins-equivalent test command", 1, false, false),
                    action("REVIEW_TEST_FIX", "Review and approve the fix",
                            "Do not bypass, disable, or weaken the failing assertion as a healing action.",
                            "Create a reviewed code change", 2, false, true));
        }

        if (containsAny(message, "kubectl", "helm", "container", "docker", "image pull",
                "deployment", "deploy")) {
            return recommendation("DEPLOYMENT_RECOVERY", "Target environment or deployment-stage failure", 84,
                    "CRITICAL", true, true,
                    "Validate the artifact and environment before selecting retry or rollback.",
                    action("VALIDATE_ARTIFACT", "Validate deployable artifact",
                            "Confirm that the package or image exists, is accessible, and matches the requested version.",
                            "Check artifact registry and image tag", 1, false, false),
                    action("VALIDATE_ENVIRONMENT", "Validate target environment",
                            "Check target credentials, quota, configuration, health, and deployment prerequisites.",
                            "Review Kubernetes or target platform events", 2, false, false),
                    action("ROLLBACK", "Consider rollback",
                            "Use the last known-good version when impact is active and rollback conditions are satisfied.",
                            "Run the approved rollback workflow", 3, false, true));
        }

        if (status == DeploymentStatus.ABORTED) {
            return recommendation("ABORT_RECOVERY", "Execution was aborted", 85,
                    "MEDIUM", true, true,
                    "Identify whether cancellation was manual, timeout-driven, or policy-driven before retrying.",
                    action("CHECK_ABORT_REASON", "Check abort reason",
                            "Review Jenkins audit history, timeout settings, and concurrent execution rules.",
                            "Review Jenkins build history", 1, false, false),
                    action("RETRY_PIPELINE", "Retry when the environment is safe",
                            "Create a new execution only after confirming the previous run cannot continue.",
                            "POST /api/executions/execute", 2, false, true));
        }

        return recommendation("MANUAL_DIAGNOSIS_REQUIRED", "Failure needs additional evidence", 65,
                "HIGH", false, true,
                "The stored message is insufficient for a safe automated recommendation. Collect console logs first.",
                action("COLLECT_LOGS", "Collect Jenkins console evidence",
                        "Capture the first failed stage, exception, failed command, and relevant surrounding log lines.",
                        "Open jenkinsUrl or Jenkins console output", 1, false, false),
                action("RUN_RCA", "Run RCA again with complete evidence",
                        "Refresh execution details after storing a more specific Jenkins message.",
                        "GET /api/deployment-rca/" + execution.getId(), 2, false, false));
    }

    private List<String> buildEvidence(DeploymentExecutionEntity execution) {
        List<String> evidence = new ArrayList<>();
        evidence.add("Execution status: " + nameOf(execution.getStatus()));
        evidence.add("Deployment result: " + (execution.getDeploymentResult() == null
                ? "UNKNOWN" : execution.getDeploymentResult().name()));
        if (execution.getBuildNumber() != null) evidence.add("Jenkins build number: " + execution.getBuildNumber());
        if (execution.getQueueId() != null) evidence.add("Jenkins queue ID: " + execution.getQueueId());
        if (execution.getJenkinsMessage() != null && !execution.getJenkinsMessage().isBlank()) {
            evidence.add("Jenkins message: " + execution.getJenkinsMessage().trim());
        }
        return evidence;
    }

    private SelfHealingRecommendationResponse buildResponse(
            DeploymentExecutionEntity execution) {

        Recommendation recommendation =
                determineRecommendation(execution);

        return new SelfHealingRecommendationResponse(
                execution.getId(),
                execution.getRepositoryName(),
                execution.getJobName(),
                execution.getBuildNumber(),
                nameOf(execution.getStatus()),
                execution.getDeploymentResult() == null
                        ? "UNKNOWN"
                        : execution.getDeploymentResult().name(),
                recommendation.code(),
                recommendation.diagnosis(),
                recommendation.confidence(),
                recommendation.risk(),
                recommendation.healingRecommended(),
                recommendation.approvalRequired(),
                recommendation.summary(),
                List.copyOf(buildEvidence(execution)),
                List.copyOf(recommendation.actions()),
                LocalDateTime.now()
        );
    }


    private Recommendation recommendation(String code, String diagnosis, int confidence, String risk,
            boolean healingRecommended, boolean approvalRequired, String summary,
            SelfHealingAction... actions) {
        return new Recommendation(code, diagnosis, confidence, risk, healingRecommended,
                approvalRequired, summary, List.of(actions));
    }

    private SelfHealingAction action(String code, String title, String description, String commandHint,
            int priority, boolean automated, boolean approvalRequired) {
        return new SelfHealingAction(code, title, description, commandHint, priority,
                automated, approvalRequired);
    }

    private String normalize(String value) {
        return value == null ? "" : value.toLowerCase(Locale.ROOT);
    }

    private boolean containsAny(String source, String... tokens) {
        for (String token : tokens) if (source.contains(token)) return true;
        return false;
    }

    private String nameOf(DeploymentStatus status) { return status == null ? "UNKNOWN" : status.name(); }
    private String safe(String value) { return value == null || value.isBlank() ? "not available" : value; }

    private record Recommendation(String code, String diagnosis, int confidence, String risk,
                                  boolean healingRecommended, boolean approvalRequired,
                                  String summary, List<SelfHealingAction> actions) {}
}
