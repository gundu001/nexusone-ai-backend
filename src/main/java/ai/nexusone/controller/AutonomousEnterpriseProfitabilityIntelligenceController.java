package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.enums.*;
import ai.nexusone.service.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autonomous-enterprise-profitability-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class AutonomousEnterpriseProfitabilityIntelligenceController {
    private final AutonomousEnterpriseProfitabilityIntelligenceService service;

    public AutonomousEnterpriseProfitabilityIntelligenceController(AutonomousEnterpriseProfitabilityIntelligenceService service) { this.service = service; }

    @PostMapping("/generate")
    public ResponseEntity<AutonomousEnterpriseProfitabilityIntelligenceResponse> generate(@Valid @RequestBody AutonomousEnterpriseProfitabilityIntelligenceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    public AutonomousEnterpriseProfitabilityIntelligenceResponse get(@PathVariable Long id) { return service.getById(id); }

    @GetMapping("/history")
    public Page<AutonomousEnterpriseProfitabilityIntelligenceResponse> history(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/search")
    public Page<AutonomousEnterpriseProfitabilityIntelligenceResponse> search(@RequestParam(required = false) String keyword, @RequestParam(required = false) AutonomousEnterpriseProfitabilityIntelligencePriority priority, @RequestParam(required = false) AutonomousEnterpriseProfitabilityIntelligenceStatus status, @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }
}
