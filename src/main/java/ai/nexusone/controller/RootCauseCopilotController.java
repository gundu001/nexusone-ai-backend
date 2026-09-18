package ai.nexusone.controller;

import ai.nexusone.dto.request.RootCauseQueryRequest;
import ai.nexusone.dto.response.RootCauseAssessmentResponse;
import ai.nexusone.dto.response.RootCauseIncidentResponse;
import ai.nexusone.dto.response.RootCauseOverviewResponse;
import ai.nexusone.entity.RootCauseAnalysis;
import ai.nexusone.service.RootCauseCopilotService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/root-cause-copilot")
@CrossOrigin(origins = "http://localhost:5173")
public class RootCauseCopilotController {
    private final RootCauseCopilotService service;

    public RootCauseCopilotController(RootCauseCopilotService service) { this.service = service; }

    @GetMapping("/overview")
    public RootCauseOverviewResponse overview() { return service.overview(); }

    @GetMapping("/incidents")
    public List<RootCauseIncidentResponse> incidents() { return service.incidents(); }

    @GetMapping("/incidents/{incidentId}/assessment")
    public RootCauseAssessmentResponse assessment(@PathVariable String incidentId) {
        return service.assessment(incidentId);
    }

    @PostMapping("/ask")
    public RootCauseAssessmentResponse ask(@Valid @RequestBody RootCauseQueryRequest request) {
        return service.ask(request);
    }

    @GetMapping("/history")
    public Page<RootCauseAnalysis> history(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "20") int size) {
        return service.history(page, size);
    }

    @GetMapping("/history/{incidentId}")
    public Page<RootCauseAnalysis> historyByIncident(@PathVariable String incidentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return service.historyByIncident(incidentId, page, size);
    }
}
