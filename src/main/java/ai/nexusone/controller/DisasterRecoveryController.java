package ai.nexusone.controller;

import ai.nexusone.dto.request.BusinessImpactRequest;
import ai.nexusone.dto.request.RecoveryAssessmentRequest;
import ai.nexusone.dto.request.RecoverySimulationRequest;
import ai.nexusone.service.DisasterRecoveryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disaster-recovery")
@CrossOrigin(origins = "*")
public class DisasterRecoveryController {

 private final DisasterRecoveryService service;

 public DisasterRecoveryController(DisasterRecoveryService service) {
  this.service = service;
 }

 @PostMapping("/assess")
 public ResponseEntity<?> assess(@Valid @RequestBody RecoveryAssessmentRequest request) {
  return ResponseEntity.status(HttpStatus.CREATED)
          .body(service.assess(request));
 }

 @PostMapping("/simulate")
 public ResponseEntity<?> simulate(@Valid @RequestBody RecoverySimulationRequest request) {
  return ResponseEntity.status(HttpStatus.CREATED)
          .body(service.simulate(request));
 }

 @PostMapping("/impact")
 public ResponseEntity<?> impact(@Valid @RequestBody BusinessImpactRequest request) {
  return ResponseEntity.status(HttpStatus.CREATED)
          .body(service.analyzeImpact(request));
 }

 @GetMapping("/overview")
 public ResponseEntity<?> overview() {
  return ResponseEntity.ok(service.overview());
 }

 @GetMapping("/assessments")
 public Object assessments(Pageable pageable) {
  return service.assessments(pageable);
 }

 @GetMapping("/simulations")
 public Object simulations(Pageable pageable) {
  return service.simulations(pageable);
 }

 @GetMapping("/impacts")
 public Object impacts(Pageable pageable) {
  return service.impacts(pageable);
 }
}