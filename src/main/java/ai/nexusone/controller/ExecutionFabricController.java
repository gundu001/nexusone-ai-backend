package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.service.ExecutionFabricService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;

import java.util.Map;



@RestController
@RequestMapping("/api/execution-fabric")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ExecutionFabricController {
    private final ExecutionFabricService service;

    @PostMapping("/execute")
    public ResponseEntity<ExecutionResponse> execute(@Valid @RequestBody ExecutionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.execute(request));
    }
    @PostMapping("/{id}/rollback")
    public ExecutionResponse rollback(@PathVariable Long id, @RequestBody Map<String,String> body) {
        return service.rollback(id, body.getOrDefault("requestedBy", "NexusOne AI Operator"));
    }
    @PostMapping("/{id}/retry")
    public ExecutionResponse retry(@PathVariable Long id, @RequestBody Map<String,String> body) {
        return service.retry(id, body.getOrDefault("requestedBy", "NexusOne AI Operator"));
    }
    @PostMapping("/{id}/cancel")
    public ExecutionResponse cancel(@PathVariable Long id, @RequestBody Map<String,String> body) {
        return service.cancel(id, body.getOrDefault("requestedBy", "NexusOne AI Operator"));
    }
    @GetMapping("/{id}") public ExecutionResponse get(@PathVariable Long id) { return service.get(id); }
    @GetMapping("/history")
    public Page<ExecutionResponse> history(@PageableDefault(size=20, sort="createdAt", direction=Sort.Direction.DESC) Pageable p) { return service.history(p); }
    @GetMapping("/analytics") public ExecutionAnalyticsResponse analytics() { return service.analytics(); }
    @GetMapping("/health") public Map<String,String> health() { return Map.of("status","UP","mode","SIMULATION"); }
}
