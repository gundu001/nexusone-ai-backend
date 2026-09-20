package ai.nexusone.controller;
import ai.nexusone.dto.request.FinOpsAssessmentRequest;
import ai.nexusone.service.FinOpsService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/finops") @CrossOrigin(origins="*")
public class FinOpsController{
 private final FinOpsService service;
 public FinOpsController(FinOpsService service){this.service=service;}
 @PostMapping("/analyze") public ResponseEntity<?> analyze(@Valid @RequestBody FinOpsAssessmentRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.analyze(r));}
 @GetMapping("/overview") public ResponseEntity<?> overview(){return ResponseEntity.ok(service.overview());}
 @GetMapping("/history") public ResponseEntity<?> history(Pageable p){return ResponseEntity.ok(service.history(p));}
 @GetMapping("/recommendations") public ResponseEntity<?> recommendations(){return ResponseEntity.ok(service.recommendations());}
 @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable Long id){return ResponseEntity.ok(service.get(id));}
 @ExceptionHandler({IllegalArgumentException.class,NoSuchElementException.class}) public ResponseEntity<?> invalid(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));}
}
