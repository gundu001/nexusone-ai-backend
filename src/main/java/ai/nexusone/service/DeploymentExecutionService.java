package ai.nexusone.service;

import ai.nexusone.dto.DeploymentExecutionRequest;
import ai.nexusone.dto.DeploymentExecutionResponse;
import ai.nexusone.dto.JenkinsBuildResponse;
import ai.nexusone.dto.DeploymentMetricsResponse;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentResult;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeploymentExecutionService {

    private final DeploymentExecutionRepository repository;
    private final JenkinsService jenkinsService;

    public DeploymentExecutionService(DeploymentExecutionRepository repository,
                                      JenkinsService jenkinsService) {
        this.repository = repository;
        this.jenkinsService = jenkinsService;
    }

    @Transactional
    public DeploymentExecutionResponse executeDeployment(DeploymentExecutionRequest request) {
        DeploymentExecutionEntity entity = new DeploymentExecutionEntity();
        entity.setRepositoryName(request.getRepositoryName().trim());
        entity.setJobName(request.getJobName().trim());
        entity.setTriggeredBy(request.getTriggeredBy().trim());
        entity.setStatus(DeploymentStatus.PENDING);
        entity.setDeploymentResult(DeploymentResult.IN_PROGRESS);
        entity.setStartedAt(LocalDateTime.now());
        entity = repository.save(entity);

        try {
            JenkinsBuildResponse build = jenkinsService.triggerBuild(entity.getJobName());
            entity.setBuildNumber(build.buildNumber());
            entity.setStatus(DeploymentStatus.RUNNING);
            entity.setJenkinsMessage(build.message());
            entity = repository.save(entity);
            return toResponse(entity, "Deployment execution started");
        } catch (RuntimeException ex) {
            entity.setStatus(DeploymentStatus.FAILED);
            entity.setDeploymentResult(DeploymentResult.FAILED);
            entity.setCompletedAt(LocalDateTime.now());
            entity.setJenkinsMessage(ex.getMessage());
            repository.save(entity);
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    public List<DeploymentExecutionResponse> getHistory(String repositoryName) {
        List<DeploymentExecutionEntity> entities =
                repositoryName == null || repositoryName.isBlank()
                        ? repository.findAllByOrderByStartedAtDesc()
                        : repository.findByRepositoryNameIgnoreCaseOrderByStartedAtDesc(repositoryName.trim());
        return entities.stream().map(entity -> toResponse(entity, entity.getJenkinsMessage())).toList();
    }

    @Transactional(readOnly = true)
    public DeploymentExecutionResponse getExecution(Long id) {
        DeploymentExecutionEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Deployment execution not found for id: " + id));
        return toResponse(entity, entity.getJenkinsMessage());
    }

    @Transactional
    public DeploymentExecutionResponse updateStatus(Long id, DeploymentStatus status) {
        DeploymentExecutionEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Deployment execution not found for id: " + id));
        entity.setStatus(status);
        if (status == DeploymentStatus.SUCCESS) {
            entity.setDeploymentResult(DeploymentResult.DEPLOYED);
            entity.setCompletedAt(LocalDateTime.now());
        } else if (status == DeploymentStatus.FAILED) {
            entity.setDeploymentResult(DeploymentResult.FAILED);
            entity.setCompletedAt(LocalDateTime.now());
        }
        entity = repository.save(entity);
        return toResponse(entity, "Deployment status updated");
    }

    private DeploymentExecutionResponse toResponse(DeploymentExecutionEntity entity, String message) {
        return new DeploymentExecutionResponse(
                entity.getId(), entity.getRepositoryName(), entity.getJobName(), entity.getBuildNumber(),
                entity.getStatus(), entity.getDeploymentResult(), entity.getTriggeredBy(), entity.getStartedAt(),
                entity.getCompletedAt(), message
        );
    }
    @Transactional(readOnly = true)
    public DeploymentMetricsResponse getMetrics() {

        long totalDeployments =
                repository.count();

        long successfulDeployments =
                repository.countByStatus(
                        DeploymentStatus.SUCCESS);

        long failedDeployments =
                repository.countByStatus(
                        DeploymentStatus.FAILED);

        double successRate =
                totalDeployments == 0
                        ? 0
                        : (successfulDeployments * 100.0)
                        / totalDeployments;

        return new DeploymentMetricsResponse(
                totalDeployments,
                successfulDeployments,
                failedDeployments,
                successRate);
    }

    @Transactional(readOnly = true)
    public List<DeploymentExecutionResponse>
    getRecentExecutions() {

        return repository
                .findTop10ByOrderByStartedAtDesc()
                .stream()
                .map(entity ->
                        toResponse(
                                entity,
                                entity.getJenkinsMessage()))
                .toList();
    }

}
