package ai.nexusone.controller;

import ai.nexusone.dto.RiskAnalysisResult;
import ai.nexusone.service.RiskAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/repositories")
@CrossOrigin("*")
public class RiskAnalysisController {

    private final RiskAnalysisService riskAnalysisService;

    public RiskAnalysisController(
            RiskAnalysisService riskAnalysisService) {

        this.riskAnalysisService = riskAnalysisService;
    }

    @GetMapping("/risk")
    public ResponseEntity<RiskAnalysisResult> analyzeRisk(
            @RequestParam("repoName") String repoName)
            throws Exception {

        RiskAnalysisResult result =
                riskAnalysisService
                        .analyzeRisk(repoName);

        return ResponseEntity.ok(result);
    }
}