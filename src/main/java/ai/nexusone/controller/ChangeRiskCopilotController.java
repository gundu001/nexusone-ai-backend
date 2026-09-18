package ai.nexusone.controller;

import ai.nexusone.dto.request.ChangeRiskCopilotQueryRequest;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.ChangeRiskCopilotAnalysis;
import ai.nexusone.service.ChangeRiskCopilotService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/change-risk-copilot")
public class ChangeRiskCopilotController {
    private final ChangeRiskCopilotService service;

    public ChangeRiskCopilotController(ChangeRiskCopilotService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public ResponseEntity<ChangeRiskCopilotOverviewResponse> overview() {
        return ResponseEntity.ok(service.overview());
    }

    @GetMapping("/changes")
    public ResponseEntity<List<ChangeRiskAssessmentResponse>> changes() {
        return ResponseEntity.ok(service.changes());
    }

    @GetMapping("/changes/{changeId}/assessment")
    public ResponseEntity<ChangeRiskCopilotAnswerResponse> assessment(@PathVariable Long changeId) {
        return ResponseEntity.ok(service.assess(changeId));
    }

    @PostMapping("/ask")
    public ResponseEntity<ChangeRiskCopilotAnswerResponse> ask(
            @Valid @RequestBody ChangeRiskCopilotQueryRequest request) {
        return ResponseEntity.ok(service.ask(request));
    }

    @GetMapping("/history")
    public ResponseEntity<Page<ChangeRiskCopilotAnalysis>> history(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(service.history(page, size));
    }

    @GetMapping("/history/{changeId}")
    public ResponseEntity<Page<ChangeRiskCopilotAnalysis>> historyByChange(
            @PathVariable Long changeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(service.historyByChange(changeId, page, size));
    }
}
