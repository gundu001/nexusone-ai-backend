package ai.nexusone.controller;

import ai.nexusone.dto.JenkinsBuildStatusResponse;
import ai.nexusone.dto.JenkinsJobResponse;
import ai.nexusone.dto.JenkinsTriggerRequest;
import ai.nexusone.dto.JenkinsTriggerResponse;
import ai.nexusone.service.JenkinsIntegrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jenkins")
public class JenkinsController {

 private final JenkinsIntegrationService service;

 public JenkinsController(
         JenkinsIntegrationService service) {

  this.service = service;
 }

 @PostMapping("/trigger")
 public ResponseEntity<JenkinsTriggerResponse> trigger(
         @Valid @RequestBody JenkinsTriggerRequest request) {

  return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(service.trigger(request));
 }

 @GetMapping("/build-status/{id}")
 public JenkinsBuildStatusResponse getBuildStatus(
         @PathVariable Long id) {

  System.out.println("=== BUILD STATUS CONTROLLER HIT ===");

  return service.getBuildStatus(id);
 }

 @GetMapping("/jobs")
 public List<JenkinsJobResponse> getJobs() {

  return service.getJobs();
 }
}