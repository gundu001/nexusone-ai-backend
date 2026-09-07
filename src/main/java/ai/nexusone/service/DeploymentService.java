package ai.nexusone.service;

import ai.nexusone.dto.DeploymentRecommendationResult;
import ai.nexusone.dto.DeploymentRequest;
import ai.nexusone.dto.DeploymentResponse;
import ai.nexusone.entity.DeploymentEntity;
import ai.nexusone.repository.DeploymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class DeploymentService {

    private static final Set<String> ALLOWED_ENVIRONMENTS = Set.of("DEV", "TEST", "QA", "UAT", "PROD");
    private static final Set<String> ALLOWED_STATUSES = Set.of("PENDING", "RUNNING", "SUCCESS", "FAILED", "CANCELLED", "BLOCKED");

    private final DeploymentRepository deploymentRepository;
    private final DeploymentRecommendationService recommendationService;

    public DeploymentService(DeploymentRepository deploymentRepository,
                             DeploymentRecommendationService recommendationService) {
        this.deploymentRepository = deploymentRepository;
        this.recommendationService = recommendationService;
    }

    @Transactional
    public DeploymentResponse createDeployment(DeploymentRequest request) throws IOException {
        validateRequest(request);

        String repositoryName = request.getRepositoryName().trim();
        String environment = request.getEnvironment().trim().toUpperCase(Locale.ROOT);
        DeploymentRecommendationResult recommendation = recommendationService.getRecommendation(repositoryName);

        DeploymentEntity deployment = new DeploymentEntity();
        deployment.setRepositoryName(repositoryName);
        deployment.setEnvironment(environment);
        deployment.setRiskScore(recommendation.getRiskScore());
        deployment.setRiskSeverity(recommendation.getSeverity());
        deployment.setRecommendation(recommendation.getRecommendation());
        deployment.setRequestedAt(LocalDateTime.now());

        if ("DEPLOYMENT_BLOCKED".equalsIgnoreCase(recommendation.getRecommendation())) {
            deployment.setStatus("BLOCKED");
            deployment.setCompletedAt(LocalDateTime.now());
            deployment.setStatusMessage("Deployment blocked because the repository has high deployment risk.");
        } else {
            deployment.setStatus("PENDING");
            deployment.setStatusMessage("Deployment request created successfully.");
        }

        return mapToResponse(deploymentRepository.save(deployment));
    }

    @Transactional(readOnly = true)
    public DeploymentResponse getDeployment(Long deploymentId) {
        return mapToResponse(findDeployment(deploymentId));
    }

    @Transactional(readOnly = true)
    public List<DeploymentResponse> getAllDeployments() {
        return deploymentRepository.findAllByOrderByRequestedAtDesc().stream().map(this::mapToResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<DeploymentResponse> getDeploymentsByRepository(String repositoryName) {
        if (repositoryName == null || repositoryName.isBlank()) {
            throw new IllegalArgumentException("Repository name is required.");
        }
        return deploymentRepository.findByRepositoryNameOrderByRequestedAtDesc(repositoryName.trim())
                .stream().map(this::mapToResponse).toList();
    }

    @Transactional
    public DeploymentResponse updateDeploymentStatus(Long deploymentId, String requestedStatus) {
        if (requestedStatus == null || requestedStatus.isBlank()) {
            throw new IllegalArgumentException("Deployment status is required.");
        }

        String status = requestedStatus.trim().toUpperCase(Locale.ROOT);
        if (!ALLOWED_STATUSES.contains(status)) {
            throw new IllegalArgumentException("Invalid deployment status. Allowed statuses are: " + ALLOWED_STATUSES);
        }

        DeploymentEntity deployment = findDeployment(deploymentId);
        if ("BLOCKED".equals(deployment.getStatus())) {
            throw new IllegalStateException("A blocked deployment cannot be started or completed.");
        }

        deployment.setStatus(status);
        switch (status) {
            case "PENDING" -> deployment.setStatusMessage("Deployment is waiting to start.");
            case "RUNNING" -> {
                if (deployment.getStartedAt() == null) deployment.setStartedAt(LocalDateTime.now());
                deployment.setStatusMessage("Deployment is currently running.");
            }
            case "SUCCESS" -> {
                if (deployment.getStartedAt() == null) deployment.setStartedAt(LocalDateTime.now());
                deployment.setCompletedAt(LocalDateTime.now());
                deployment.setStatusMessage("Deployment completed successfully.");
            }
            case "FAILED" -> {
                if (deployment.getStartedAt() == null) deployment.setStartedAt(LocalDateTime.now());
                deployment.setCompletedAt(LocalDateTime.now());
                deployment.setStatusMessage("Deployment failed.");
            }
            case "CANCELLED" -> {
                deployment.setCompletedAt(LocalDateTime.now());
                deployment.setStatusMessage("Deployment was cancelled.");
            }
            default -> deployment.setStatusMessage("Deployment status updated to " + status + ".");
        }

        return mapToResponse(deploymentRepository.save(deployment));
    }

    private void validateRequest(DeploymentRequest request) {
        if (request == null) throw new IllegalArgumentException("Deployment request is required.");
        if (request.getRepositoryName() == null || request.getRepositoryName().isBlank())
            throw new IllegalArgumentException("Repository name is required.");
        if (request.getEnvironment() == null || request.getEnvironment().isBlank())
            throw new IllegalArgumentException("Environment is required.");

        String environment = request.getEnvironment().trim().toUpperCase(Locale.ROOT);
        if (!ALLOWED_ENVIRONMENTS.contains(environment)) {
            throw new IllegalArgumentException("Invalid environment. Allowed environments are: " + ALLOWED_ENVIRONMENTS);
        }
    }

    private DeploymentEntity findDeployment(Long deploymentId) {
        if (deploymentId == null) throw new IllegalArgumentException("Deployment ID is required.");
        return deploymentRepository.findById(deploymentId)
                .orElseThrow(() -> new IllegalArgumentException("Deployment was not found for ID: " + deploymentId));
    }

    private DeploymentResponse mapToResponse(DeploymentEntity deployment) {
        DeploymentResponse response = new DeploymentResponse();
        response.setDeploymentId(deployment.getId());
        response.setRepositoryName(deployment.getRepositoryName());
        response.setEnvironment(deployment.getEnvironment());
        response.setStatus(deployment.getStatus());
        response.setRiskScore(deployment.getRiskScore());
        response.setRiskSeverity(deployment.getRiskSeverity());
        response.setRecommendation(deployment.getRecommendation());
        response.setMessage(deployment.getStatusMessage());
        response.setRequestedAt(deployment.getRequestedAt());
        response.setStartedAt(deployment.getStartedAt());
        response.setCompletedAt(deployment.getCompletedAt());
        return response;
    }
}
