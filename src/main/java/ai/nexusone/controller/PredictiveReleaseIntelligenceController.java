package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.service.PredictiveReleaseIntelligenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/predictive")
public class PredictiveReleaseIntelligenceController {
    private final PredictiveReleaseIntelligenceService service;
    public PredictiveReleaseIntelligenceController(PredictiveReleaseIntelligenceService service){this.service=service;}
    @GetMapping("/overview") public ResponseEntity<PredictiveOverviewResponse> overview(){return ResponseEntity.ok(service.getOverview());}
    @GetMapping("/release-success/{executionId}") public ResponseEntity<ReleaseSuccessPredictionResponse> success(@PathVariable Long executionId){return ResponseEntity.ok(service.predictReleaseSuccess(executionId));}
    @GetMapping("/failure-probability/{executionId}") public ResponseEntity<FailureProbabilityResponse> failure(@PathVariable Long executionId){return ResponseEntity.ok(service.getFailureProbability(executionId));}
    @GetMapping("/risk-forecast") public ResponseEntity<List<RiskForecastResponse>> risk(){return ResponseEntity.ok(service.getRiskForecast());}
    @GetMapping("/deployment-capacity") public ResponseEntity<DeploymentCapacityResponse> capacity(){return ResponseEntity.ok(service.getDeploymentCapacity());}
    @GetMapping("/change-impact/{executionId}") public ResponseEntity<ChangeImpactResponse> impact(@PathVariable Long executionId){return ResponseEntity.ok(service.getChangeImpact(executionId));}
    @GetMapping("/ai-predictions") public ResponseEntity<List<AiPredictionResponse>> predictions(){return ResponseEntity.ok(service.getAiPredictions());}
}
