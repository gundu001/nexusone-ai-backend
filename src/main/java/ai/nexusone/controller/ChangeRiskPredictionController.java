package ai.nexusone.controller;
import ai.nexusone.dto.response.*;
import ai.nexusone.service.ChangeRiskPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/change-risk")
public class ChangeRiskPredictionController {
    private final ChangeRiskPredictionService service;
    public ChangeRiskPredictionController(ChangeRiskPredictionService service){this.service=service;}
    @GetMapping("/overview") public ResponseEntity<ChangeRiskOverviewResponse> overview(){return ResponseEntity.ok(service.getOverview());}
    @GetMapping("/assessments") public ResponseEntity<List<ChangeRiskAssessmentResponse>> assessments(){return ResponseEntity.ok(service.getAssessments());}
    @GetMapping("/impact-analysis") public ResponseEntity<List<ImpactAnalysisResponse>> impact(){return ResponseEntity.ok(service.getImpactAnalysis());}
    @GetMapping("/trends") public ResponseEntity<ChangeRiskTrendResponse> trends(){return ResponseEntity.ok(service.getTrends());}
    @GetMapping("/recommendations") public ResponseEntity<List<ChangeRiskRecommendationResponse>> recommendations(){return ResponseEntity.ok(service.getRecommendations());}
}
