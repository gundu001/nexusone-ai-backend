package ai.nexusone.service;

import ai.nexusone.dto.DeploymentRequest;
import ai.nexusone.dto.RiskResponse;
import ai.nexusone.entity.DeploymentEntity;
import ai.nexusone.exception.NotFoundException;
import ai.nexusone.repository.DeploymentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DeploymentService {

 private final DeploymentRepository repository;

 public DeploymentService(DeploymentRepository repository) {
  this.repository = repository;
 }

 /**
  * Get all deployments
  */
 public List<DeploymentEntity> findAll() {
  return repository.findAll();
 }

 /**
  * Simulate deployment risk
  */
 public RiskResponse simulate(DeploymentRequest request) {

  int score = Math.floorMod(
          Objects.hash(
                  request.applicationName(),
                  request.environment(),
                  request.provider(),
                  request.version()
          ),
          61
  ) + 15;

  if ("production".equalsIgnoreCase(request.environment())) {
   score = Math.min(100, score + 10);
  }

  String level;

  if (score < 40) {
   level = "LOW";
  } else if (score < 70) {
   level = "MEDIUM";
  } else {
   level = "HIGH";
  }

  List<String> recommendations;

  if (score < 40) {

   recommendations = List.of(
           "Proceed with standard approval gates"
   );

  } else {

   recommendations = List.of(
           "Use canary rollout",
           "Require manual approval",
           "Verify rollback readiness"
   );
  }

  return new RiskResponse(
          score,
          level,
          recommendations
  );
 }

 /**
  * Create deployment
  */
 public DeploymentEntity create(DeploymentRequest request) {

  RiskResponse riskResponse = simulate(request);

  String status =
          riskResponse.riskScore() >= 85
                  ? "PENDING_APPROVAL"
                  : "SUCCEEDED";

  DeploymentEntity deployment = new DeploymentEntity(
          request.applicationName(),
          request.environment(),
          request.provider(),
          request.version(),
          status,
          riskResponse.riskScore()
  );

  return repository.save(deployment);
 }

 /**
  * Rollback deployment
  */
 public DeploymentEntity rollback(String deploymentId) {

  DeploymentEntity deployment = repository
          .findById(deploymentId)
          .orElseThrow(() ->
                  new NotFoundException(
                          "Deployment not found: " + deploymentId
                  )
          );

  deployment.setStatus("ROLLED_BACK");

  return repository.save(deployment);
 }
}