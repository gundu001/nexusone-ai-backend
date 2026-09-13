package ai.nexusone.controller;

import ai.nexusone.dto.ControlTowerDtos;
import ai.nexusone.service.ControlTowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/control-tower")
public class ControlTowerController {
    private final ControlTowerService service;
    public ControlTowerController(ControlTowerService service) { this.service = service; }

    @GetMapping("/overview")
    public ControlTowerDtos.OverviewResponse overview() { return service.overview(); }
    @GetMapping("/opportunities")
    public List<ControlTowerDtos.OpportunityResponse> opportunities() { return service.opportunities(); }
    @GetMapping("/approvals")
    public List<ControlTowerDtos.ApprovalResponse> approvals() { return service.approvals(); }
    @PostMapping("/approvals/{approvalId}/approve")
    public ControlTowerDtos.ApprovalResponse approve(@PathVariable long approvalId, @RequestBody(required = false) ControlTowerDtos.DecisionRequest request) {
        return service.decide(approvalId, true, request == null ? null : request.note());
    }
    @PostMapping("/approvals/{approvalId}/reject")
    public ControlTowerDtos.ApprovalResponse reject(@PathVariable long approvalId, @RequestBody(required = false) ControlTowerDtos.DecisionRequest request) {
        return service.decide(approvalId, false, request == null ? null : request.note());
    }
    @PostMapping("/execute/{executionId}")
    public ResponseEntity<ControlTowerDtos.ExecutionResponse> execute(@PathVariable long executionId) {
        return ResponseEntity.ok(service.execute(executionId));
    }
    @GetMapping("/history")
    public List<ControlTowerDtos.HistoryResponse> history() { return service.history(); }
}
