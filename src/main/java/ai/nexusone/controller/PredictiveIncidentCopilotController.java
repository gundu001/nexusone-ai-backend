package ai.nexusone.controller;

import ai.nexusone.dto.request.PredictiveIncidentQueryRequest;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.PredictiveIncidentAnalysis;
import ai.nexusone.service.PredictiveIncidentCopilotService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/predictive-incident-copilot")
public class PredictiveIncidentCopilotController {
    private final PredictiveIncidentCopilotService service;

    public PredictiveIncidentCopilotController(PredictiveIncidentCopilotService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public PredictiveIncidentOverviewResponse overview() { return service.overview(); }

    @GetMapping("/predictions")
    public List<PredictiveIncidentResponse> predictions() { return service.predictions(); }

    @GetMapping("/applications/{application}/assessment")
    public PredictiveIncidentAssessmentResponse assessment(@PathVariable String application) {
        return service.assessment(application);
    }

    @PostMapping("/forecast")
    public PredictiveIncidentAssessmentResponse forecast(@Valid @RequestBody PredictiveIncidentQueryRequest request) {
        return service.forecast(request);
    }

    @GetMapping("/history")
    public Page<PredictiveIncidentAnalysis> history(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "20") int size) {
        return service.history(page, size);
    }

    @GetMapping("/applications/{application}/history")
    public Page<PredictiveIncidentAnalysis> historyByApplication(@PathVariable String application,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return service.historyByApplication(application, page, size);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> notFound(NoSuchElementException ex) {
        return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
    }
}
