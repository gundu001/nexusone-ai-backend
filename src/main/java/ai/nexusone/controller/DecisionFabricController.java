package ai.nexusone.controller;

import ai.nexusone.dto.request.*;
import ai.nexusone.service.DecisionFabricService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/decision-fabric")
@CrossOrigin(origins="*")
public class DecisionFabricController {
    private final DecisionFabricService service;
    public DecisionFabricController(DecisionFabricService service){this.service=service;}
    @PostMapping("/evaluate") public ResponseEntity<?> evaluate(@Valid @RequestBody DecisionFabricRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.evaluate(r));}
    @PutMapping("/{id}/decision") public ResponseEntity<?> decide(@PathVariable Long id,@Valid @RequestBody DecisionApprovalRequest r){return ResponseEntity.ok(service.decide(id,r));}
    @GetMapping("/overview") public ResponseEntity<?> overview(){return ResponseEntity.ok(service.overview());}
    @GetMapping("/analytics") public ResponseEntity<?> analytics(){return ResponseEntity.ok(service.analytics());}
    @GetMapping("/pending-approvals") public ResponseEntity<?> pending(){return ResponseEntity.ok(service.pendingApprovals());}
    @GetMapping("/history") public ResponseEntity<?> history(Pageable p){return ResponseEntity.ok(service.history(p));}
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable Long id){return ResponseEntity.ok(service.get(id));}
    @ExceptionHandler({IllegalArgumentException.class,IllegalStateException.class,NoSuchElementException.class})
    public ResponseEntity<?> invalid(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));}
}
