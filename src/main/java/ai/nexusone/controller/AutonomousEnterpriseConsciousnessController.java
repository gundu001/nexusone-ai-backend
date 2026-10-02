package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.enums.*;
import ai.nexusone.service.AutonomousEnterpriseConsciousnessService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autonomous-enterprise-consciousness")
@CrossOrigin(origins = "http://localhost:5173")
public class AutonomousEnterpriseConsciousnessController {
    private final AutonomousEnterpriseConsciousnessService service;

    public AutonomousEnterpriseConsciousnessController(
            AutonomousEnterpriseConsciousnessService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    ResponseEntity<AutonomousEnterpriseConsciousnessResponse> generate(
            @Valid @RequestBody AutonomousEnterpriseConsciousnessRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    AutonomousEnterpriseConsciousnessResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/history")
    Page<AutonomousEnterpriseConsciousnessResponse> history(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.history(pageable);
    }

    @GetMapping("/search")
    Page<AutonomousEnterpriseConsciousnessResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) AutonomousEnterpriseConsciousnessPriority priority,
            @RequestParam(required = false) AutonomousEnterpriseConsciousnessStatus status,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }

    @PostMapping("/{id}/review")
    AutonomousEnterpriseConsciousnessResponse review(@PathVariable Long id,
            @Valid @RequestBody AutonomousEnterpriseConsciousnessActionRequest request) {
        return service.review(id, request);
    }

    @PostMapping("/{id}/approve")
    AutonomousEnterpriseConsciousnessResponse approve(@PathVariable Long id,
            @Valid @RequestBody AutonomousEnterpriseConsciousnessActionRequest request) {
        return service.approve(id, request);
    }

    @PostMapping("/{id}/reject")
    AutonomousEnterpriseConsciousnessResponse reject(@PathVariable Long id,
            @Valid @RequestBody AutonomousEnterpriseConsciousnessActionRequest request) {
        return service.reject(id, request);
    }

    @PostMapping("/{id}/publish")
    AutonomousEnterpriseConsciousnessResponse publish(@PathVariable Long id,
            @Valid @RequestBody AutonomousEnterpriseConsciousnessActionRequest request) {
        return service.publish(id, request);
    }

    @GetMapping({"/analytics", "/dashboard"})
    AutonomousEnterpriseConsciousnessAnalyticsResponse analytics() {
        return service.analytics();
    }

    @GetMapping("/health")
    Map<String, String> health() {
        return Map.of("status", "UP", "mode", "AUTONOMOUS_ENTERPRISE_CONSCIOUSNESS_FABRIC");
    }
}
