package ai.nexusone.controller;

import ai.nexusone.dto.request.SreAnalysisRequest;
import ai.nexusone.service.SreIntelligenceService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/sre")
@CrossOrigin(origins = "*")
public class SreIntelligenceController {
    private final SreIntelligenceService service;
    public SreIntelligenceController(SreIntelligenceService service) { this.service = service; }
    @PostMapping("/analyze") public ResponseEntity<?> analyze(@Valid @RequestBody SreAnalysisRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(service.analyze(request)); }
    @GetMapping("/overview") public ResponseEntity<?> overview() { return ResponseEntity.ok(service.overview()); }
    @GetMapping("/history") public ResponseEntity<?> history(Pageable pageable) { return ResponseEntity.ok(service.history(pageable)); }
    @GetMapping("/recommendations") public ResponseEntity<?> recommendations() { return ResponseEntity.ok(service.recommendations()); }
    @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity<?> invalid(IllegalArgumentException e) { return ResponseEntity.badRequest().body(Map.of("message", e.getMessage())); }
}
