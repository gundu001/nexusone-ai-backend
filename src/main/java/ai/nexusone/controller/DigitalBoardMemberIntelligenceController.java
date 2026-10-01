package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.enums.DigitalBoardMemberPriority;
import ai.nexusone.enums.DigitalBoardMemberStatus;
import ai.nexusone.service.DigitalBoardMemberIntelligenceService;
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
@RequestMapping("/api/digital-board-member-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class DigitalBoardMemberIntelligenceController {
    private final DigitalBoardMemberIntelligenceService service;

    public DigitalBoardMemberIntelligenceController(DigitalBoardMemberIntelligenceService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    ResponseEntity<DigitalBoardMemberIntelligenceResponse> generate(
            @Valid @RequestBody DigitalBoardMemberIntelligenceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    DigitalBoardMemberIntelligenceResponse get(@PathVariable Long id) { return service.get(id); }

    @GetMapping("/history")
    Page<DigitalBoardMemberIntelligenceResponse> history(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.history(pageable);
    }

    @GetMapping("/search")
    Page<DigitalBoardMemberIntelligenceResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) DigitalBoardMemberPriority priority,
            @RequestParam(required = false) DigitalBoardMemberStatus status,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }

    @PostMapping("/{id}/review")
    DigitalBoardMemberIntelligenceResponse review(@PathVariable Long id,
            @Valid @RequestBody DigitalBoardMemberActionRequest request) {
        return service.review(id, request);
    }

    @PostMapping("/{id}/approve")
    DigitalBoardMemberIntelligenceResponse approve(@PathVariable Long id,
            @Valid @RequestBody DigitalBoardMemberActionRequest request) {
        return service.approve(id, request);
    }

    @PostMapping("/{id}/reject")
    DigitalBoardMemberIntelligenceResponse reject(@PathVariable Long id,
            @Valid @RequestBody DigitalBoardMemberActionRequest request) {
        return service.reject(id, request);
    }

    @PostMapping("/{id}/publish")
    DigitalBoardMemberIntelligenceResponse publish(@PathVariable Long id,
            @Valid @RequestBody DigitalBoardMemberActionRequest request) {
        return service.publish(id, request);
    }

    @GetMapping({"/analytics", "/dashboard"})
    DigitalBoardMemberAnalyticsResponse analytics() { return service.analytics(); }

    @GetMapping("/health")
    Map<String, String> health() {
        return Map.of("status", "UP", "mode", "AUTONOMOUS_ENTERPRISE_DIGITAL_BOARD_MEMBER_INTELLIGENCE");
    }
}
