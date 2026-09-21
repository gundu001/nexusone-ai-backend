package ai.nexusone.controller;

import ai.nexusone.dto.request.AutonomousCommandCenterRequest;
import ai.nexusone.service.AutonomousCommandCenterService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/autonomous-command-center")
@CrossOrigin(origins = "*")
public class AutonomousCommandCenterController {
    private final AutonomousCommandCenterService service;

    public AutonomousCommandCenterController(AutonomousCommandCenterService service) { this.service = service; }

    @PostMapping("/analyze")
    public ResponseEntity<?> analyze(@Valid @RequestBody AutonomousCommandCenterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.analyze(request));
    }

    @GetMapping("/overview") public ResponseEntity<?> overview() { return ResponseEntity.ok(service.overview()); }
    @GetMapping("/history") public ResponseEntity<?> history(Pageable pageable) { return ResponseEntity.ok(service.history(pageable)); }
    @GetMapping("/recommendations") public ResponseEntity<?> recommendations() { return ResponseEntity.ok(service.recommendations()); }
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable Long id) { return ResponseEntity.ok(service.get(id)); }

    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
    public ResponseEntity<?> invalid(RuntimeException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
    }
}
