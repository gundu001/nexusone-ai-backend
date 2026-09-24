package ai.nexusone.controller;

import ai.nexusone.dto.ExecutiveAdvisoryAnalyticsResponse;
import ai.nexusone.dto.ExecutiveAdvisoryRequest;
import ai.nexusone.dto.ExecutiveAdvisoryResponse;
import ai.nexusone.dto.ExecutiveDecisionRequest;
import ai.nexusone.enums.ExecutiveAdvisoryStatus;
import ai.nexusone.enums.ExecutivePriority;
import ai.nexusone.service.ExecutiveAdvisoryService;
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
@RequestMapping("/api/executive-advisory")
@CrossOrigin(origins = "http://localhost:5173")
public class ExecutiveAdvisoryController {
    private final ExecutiveAdvisoryService service;

    public ExecutiveAdvisoryController(ExecutiveAdvisoryService service) { this.service = service; }

    @PostMapping("/generate")
    ResponseEntity<ExecutiveAdvisoryResponse> generate(@Valid @RequestBody ExecutiveAdvisoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    ExecutiveAdvisoryResponse get(@PathVariable Long id) { return service.get(id); }

    @GetMapping("/history")
    Page<ExecutiveAdvisoryResponse> history(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.history(pageable);
    }

    @GetMapping("/search")
    Page<ExecutiveAdvisoryResponse> search(@RequestParam(required = false) String keyword,
                                           @RequestParam(required = false) ExecutivePriority priority,
                                           @RequestParam(required = false) ExecutiveAdvisoryStatus status,
                                           @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
                                           Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }

    @PostMapping("/{id}/recommend")
    ExecutiveAdvisoryResponse recommend(@PathVariable Long id,
                                         @Valid @RequestBody ExecutiveDecisionRequest request) {
        return service.recommend(id, request);
    }

    @PostMapping("/{id}/accept")
    ExecutiveAdvisoryResponse accept(@PathVariable Long id,
                                      @Valid @RequestBody ExecutiveDecisionRequest request) {
        return service.accept(id, request);
    }

    @PostMapping("/{id}/reject")
    ExecutiveAdvisoryResponse reject(@PathVariable Long id,
                                      @Valid @RequestBody ExecutiveDecisionRequest request) {
        return service.reject(id, request);
    }

    @PostMapping("/{id}/execute")
    ExecutiveAdvisoryResponse execute(@PathVariable Long id,
                                       @Valid @RequestBody ExecutiveDecisionRequest request) {
        return service.execute(id, request);
    }

    @GetMapping("/analytics")
    ExecutiveAdvisoryAnalyticsResponse analytics() { return service.analytics(); }

    @GetMapping("/dashboard")
    ExecutiveAdvisoryAnalyticsResponse dashboard() { return service.analytics(); }

    @GetMapping("/health")
    Map<String, String> health() {
        return Map.of("status", "UP", "mode", "AUTONOMOUS_ENTERPRISE_EXECUTIVE_ADVISORY");
    }
}
