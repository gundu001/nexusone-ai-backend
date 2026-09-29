package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.enums.IndustryEcosystemIntelligenceStatus;
import ai.nexusone.enums.IndustryPriority;
import ai.nexusone.service.IndustryEcosystemIntelligenceService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/industry-ecosystem-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class IndustryEcosystemIntelligenceController {
    private final IndustryEcosystemIntelligenceService service;

    public IndustryEcosystemIntelligenceController(IndustryEcosystemIntelligenceService service) { this.service = service; }

    @PostMapping("/generate")
    ResponseEntity<IndustryEcosystemIntelligenceResponse> generate(@Valid @RequestBody IndustryEcosystemIntelligenceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    IndustryEcosystemIntelligenceResponse get(@PathVariable Long id) { return service.get(id); }

    @GetMapping("/history")
    Page<IndustryEcosystemIntelligenceResponse> history(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.history(pageable);
    }

    @GetMapping("/search")
    Page<IndustryEcosystemIntelligenceResponse> search(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) IndustryPriority priority,
            @RequestParam(required = false) IndustryEcosystemIntelligenceStatus status,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }

    @PostMapping("/{id}/review")
    IndustryEcosystemIntelligenceResponse review(@PathVariable Long id, @Valid @RequestBody IndustryActionRequest request) {
        return service.review(id, request);
    }

    @PostMapping("/{id}/approve")
    IndustryEcosystemIntelligenceResponse approve(@PathVariable Long id, @Valid @RequestBody IndustryActionRequest request) {
        return service.approve(id, request);
    }

    @PostMapping("/{id}/reject")
    IndustryEcosystemIntelligenceResponse reject(@PathVariable Long id, @Valid @RequestBody IndustryActionRequest request) {
        return service.reject(id, request);
    }

    @PostMapping("/{id}/publish")
    IndustryEcosystemIntelligenceResponse publish(@PathVariable Long id, @Valid @RequestBody IndustryActionRequest request) {
        return service.publish(id, request);
    }

    @GetMapping({"/analytics", "/dashboard"})
    IndustryAnalyticsResponse analytics() { return service.analytics(); }

    @GetMapping("/health")
    Map<String, String> health() {
        return Map.of("status", "UP", "mode", "AUTONOMOUS_ENTERPRISE_INDUSTRY_ECOSYSTEM_INTELLIGENCE");
    }
}
