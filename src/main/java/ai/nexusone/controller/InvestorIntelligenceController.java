package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.enums.InvestorIntelligenceStatus;
import ai.nexusone.enums.InvestorPriority;
import ai.nexusone.service.InvestorIntelligenceService;
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
@RequestMapping("/api/investor-shareholder-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class InvestorIntelligenceController {
    private final InvestorIntelligenceService service;

    public InvestorIntelligenceController(InvestorIntelligenceService service) { this.service = service; }

    @PostMapping("/generate")
    ResponseEntity<InvestorIntelligenceResponse> generate(@Valid @RequestBody InvestorIntelligenceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    InvestorIntelligenceResponse get(@PathVariable Long id) { return service.get(id); }

    @GetMapping("/history")
    Page<InvestorIntelligenceResponse> history(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.history(pageable);
    }

    @GetMapping("/search")
    Page<InvestorIntelligenceResponse> search(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) InvestorPriority priority,
            @RequestParam(required = false) InvestorIntelligenceStatus status,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }

    @PostMapping("/{id}/review")
    InvestorIntelligenceResponse review(@PathVariable Long id, @Valid @RequestBody InvestorActionRequest request) {
        return service.review(id, request);
    }

    @PostMapping("/{id}/approve")
    InvestorIntelligenceResponse approve(@PathVariable Long id, @Valid @RequestBody InvestorActionRequest request) {
        return service.approve(id, request);
    }

    @PostMapping("/{id}/reject")
    InvestorIntelligenceResponse reject(@PathVariable Long id, @Valid @RequestBody InvestorActionRequest request) {
        return service.reject(id, request);
    }

    @PostMapping("/{id}/publish")
    InvestorIntelligenceResponse publish(@PathVariable Long id, @Valid @RequestBody InvestorActionRequest request) {
        return service.publish(id, request);
    }

    @GetMapping({"/analytics", "/dashboard"})
    InvestorAnalyticsResponse analytics() { return service.analytics(); }

    @GetMapping("/health")
    Map<String, String> health() {
        return Map.of("status", "UP", "mode", "AUTONOMOUS_ENTERPRISE_INVESTOR_SHAREHOLDER_INTELLIGENCE");
    }
}
