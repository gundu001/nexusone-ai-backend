package ai.nexusone.controller;

import ai.nexusone.dto.request.AgentExecutionRequest;
import ai.nexusone.service.AgenticEnterpriseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/agentic-enterprise")
@CrossOrigin(origins = "*")
public class AgenticEnterpriseController {
    private final AgenticEnterpriseService service;

    public AgenticEnterpriseController(AgenticEnterpriseService service) {
        this.service = service;
    }

    @PostMapping("/execute")
    public ResponseEntity<?> execute(@Valid @RequestBody AgentExecutionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.execute(request));
    }

    @GetMapping("/overview")
    public ResponseEntity<?> overview() { return ResponseEntity.ok(service.overview()); }

    @GetMapping("/history")
    public ResponseEntity<?> history(Pageable pageable) { return ResponseEntity.ok(service.history(pageable)); }

    @GetMapping("/recommendations")
    public ResponseEntity<?> recommendations() { return ResponseEntity.ok(service.recommendations()); }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) { return ResponseEntity.ok(service.get(id)); }

    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
    public ResponseEntity<?> invalid(RuntimeException exception) {
        return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
    }
}
