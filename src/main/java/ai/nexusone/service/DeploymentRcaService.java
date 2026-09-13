package ai.nexusone.service;

import ai.nexusone.dto.DeploymentRcaResponse;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class DeploymentRcaService {

    private final DeploymentExecutionRepository repository;

    public DeploymentRcaService(
            DeploymentExecutionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public DeploymentRcaResponse analyze(Long executionId) {
        if (executionId == null) {
            throw new IllegalArgumentException("Execution ID is required");
        }

        DeploymentExecutionEntity execution = repository.findById(executionId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Deployment execution not found for id: " + executionId));

        RcaFinding finding = determineFinding(execution);

        return new DeploymentRcaResponse(
                execution.getId(),
                execution.getRepositoryName(),
                execution.getJobName(),
                execution.getBuildNumber(),
                execution.getStatus().name(),
                execution.getDeploymentResult().name(),
                finding.rootCauseCode(),
                finding.rootCause(),
                finding.confidenceScore(),
                finding.severity(),
                finding.summary(),
                List.copyOf(finding.evidence()),
                List.copyOf(finding.recommendedFixes()),
                LocalDateTime.now());
    }

    private RcaFinding determineFinding(
            DeploymentExecutionEntity execution) {

        List<String> evidence = buildEvidence(execution);
        String message = normalize(execution.getJenkinsMessage());
        DeploymentStatus status = execution.getStatus();

        if (status == DeploymentStatus.SUCCESS) {
            return finding(
                    "NO_FAILURE_DETECTED",
                    "No deployment failure detected",
                    100,
                    "LOW",
                    "The execution completed successfully, so root cause analysis is not required.",
                    evidence,
                    List.of(
                            "No corrective action is required.",
                            "Continue with smoke testing and post-deployment monitoring."));
        }

        if (status == DeploymentStatus.PENDING
                || status == DeploymentStatus.QUEUED
                || status == DeploymentStatus.RUNNING) {
            return finding(
                    "EXECUTION_NOT_TERMINAL",
                    "Execution is still in progress",
                    100,
                    "INFO",
                    "Root cause analysis cannot be finalized until Jenkins returns a terminal result.",
                    evidence,
                    List.of(
                            "Continue monitoring the execution.",
                            "Run RCA again after the execution succeeds, fails, or is aborted."));
        }

        if (containsAny(message,
                "authentication", "unauthorized", "forbidden",
                "401", "403", "invalid token", "bad credentials")) {
            return finding(
                    "JENKINS_AUTHENTICATION_FAILURE",
                    "Jenkins authentication or authorization failure",
                    95,
                    "HIGH",
                    "The Jenkins request appears to have failed because credentials or permissions were rejected.",
                    evidence,
                    List.of(
                            "Verify the configured Jenkins username and API token.",
                            "Confirm that the Jenkins user can read and build the target job.",
                            "Rotate the API token if it is expired or compromised."));
        }

        if (containsAny(message,
                "connection refused", "cannot connect", "timed out",
                "timeout", "unknown host", "unreachable")) {
            return finding(
                    "JENKINS_CONNECTIVITY_FAILURE",
                    "Jenkins connectivity failure",
                    93,
                    "HIGH",
                    "The application could not reliably connect to the configured Jenkins server.",
                    evidence,
                    List.of(
                            "Verify the Jenkins base URL and port.",
                            "Confirm Jenkins is running and reachable from the backend host.",
                            "Check firewall, proxy, DNS, and network routing settings."));
        }

        if (containsAny(message,
                "not found", "404", "no such job", "job does not exist")) {
            return finding(
                    "JENKINS_RESOURCE_NOT_FOUND",
                    "Jenkins job, queue item, or build was not found",
                    92,
                    "HIGH",
                    "Jenkins returned a not-found response for a requested execution resource.",
                    evidence,
                    List.of(
                            "Verify the stored Jenkins job name.",
                            "Check whether the queue item expired after Jenkins started the build.",
                            "Use the assigned build number or Jenkins build URL when available."));
        }

        if (containsAny(message,
                "compilation", "compile", "cannot find symbol",
                "maven", "gradle", "build failure")) {
            return finding(
                    "BUILD_COMPILATION_FAILURE",
                    "Application build or compilation failure",
                    90,
                    "HIGH",
                    "The available Jenkins message indicates a build-tool or compilation problem.",
                    evidence,
                    List.of(
                            "Review the Jenkins console output around the failed build stage.",
                            "Run the same Maven or Gradle build command locally.",
                            "Fix compilation errors, dependency conflicts, or plugin failures before retrying."));
        }

        if (containsAny(message,
                "test failure", "tests failed", "failing test",
                "assertionerror", "junit", "surefire")) {
            return finding(
                    "AUTOMATED_TEST_FAILURE",
                    "Automated tests failed",
                    90,
                    "HIGH",
                    "The available Jenkins message indicates that an automated test stage failed.",
                    evidence,
                    List.of(
                            "Review the failed test names and their assertions.",
                            "Reproduce the failed tests locally with the same configuration.",
                            "Correct the regression or test-data issue before retrying."));
        }

        if (containsAny(message,
                "deploy", "deployment", "kubectl", "helm",
                "container", "docker", "image pull")) {
            return finding(
                    "DEPLOYMENT_STAGE_FAILURE",
                    "Deployment stage or target-environment failure",
                    82,
                    "HIGH",
                    "The available message suggests a failure while publishing or deploying the application.",
                    evidence,
                    List.of(
                            "Review the deployment-stage console output.",
                            "Validate target-environment credentials and configuration.",
                            "Confirm the artifact or container image exists and is accessible."));
        }

        if (status == DeploymentStatus.ABORTED) {
            return finding(
                    "EXECUTION_ABORTED",
                    "Jenkins execution was aborted",
                    85,
                    "MEDIUM",
                    "The execution ended because it was cancelled or aborted before completion.",
                    evidence,
                    List.of(
                            "Confirm whether the abort was manual or automatic.",
                            "Review timeout, concurrency, and cancellation settings.",
                            "Verify the environment state before retrying."));
        }

        if (status == DeploymentStatus.UNKNOWN) {
            return finding(
                    "UNKNOWN_EXECUTION_STATE",
                    "Execution state could not be determined",
                    70,
                    "HIGH",
                    "The stored execution state is unknown and the available evidence is insufficient for a specific root cause.",
                    evidence,
                    List.of(
                            "Verify Jenkins connectivity and the stored job, queue, and build identifiers.",
                            "Retrieve Jenkins console output and the final build result.",
                            "Do not promote until the execution state is confirmed."));
        }

        return finding(
                "GENERAL_PIPELINE_FAILURE",
                "Jenkins pipeline execution failure",
                message.isBlank() ? 60 : 75,
                "HIGH",
                "The execution failed, but the stored Jenkins message does not contain enough detail for a more specific classification.",
                evidence,
                List.of(
                        "Review the Jenkins console output and identify the first failing stage.",
                        "Capture the exception, failed command, and relevant log lines.",
                        "Resolve the underlying issue and rerun the pipeline."));
    }

    private List<String> buildEvidence(
            DeploymentExecutionEntity execution) {

        List<String> evidence = new ArrayList<>();
        evidence.add("Execution status is " + execution.getStatus().name() + ".");
        evidence.add("Deployment result is "
                + execution.getDeploymentResult().name() + ".");

        if (execution.getBuildNumber() == null) {
            evidence.add("Jenkins build number is not available.");
        } else {
            evidence.add("Jenkins build number is #"
                    + execution.getBuildNumber() + ".");
        }

        if (execution.getQueueId() != null) {
            evidence.add("Jenkins queue ID is "
                    + execution.getQueueId() + ".");
        }

        if (execution.getJenkinsMessage() != null
                && !execution.getJenkinsMessage().isBlank()) {
            evidence.add("Jenkins message: "
                    + execution.getJenkinsMessage().trim());
        }

        if (execution.getStartedAt() != null
                && execution.getCompletedAt() != null) {
            long seconds = Math.max(
                    0,
                    Duration.between(
                            execution.getStartedAt(),
                            execution.getCompletedAt()).getSeconds());
            evidence.add("Recorded execution duration is "
                    + seconds + " seconds.");
        }

        return evidence;
    }

    private String normalize(String value) {
        return value == null
                ? ""
                : value.toLowerCase(Locale.ROOT);
    }

    private boolean containsAny(
            String value,
            String... patterns) {

        for (String pattern : patterns) {
            if (value.contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    private RcaFinding finding(
            String rootCauseCode,
            String rootCause,
            int confidenceScore,
            String severity,
            String summary,
            List<String> evidence,
            List<String> recommendedFixes) {

        return new RcaFinding(
                rootCauseCode,
                rootCause,
                confidenceScore,
                severity,
                summary,
                evidence,
                recommendedFixes);
    }

    private record RcaFinding(
            String rootCauseCode,
            String rootCause,
            int confidenceScore,
            String severity,
            String summary,
            List<String> evidence,
            List<String> recommendedFixes) {
    }
}
