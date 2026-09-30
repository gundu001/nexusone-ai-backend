package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.enums.GlobalEconomicPriority;
import ai.nexusone.enums.GlobalEconomicStatus;
import ai.nexusone.service.GlobalEconomicIntelligenceService;
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
@RequestMapping("/api/global-economic-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class GlobalEconomicIntelligenceController {
    private final GlobalEconomicIntelligenceService service;

    public GlobalEconomicIntelligenceController(GlobalEconomicIntelligenceService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    ResponseEntity<GlobalEconomicIntelligenceResponse> generate(
            @Valid @RequestBody GlobalEconomicIntelligenceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    GlobalEconomicIntelligenceResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/history")
    Page<GlobalEconomicIntelligenceResponse> history(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.history(pageable);
    }

    @GetMapping("/search")
    Page<GlobalEconomicIntelligenceResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) GlobalEconomicPriority priority,
            @RequestParam(required = false) GlobalEconomicStatus status,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }

    @PostMapping("/{id}/review")
    GlobalEconomicIntelligenceResponse review(
            @PathVariable Long id, @Valid @RequestBody GlobalEconomicActionRequest request) {
        return service.review(id, request);
    }

    @PostMapping("/{id}/approve")
    GlobalEconomicIntelligenceResponse approve(
            @PathVariable Long id, @Valid @RequestBody GlobalEconomicActionRequest request) {
        return service.approve(id, request);
    }

    @PostMapping("/{id}/reject")
    GlobalEconomicIntelligenceResponse reject(
            @PathVariable Long id, @Valid @RequestBody GlobalEconomicActionRequest request) {
        return service.reject(id, request);
    }

    @PostMapping("/{id}/publish")
    GlobalEconomicIntelligenceResponse publish(
            @PathVariable Long id, @Valid @RequestBody GlobalEconomicActionRequest request) {
        return service.publish(id, request);
    }

    @GetMapping({"/analytics", "/dashboard"})
    GlobalEconomicAnalyticsResponse analytics() {
        return service.analytics();
    }

    @GetMapping("/health")
    Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "mode", "AUTONOMOUS_ENTERPRISE_GLOBAL_ECONOMIC_INTELLIGENCE");
    }
}
