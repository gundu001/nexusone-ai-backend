package ai.nexusone.controller;

import ai.nexusone.dto.CeoActionRequest;
import ai.nexusone.dto.CeoAnalyticsResponse;
import ai.nexusone.dto.CeoIntelligenceRequest;
import ai.nexusone.dto.CeoIntelligenceResponse;
import ai.nexusone.enums.CeoIntelligenceStatus;
import ai.nexusone.enums.CeoPriority;
import ai.nexusone.service.CeoIntelligenceService;
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
@RequestMapping("/api/ceo-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class CeoIntelligenceController {

    private final CeoIntelligenceService service;

    public CeoIntelligenceController(CeoIntelligenceService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    ResponseEntity<CeoIntelligenceResponse> generate(
            @Valid @RequestBody CeoIntelligenceRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    CeoIntelligenceResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/history")
    Page<CeoIntelligenceResponse> history(
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        return service.history(pageable);
    }

    @GetMapping("/search")
    Page<CeoIntelligenceResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) CeoPriority priority,
            @RequestParam(required = false) CeoIntelligenceStatus status,
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        return service.search(keyword, priority, status, pageable);
    }

    @PostMapping("/{id}/review")
    CeoIntelligenceResponse review(
            @PathVariable Long id,
            @Valid @RequestBody CeoActionRequest request
    ) {
        return service.review(id, request);
    }

    @PostMapping("/{id}/approve")
    CeoIntelligenceResponse approve(
            @PathVariable Long id,
            @Valid @RequestBody CeoActionRequest request
    ) {
        return service.approve(id, request);
    }

    @PostMapping("/{id}/reject")
    CeoIntelligenceResponse reject(
            @PathVariable Long id,
            @Valid @RequestBody CeoActionRequest request
    ) {
        return service.reject(id, request);
    }

    @PostMapping("/{id}/execute")
    CeoIntelligenceResponse execute(
            @PathVariable Long id,
            @Valid @RequestBody CeoActionRequest request
    ) {
        return service.execute(id, request);
    }

    @GetMapping({"/analytics", "/dashboard"})
    CeoAnalyticsResponse analytics() {
        return service.analytics();
    }

    @GetMapping("/health")
    Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "mode", "AUTONOMOUS_ENTERPRISE_CEO_INTELLIGENCE"
        );
    }
}
