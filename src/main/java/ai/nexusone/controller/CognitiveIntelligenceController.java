package ai.nexusone.controller;

import ai.nexusone.dto.ApproveInsightRequest;
import ai.nexusone.dto.CognitiveAnalyticsResponse;
import ai.nexusone.dto.CognitiveInsightRequest;
import ai.nexusone.dto.CognitiveInsightResponse;
import ai.nexusone.dto.GenerateInsightRequest;
import ai.nexusone.dto.ReviewInsightRequest;
import ai.nexusone.enums.InsightStatus;
import ai.nexusone.enums.InsightType;
import ai.nexusone.service.CognitiveIntelligenceService;
import jakarta.validation.Valid;
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

import java.util.Map;

@RestController
@RequestMapping("/api/cognitive-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class CognitiveIntelligenceController {

    private final CognitiveIntelligenceService service;

    public CognitiveIntelligenceController(CognitiveIntelligenceService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<CognitiveInsightResponse> create(
            @Valid @RequestBody CognitiveInsightRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PostMapping("/generate")
    public ResponseEntity<CognitiveInsightResponse> generate(
            @Valid @RequestBody GenerateInsightRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.generate(request));
    }

    @GetMapping("/{id}")
    public CognitiveInsightResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/history")
    public Page<CognitiveInsightResponse> history(
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {

        return service.history(pageable);
    }

    @GetMapping("/search")
    public Page<CognitiveInsightResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) InsightType type,
            @RequestParam(required = false) InsightStatus status,
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {

        return service.search(keyword, type, status, pageable);
    }

    @PostMapping("/{id}/review")
    public CognitiveInsightResponse review(
            @PathVariable Long id,
            @Valid @RequestBody ReviewInsightRequest request) {

        return service.review(id, request);
    }

    @PostMapping("/{id}/approve")
    public CognitiveInsightResponse approve(
            @PathVariable Long id,
            @Valid @RequestBody ApproveInsightRequest request) {

        return service.approve(id, request);
    }

    @PostMapping("/{id}/archive")
    public CognitiveInsightResponse archive(@PathVariable Long id) {
        return service.archive(id);
    }

    @GetMapping("/analytics")
    public CognitiveAnalyticsResponse analytics() {
        return service.analytics();
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "mode", "AUTONOMOUS_ENTERPRISE_COGNITIVE_INTELLIGENCE"
        );
    }
}
