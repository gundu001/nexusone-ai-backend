package ai.nexusone.controller;

import ai.nexusone.dto.request.PlatformEngineeringAssessmentRequest;
import ai.nexusone.service.PlatformEngineeringService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/platform-engineering")
@CrossOrigin(origins="*")
public class PlatformEngineeringController {
    private final PlatformEngineeringService service;
    public PlatformEngineeringController(PlatformEngineeringService service){this.service=service;}
    @PostMapping("/assess") public ResponseEntity<?> assess(@Valid @RequestBody PlatformEngineeringAssessmentRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.assess(r));}
    @GetMapping("/overview") public ResponseEntity<?> overview(){return ResponseEntity.ok(service.overview());}
    @GetMapping("/history") public ResponseEntity<?> history(Pageable p){return ResponseEntity.ok(service.history(p));}
    @GetMapping("/recommendations") public ResponseEntity<?> recommendations(){return ResponseEntity.ok(service.recommendations());}
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable Long id){return ResponseEntity.ok(service.get(id));}
    @ExceptionHandler({IllegalArgumentException.class,NoSuchElementException.class}) public ResponseEntity<?> invalid(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));}
}
