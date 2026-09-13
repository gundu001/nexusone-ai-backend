package ai.nexusone.controller;

import ai.nexusone.dto.ExecutiveBusinessKpiResponse;
import ai.nexusone.dto.ExecutiveInsightResponse;
import ai.nexusone.dto.ExecutiveOverviewResponse;
import ai.nexusone.dto.ExecutivePlatformHealthResponse;
import ai.nexusone.dto.ExecutiveReliabilityResponse;
import ai.nexusone.dto.ExecutiveTrendResponse;
import ai.nexusone.service.ExecutiveIntelligenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/executive")
public class ExecutiveIntelligenceController {

    private final ExecutiveIntelligenceService service;

    public ExecutiveIntelligenceController(ExecutiveIntelligenceService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public ResponseEntity<ExecutiveOverviewResponse> overview() {
        return ResponseEntity.ok(service.getOverview());
    }

    @GetMapping("/business-kpis")
    public ResponseEntity<ExecutiveBusinessKpiResponse> businessKpis() {
        return ResponseEntity.ok(service.getBusinessKpis());
    }

    @GetMapping("/platform-health")
    public ResponseEntity<ExecutivePlatformHealthResponse> platformHealth() {
        return ResponseEntity.ok(service.getPlatformHealth());
    }

    @GetMapping("/reliability")
    public ResponseEntity<ExecutiveReliabilityResponse> reliability() {
        return ResponseEntity.ok(service.getReliability());
    }

    @GetMapping("/trends")
    public ResponseEntity<List<ExecutiveTrendResponse>> trends() {
        return ResponseEntity.ok(service.getTrends());
    }

    @GetMapping("/ai-insights")
    public ResponseEntity<List<ExecutiveInsightResponse>> insights() {
        return ResponseEntity.ok(service.getAiInsights());
    }
}
