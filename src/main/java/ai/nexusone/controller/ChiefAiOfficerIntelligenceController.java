package ai.nexusone.controller;

import ai.nexusone.dto.ChiefAiOfficerActionRequest;
import ai.nexusone.dto.ChiefAiOfficerAnalyticsResponse;
import ai.nexusone.dto.ChiefAiOfficerIntelligenceRequest;
import ai.nexusone.dto.ChiefAiOfficerIntelligenceResponse;
import ai.nexusone.enums.ChiefAiOfficerPriority;
import ai.nexusone.enums.ChiefAiOfficerStatus;
import ai.nexusone.service.ChiefAiOfficerIntelligenceService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chief-ai-officer-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class ChiefAiOfficerIntelligenceController {
    private final ChiefAiOfficerIntelligenceService service;

    public ChiefAiOfficerIntelligenceController(ChiefAiOfficerIntelligenceService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    ResponseEntity<ChiefAiOfficerIntelligenceResponse> generate(
            @Valid @RequestBody ChiefAiOfficerIntelligenceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    ChiefAiOfficerIntelligenceResponse get(@PathVariable Long id) { return service.get(id); }

    @GetMapping("/history")
    Page<ChiefAiOfficerIntelligenceResponse> history(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.history(pageable);
    }

    @GetMapping("/search")
    Page<ChiefAiOfficerIntelligenceResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) ChiefAiOfficerPriority priority,
            @RequestParam(required = false) ChiefAiOfficerStatus status,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }

    @PostMapping("/{id}/review")
    ChiefAiOfficerIntelligenceResponse review(
            @PathVariable Long id, @Valid @RequestBody ChiefAiOfficerActionRequest request) {
        return service.review(id, request);
    }

    @PostMapping("/{id}/approve")
    ChiefAiOfficerIntelligenceResponse approve(
            @PathVariable Long id, @Valid @RequestBody ChiefAiOfficerActionRequest request) {
        return service.approve(id, request);
    }

    @PostMapping("/{id}/reject")
    ChiefAiOfficerIntelligenceResponse reject(
            @PathVariable Long id, @Valid @RequestBody ChiefAiOfficerActionRequest request) {
        return service.reject(id, request);
    }

    @PostMapping("/{id}/publish")
    ChiefAiOfficerIntelligenceResponse publish(
            @PathVariable Long id, @Valid @RequestBody ChiefAiOfficerActionRequest request) {
        return service.publish(id, request);
    }

    @GetMapping({"/analytics", "/dashboard"})
    ChiefAiOfficerAnalyticsResponse analytics() { return service.analytics(); }

    @GetMapping("/health")
    Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "mode", "AUTONOMOUS_ENTERPRISE_CHIEF_AI_OFFICER_INTELLIGENCE");
    }
}
