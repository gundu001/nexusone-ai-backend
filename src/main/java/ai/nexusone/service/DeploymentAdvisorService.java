package ai.nexusone.service;

import ai.nexusone.dto.DeploymentAdvisorResponse;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeploymentAdvisorService {

    private final DeploymentExecutionRepository repository;

    public DeploymentAdvisorService(DeploymentExecutionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public DeploymentAdvisorResponse getInsight(Long executionId) {
        if (executionId == null) {
            throw new IllegalArgumentException("Execution ID is required");
        }

        DeploymentExecutionEntity execution = repository.findById(executionId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Deployment execution not found for id: " + executionId));

        Advice advice = evaluate(execution);

        return new DeploymentAdvisorResponse(
                execution.getId(),
                execution.getRepositoryName(),
                execution.getJobName(),
                execution.getBuildNumber(),
                execution.getStatus().name(),
                execution.getDeploymentResult().name(),
                advice.riskLevel(),
                advice.recommendation(),
                advice.summary(),
                List.copyOf(advice.observations()),
                List.copyOf(advice.actions()),
                LocalDateTime.now());
    }

    private Advice evaluate(DeploymentExecutionEntity execution) {
        DeploymentStatus status = execution.getStatus();
        List<String> observations = new ArrayList<>();
        List<String> actions = new ArrayList<>();

        observations.add("Execution status is " + status.name() + ".");

        if (execution.getBuildNumber() == null) {
            observations.add("Jenkins build number is not available yet.");
        } else {
            observations.add("Jenkins build number is #" + execution.getBuildNumber() + ".");
        }

        if (execution.getJenkinsMessage() != null
                && !execution.getJenkinsMessage().isBlank()) {
            observations.add("Jenkins message: " + execution.getJenkinsMessage());
        }

        if (execution.getStartedAt() != null && execution.getCompletedAt() != null) {
            long seconds = Math.max(0, Duration.between(
                    execution.getStartedAt(), execution.getCompletedAt()).getSeconds());
            observations.add("Recorded execution duration is " + seconds + " seconds.");
        }

        if (status == DeploymentStatus.SUCCESS) {
            actions.add("Proceed with post-deployment smoke validation.");
            actions.add("Monitor application health and logs before promotion.");
            actions.add("Promote to the next environment after validation passes.");
            return new Advice(
                    "LOW",
                    "SAFE_TO_PROMOTE",
                    "The deployment completed successfully and is eligible for controlled promotion.",
                    observations,
                    actions);
        }

        if (status == DeploymentStatus.FAILED) {
            actions.add("Review the Jenkins console output and failed stage.");
            actions.add("Compare the failure with the previous successful build.");
            actions.add("Resolve the failure before retrying or promoting.");
            return new Advice(
                    "HIGH",
                    "DO_NOT_PROMOTE",
                    "The deployment failed and should not be promoted.",
                    observations,
                    actions);
        }

        if (status == DeploymentStatus.ABORTED) {
            actions.add("Confirm why the execution was aborted.");
            actions.add("Validate the target environment before retrying.");
            return new Advice(
                    "HIGH",
                    "REVIEW_REQUIRED",
                    "The deployment was aborted and requires review before another attempt.",
                    observations,
                    actions);
        }

        if (status == DeploymentStatus.UNKNOWN) {
            actions.add("Verify Jenkins connectivity and execution status.");
            actions.add("Do not promote until the final build result is known.");
            return new Advice(
                    "HIGH",
                    "HOLD",
                    "The execution state is unknown, so promotion should be held.",
                    observations,
                    actions);
        }

        actions.add("Continue monitoring until Jenkins returns a terminal result.");
        actions.add("Do not promote while the execution is pending, queued, or running.");
        return new Advice(
                "MEDIUM",
                "MONITOR",
                "The deployment is still in progress and needs continued monitoring.",
                observations,
                actions);
    }

    private record Advice(
            String riskLevel,
            String recommendation,
            String summary,
            List<String> observations,
            List<String> actions) {
    }
}
