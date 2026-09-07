package ai.nexusone.controller;

import ai.nexusone.dto.AnalyzeRequest;
import ai.nexusone.dto.DeploymentRequest;
import ai.nexusone.dto.RiskResponse;
import ai.nexusone.entity.ApplicationEntity;
import ai.nexusone.entity.DeploymentEntity;
import ai.nexusone.service.ApplicationService;
import ai.nexusone.service.DeploymentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

 private final ApplicationService apps;
 private final DeploymentService deployments;

 public ApiController(
         ApplicationService apps,
         DeploymentService deployments) {

  this.apps = apps;
  this.deployments = deployments;
 }

 @GetMapping("/apps")
 public List<ApplicationEntity> applications() {
  return apps.findAll();
 }

 @PostMapping("/apps/analyze")
 @ResponseStatus(HttpStatus.CREATED)
 public ApplicationEntity analyze(
         @Valid @RequestBody AnalyzeRequest request) {

  return apps.analyze(request);
 }

 @GetMapping("/deployments")
 public List<DeploymentEntity> deployments() {
  return deployments.findAll();
 }

 @PostMapping("/deployments/simulate")
 public RiskResponse simulate(
         @Valid @RequestBody DeploymentRequest request) {

  return deployments.simulate(request);
 }

 @PostMapping("/deployments")
 @ResponseStatus(HttpStatus.CREATED)
 public DeploymentEntity deploy(
         @Valid @RequestBody DeploymentRequest request) {

  return deployments.create(request);
 }

 @PostMapping("/deployments/{id}/rollback")
 public DeploymentEntity rollback(
         @PathVariable String id) {

  return deployments.rollback(id);
 }

 @GetMapping("/dashboard")
 public Map<String, Object> dashboard() {

  List<ApplicationEntity> applications = apps.findAll();
  List<DeploymentEntity> deploymentList = deployments.findAll();

  return Map.of(
          "applications", applications.size(),
          "healthy",
          applications.stream()
                  .filter(app -> "HEALTHY".equals(app.getStatus()))
                  .count(),
          "deployments", deploymentList.size(),
          "aiRecommendations",
          List.of(
                  "Run pre-deployment simulation",
                  "Enable signed artifacts",
                  "Add production approval gate"
          )
  );
 }
}