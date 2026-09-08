package ai.nexusone.controller;

import ai.nexusone.dto.DashboardSummary;
import ai.nexusone.dto.RiskDistribution;
import ai.nexusone.entity.RiskAnalysisResultEntity;
import ai.nexusone.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService
            dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService =
                dashboardService;
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummary>
    getSummary() {

        DashboardSummary summary =
                dashboardService.getSummary();

        return ResponseEntity.ok(summary);
    }

    @GetMapping("/risk-distribution")
    public ResponseEntity<List<RiskDistribution>>
    getRiskDistribution() {

        List<RiskDistribution> distribution =
                dashboardService
                        .getRiskDistribution();

        return ResponseEntity.ok(distribution);
    }

    @GetMapping("/recent-risk-analyses")
    public ResponseEntity<
            List<RiskAnalysisResultEntity>>
    getRecentRiskAnalyses() {

        List<RiskAnalysisResultEntity> results =
                dashboardService
                        .getRecentRiskAnalyses();

        return ResponseEntity.ok(results);
    }

    @GetMapping("/latest-risk-analysis")
    public ResponseEntity<RiskAnalysisResultEntity>
    getLatestRiskAnalysis() {

        RiskAnalysisResultEntity result =
                dashboardService
                        .getLatestRiskAnalysis();

        if (result == null) {

            return ResponseEntity
                    .noContent()
                    .build();
        }

        return ResponseEntity.ok(result);
    }
}