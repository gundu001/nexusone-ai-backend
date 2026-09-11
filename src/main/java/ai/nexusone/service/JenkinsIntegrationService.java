package ai.nexusone.service;

import ai.nexusone.dto.DeploymentHistoryResponse;
import ai.nexusone.dto.JenkinsApiBuildResponse;
import ai.nexusone.dto.JenkinsBuildStatusResponse;
import ai.nexusone.dto.JenkinsJobResponse;
import ai.nexusone.dto.JenkinsQueueResponse;
import ai.nexusone.dto.JenkinsTriggerRequest;
import ai.nexusone.dto.JenkinsTriggerResponse;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentResult;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JenkinsIntegrationService {

    private final DeploymentExecutionRepository repository;
    private final JenkinsClient jenkinsClient;

    public JenkinsIntegrationService(
            DeploymentExecutionRepository repository,
            JenkinsClient jenkinsClient) {

        this.repository = repository;
        this.jenkinsClient = jenkinsClient;
    }

    /**
     * Creates the deployment execution record and triggers
     * the real Jenkins job.
     */
    @Transactional
    public JenkinsTriggerResponse trigger(
            JenkinsTriggerRequest request) {

        DeploymentExecutionEntity execution =
                new DeploymentExecutionEntity();

        execution.setRepositoryName(
                request.repositoryName().trim());

        execution.setJobName(
                request.jobName().trim());

        execution.setTriggeredBy(
                request.triggeredBy().trim());

        execution.setStatus(DeploymentStatus.PENDING);
        execution.setDeploymentResult(
                DeploymentResult.IN_PROGRESS);

        execution.setStartedAt(LocalDateTime.now());

        execution = repository.save(execution);

        try {
            JenkinsTriggerResponse response =
                    jenkinsClient.trigger(
                            execution.getId(),
                            execution.getJobName(),
                            request.parameters());

            execution.setQueueId(response.queueId());
            execution.setStatus(DeploymentStatus.QUEUED);
            execution.setJenkinsUrl(response.jenkinsUrl());
            execution.setJenkinsMessage(response.message());

            repository.save(execution);

            return response;

        } catch (RuntimeException exception) {
            execution.setStatus(DeploymentStatus.FAILED);
            execution.setDeploymentResult(
                    DeploymentResult.FAILED);

            execution.setCompletedAt(LocalDateTime.now());
            execution.setJenkinsMessage(exception.getMessage());

            repository.save(execution);

            throw exception;
        }
    }

    /**
     * Resolves a Jenkins queue item to a build number and
     * returns the latest build status.
     */
    @Transactional
    public JenkinsBuildStatusResponse getBuildStatus(
            Long executionId) {

        DeploymentExecutionEntity execution =
                findExecution(executionId);

        if (isTerminalStatus(execution.getStatus())) {
            return mapToBuildStatus(
                    execution,
                    false,
                    null);
        }

        if (execution.getBuildNumber() == null) {
            resolveQueueItem(execution);

            if (execution.getBuildNumber() == null) {
                repository.save(execution);

                return mapToBuildStatus(
                        execution,
                        null,
                        null);
            }
        }
        System.out.println(
                "ExecutionId = "
                        + execution.getId());

        System.out.println(
                "Stored JobName = ["
                        + execution.getJobName()
                        + "]");

        System.out.println(
                "Stored BuildNumber = "
                        + execution.getBuildNumber());
        JenkinsApiBuildResponse jenkinsBuild =
                jenkinsClient.build(
                        execution.getJobName(),
                        execution.getBuildNumber());

        execution.setJenkinsUrl(jenkinsBuild.url());

        if (jenkinsBuild.building()) {
            execution.setStatus(
                    DeploymentStatus.RUNNING);

            execution.setDeploymentResult(
                    DeploymentResult.IN_PROGRESS);

            execution.setJenkinsMessage(
                    "Jenkins build is running");

        } else {
            applyJenkinsResult(
                    execution,
                    jenkinsBuild.result());

            execution.setCompletedAt(
                    LocalDateTime.now());
        }

        repository.save(execution);

        return mapToBuildStatus(
                execution,
                jenkinsBuild.building(),
                jenkinsBuild.duration());
    }

    /**
     * Returns the jobs visible to the configured Jenkins user.
     */
    @Transactional(readOnly = true)
    public List<JenkinsJobResponse> getJobs() {
        return jenkinsClient.jobs();
    }

    /**
     * Returns all execution history, or filters it using the
     * provided repository name.
     */
    @Transactional(readOnly = true)
    public List<DeploymentHistoryResponse> getHistory(
            String repositoryName) {

        List<DeploymentExecutionEntity> executions;

        if (repositoryName == null ||
                repositoryName.isBlank()) {

            executions =
                    repository.findAllByOrderByStartedAtDesc();

        } else {
            executions =
                    repository
                            .findByRepositoryNameIgnoreCaseOrderByStartedAtDesc(
                                    repositoryName.trim());
        }

        return executions.stream()
                .map(this::mapToHistory)
                .toList();
    }

    private void resolveQueueItem(
            DeploymentExecutionEntity execution) {

        System.out.println("=== resolveQueueItem START ===");


        System.out.println(
                "ExecutionId = "
                        + execution.getId());

        System.out.println(
                "QueueId = "
                        + execution.getQueueId());

        if (execution.getQueueId() == null) {
            execution.setStatus(
                    DeploymentStatus.UNKNOWN);

            execution.setDeploymentResult(
                    DeploymentResult.UNKNOWN);

            execution.setJenkinsMessage(
                    "Jenkins queue ID is not available");

            return;
        }

        JenkinsQueueResponse queueResponse =
                jenkinsClient.queue(
                        execution.getQueueId());
        System.out.println(
                "Queue Response Received");

        System.out.println(
                "Executable = "
                        + queueResponse.executable());

        if (queueResponse.cancelled()) {
            execution.setStatus(
                    DeploymentStatus.ABORTED);

            execution.setDeploymentResult(
                    DeploymentResult.ABORTED);

            execution.setCompletedAt(
                    LocalDateTime.now());

            execution.setJenkinsMessage(
                    "Jenkins queue item was cancelled");

            return;
        }

        if (queueResponse.executable() == null) {
            execution.setStatus(
                    DeploymentStatus.QUEUED);

            String reason = queueResponse.why();

            execution.setJenkinsMessage(
                    reason == null || reason.isBlank()
                            ? "Waiting in Jenkins queue"
                            : reason);

            return;
        }

        execution.setBuildNumber(
                queueResponse.executable().number());

        execution.setJenkinsUrl(
                queueResponse.executable().url());

        execution.setStatus(
                DeploymentStatus.RUNNING);

        execution.setJenkinsMessage(
                "Jenkins assigned build number "
                        + execution.getBuildNumber());
    }

    private void applyJenkinsResult(
            DeploymentExecutionEntity execution,
            String result) {

        if (result == null || result.isBlank()) {
            execution.setStatus(
                    DeploymentStatus.UNKNOWN);

            execution.setDeploymentResult(
                    DeploymentResult.UNKNOWN);

            execution.setJenkinsMessage(
                    "Jenkins returned an empty build result");

            return;
        }

        switch (result.toUpperCase()) {
            case "SUCCESS" -> {
                execution.setStatus(
                        DeploymentStatus.SUCCESS);

                execution.setDeploymentResult(
                        DeploymentResult.DEPLOYED);
            }

            case "ABORTED" -> {
                execution.setStatus(
                        DeploymentStatus.ABORTED);

                execution.setDeploymentResult(
                        DeploymentResult.ABORTED);
            }

            case "FAILURE", "FAILED", "UNSTABLE" -> {
                execution.setStatus(
                        DeploymentStatus.FAILED);

                execution.setDeploymentResult(
                        DeploymentResult.FAILED);
            }

            default -> {
                execution.setStatus(
                        DeploymentStatus.UNKNOWN);

                execution.setDeploymentResult(
                        DeploymentResult.UNKNOWN);
            }
        }

        execution.setJenkinsMessage(
                "Jenkins build result: " + result);
    }

    private boolean isTerminalStatus(
            DeploymentStatus status) {

        return status == DeploymentStatus.SUCCESS
                || status == DeploymentStatus.FAILED
                || status == DeploymentStatus.ABORTED;
    }

    private DeploymentExecutionEntity findExecution(
            Long executionId) {

        if (executionId == null) {
            throw new IllegalArgumentException(
                    "Execution ID is required");
        }

        return repository.findById(executionId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Deployment execution not found: "
                                        + executionId));
    }

    private JenkinsBuildStatusResponse mapToBuildStatus(
            DeploymentExecutionEntity execution,
            Boolean building,
            Long durationMillis) {

        return new JenkinsBuildStatusResponse(
                execution.getId(),
                execution.getRepositoryName(),
                execution.getJobName(),
                execution.getQueueId(),
                execution.getBuildNumber(),
                execution.getStatus().name(),
                execution.getDeploymentResult().name(),
                building,
                durationMillis,
                execution.getJenkinsUrl(),
                execution.getStartedAt(),
                execution.getCompletedAt(),
                execution.getJenkinsMessage());
    }

    private DeploymentHistoryResponse mapToHistory(
            DeploymentExecutionEntity execution) {

        return new DeploymentHistoryResponse(
                execution.getId(),
                execution.getRepositoryName(),
                execution.getJobName(),
                execution.getBuildNumber(),
                execution.getStatus().name(),
                execution.getDeploymentResult().name(),
                execution.getTriggeredBy(),
                execution.getStartedAt(),
                execution.getCompletedAt(),
                execution.getJenkinsUrl());
    }
}