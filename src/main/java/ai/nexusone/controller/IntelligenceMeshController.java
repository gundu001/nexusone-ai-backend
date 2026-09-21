package ai.nexusone.controller;

import ai.nexusone.dto.request.IntelligenceMeshRequest;
import ai.nexusone.dto.response.IntelligenceMeshAnalyticsResponse;
import ai.nexusone.service.IntelligenceMeshService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/intelligence-mesh")
@CrossOrigin(origins = "*")
public class IntelligenceMeshController {

 private final IntelligenceMeshService service;

 public IntelligenceMeshController(IntelligenceMeshService service) {
  this.service = service;
 }

 @GetMapping("/overview")
 public ResponseEntity<?> overview() {
  return ResponseEntity.ok(service.overview());
 }

 @GetMapping("/history")
 public ResponseEntity<?> history(Pageable p) {
  return ResponseEntity.ok(service.history(p));
 }

 @GetMapping("/analytics")
 public ResponseEntity<IntelligenceMeshAnalyticsResponse> getAnalytics() {
  return ResponseEntity.ok(service.getAnalytics());
 }

 @GetMapping("/pending-approvals")
 public ResponseEntity<?> pendingApprovals() {
  return ResponseEntity.ok(service.pendingApprovals());
 }

 @GetMapping("/{id}")
 public ResponseEntity<?> get(@PathVariable Long id) {
  return ResponseEntity.ok(service.get(id));
 }

 @PostMapping("/coordinate")
 public ResponseEntity<?> coordinate(@Valid @RequestBody IntelligenceMeshRequest r) {
  return ResponseEntity.status(HttpStatus.CREATED).body(service.coordinate(r));
 }

 @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
 public ResponseEntity<?> invalid(RuntimeException e) {
  return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
 }
}