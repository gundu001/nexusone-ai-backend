package ai.nexusone.controller;
import ai.nexusone.dto.request.AutonomousEnterpriseRequest;
import ai.nexusone.service.AutonomousEnterprisePlatformService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/autonomous-enterprise") @CrossOrigin(origins="*")
public class AutonomousEnterprisePlatformController{
 private final AutonomousEnterprisePlatformService service;
 public AutonomousEnterprisePlatformController(AutonomousEnterprisePlatformService service){this.service=service;}
 @PostMapping("/analyze") public ResponseEntity<?> analyze(@Valid @RequestBody AutonomousEnterpriseRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.analyze(r));}
 @GetMapping("/overview") public ResponseEntity<?> overview(){return ResponseEntity.ok(service.overview());}
 @GetMapping("/history") public ResponseEntity<?> history(Pageable p){return ResponseEntity.ok(service.history(p));}
 @GetMapping("/recommendations") public ResponseEntity<?> recommendations(){return ResponseEntity.ok(service.recommendations());}
 @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable Long id){return ResponseEntity.ok(service.get(id));}
 @ExceptionHandler({IllegalArgumentException.class,NoSuchElementException.class}) public ResponseEntity<?> invalid(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));}
}
