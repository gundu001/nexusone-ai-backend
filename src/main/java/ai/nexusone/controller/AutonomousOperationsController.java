package ai.nexusone.controller;

import ai.nexusone.dto.request.*;
import ai.nexusone.service.AutonomousOperationsService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/autonomous-operations")
@CrossOrigin(origins="*")
public class AutonomousOperationsController {
    private final AutonomousOperationsService service;
    public AutonomousOperationsController(AutonomousOperationsService service){this.service=service;}
    @PostMapping("/analyze") public ResponseEntity<?> analyze(@Valid @RequestBody AutonomousOperationRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.analyze(r));}
    @PostMapping("/{id}/approve") public ResponseEntity<?> approve(@PathVariable Long id,@Valid @RequestBody OperationApprovalRequest r){return ResponseEntity.ok(service.approve(id,r));}
    @PostMapping("/{id}/execute") public ResponseEntity<?> execute(@PathVariable Long id,@Valid @RequestBody OperationExecutionRequest r){return ResponseEntity.ok(service.execute(id,r));}
    @GetMapping("/overview") public ResponseEntity<?> overview(){return ResponseEntity.ok(service.overview());}
    @GetMapping("/history") public ResponseEntity<?> history(Pageable p){return ResponseEntity.ok(service.history(p));}
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable Long id){return ResponseEntity.ok(service.get(id));}
    @ExceptionHandler({IllegalArgumentException.class,NoSuchElementException.class}) public ResponseEntity<?> invalid(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));}
}
