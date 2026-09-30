package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.enums.EnterpriseRiskPriority;
import ai.nexusone.enums.EnterpriseRiskStatus;
import ai.nexusone.service.EnterpriseRiskIntelligenceService;
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
@RequestMapping("/api/enterprise-risk-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class EnterpriseRiskIntelligenceController {
    private final EnterpriseRiskIntelligenceService service;

    public EnterpriseRiskIntelligenceController(EnterpriseRiskIntelligenceService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    ResponseEntity<EnterpriseRiskIntelligenceResponse> generate(
            @Valid @RequestBody EnterpriseRiskIntelligenceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    EnterpriseRiskIntelligenceResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/history")
    Page<EnterpriseRiskIntelligenceResponse> history(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.history(pageable);
    }

    @GetMapping("/search")
    Page<EnterpriseRiskIntelligenceResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) EnterpriseRiskPriority priority,
            @RequestParam(required = false) EnterpriseRiskStatus status,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }

    @PostMapping("/{id}/review")
    EnterpriseRiskIntelligenceResponse review(
            @PathVariable Long id, @Valid @RequestBody EnterpriseRiskActionRequest request) {
        return service.review(id, request);
    }

    @PostMapping("/{id}/approve")
    EnterpriseRiskIntelligenceResponse approve(
            @PathVariable Long id, @Valid @RequestBody EnterpriseRiskActionRequest request) {
        return service.approve(id, request);
    }

    @PostMapping("/{id}/reject")
    EnterpriseRiskIntelligenceResponse reject(
            @PathVariable Long id, @Valid @RequestBody EnterpriseRiskActionRequest request) {
        return service.reject(id, request);
    }

    @PostMapping("/{id}/publish")
    EnterpriseRiskIntelligenceResponse publish(
            @PathVariable Long id, @Valid @RequestBody EnterpriseRiskActionRequest request) {
        return service.publish(id, request);
    }

    @GetMapping({"/analytics", "/dashboard"})
    EnterpriseRiskAnalyticsResponse analytics() {
        return service.analytics();
    }

    @GetMapping("/health")
    Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "mode", "AUTONOMOUS_ENTERPRISE_RISK_INTELLIGENCE");
    }
}
