package ai.nexusone.service;

import ai.nexusone.dto.ControlTowerDtos;
import  ai.nexusone.enums.ApprovalStatus;
import  ai.nexusone.enums.AutomationStatus;
import ai.nexusone.store.ControlTowerStore;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ControlTowerService {
    private final ControlTowerStore store;

    public ControlTowerService(ControlTowerStore store) { this.store = store; }

    @PostConstruct
    void initializeReferenceState() {
        register(buildOpportunity(8, "Order-Service", "AUTO_PROMOTION", "Promote after validation",
                95, false, "READY", List.of("Successful terminal execution", "Health score 100", "Promotion eligible")));
        register(buildOpportunity(6, "Order-Service", "ACTIVE_EXECUTION_MONITOR", "Monitor active execution",
                72, true, "APPROVAL_REQUIRED", List.of("Execution is running", "Health level WATCH")));
        register(buildOpportunity(7, "Order-Service", "WAIT_FOR_TERMINAL_RESULT", "Hold until terminal result",
                55, false, "BLOCKED", List.of("Execution is queued", "Build evidence missing")));
        register(buildOpportunity(5, "Order-Service", "WAIT_FOR_TERMINAL_RESULT", "Hold until terminal result",
                55, false, "BLOCKED", List.of("Execution is queued", "Build evidence missing")));
    }

    private ControlTowerDtos.OpportunityResponse buildOpportunity(long id, String repository, String code, String title,
                                                                  int confidence, boolean approval, String status, List<String> evidence) {
        return new ControlTowerDtos.OpportunityResponse(id, repository, code, title, confidence,
                confidence >= 70, approval, status, evidence);
    }

    private void register(ControlTowerDtos.OpportunityResponse opportunity) {
        store.saveOpportunity(opportunity);
        if (opportunity.humanApprovalRequired() && store.approvalForExecution(opportunity.executionId()).isEmpty()) {
            long id = store.nextApprovalId();
            store.saveApproval(new ControlTowerDtos.ApprovalResponse(id, opportunity.executionId(), opportunity.opportunityCode(),
                    "RELEASE_MANAGER", ApprovalStatus.PENDING.name(), LocalDateTime.now(), null, null));
        }
    }

    public ControlTowerDtos.OverviewResponse overview() {
        List<ControlTowerDtos.HistoryResponse> history = store.history();
        long completed = history.stream().filter(h -> h.status().equals("SUCCESS") || h.status().equals("FAILED")).count();
        long success = history.stream().filter(h -> h.status().equals("SUCCESS")).count();
        long failed = history.stream().filter(h -> h.status().equals("FAILED")).count();
        long pending = store.approvals().stream().filter(a -> a.status().equals(ApprovalStatus.PENDING.name())).count();
        double rate = completed == 0 ? 0.0 : Math.round((success * 10000.0) / completed) / 100.0;
        String status = failed > 0 ? "ATTENTION_REQUIRED" : pending > 0 ? "APPROVAL_MONITORING" : "ACTIVE";
        return new ControlTowerDtos.OverviewResponse(completed, success, failed, pending, rate, status, LocalDateTime.now());
    }

    public List<ControlTowerDtos.OpportunityResponse> opportunities() { return store.opportunities(); }
    public List<ControlTowerDtos.ApprovalResponse> approvals() { return store.approvals(); }
    public List<ControlTowerDtos.HistoryResponse> history() { return store.history(); }

    public synchronized ControlTowerDtos.ApprovalResponse decide(long approvalId, boolean approved, String note) {
        ControlTowerDtos.ApprovalResponse current = store.approval(approvalId)
                .orElseThrow(() -> new ResourceNotFoundException("Approval " + approvalId + " was not found."));
        if (!current.status().equals(ApprovalStatus.PENDING.name())) {
            throw new InvalidControlActionException("Approval " + approvalId + " is already decided.");
        }
        String status = approved ? ApprovalStatus.APPROVED.name() : ApprovalStatus.REJECTED.name();
        ControlTowerDtos.ApprovalResponse updated = new ControlTowerDtos.ApprovalResponse(current.approvalId(), current.executionId(), current.action(),
                current.requestedRole(), status, current.createdAt(), LocalDateTime.now(), normalizeNote(note));
        store.saveApproval(updated);
        store.opportunity(current.executionId()).ifPresent(o -> store.saveOpportunity(new ControlTowerDtos.OpportunityResponse(
                o.executionId(), o.repositoryName(), o.opportunityCode(), o.title(), o.confidence(), o.recommended(),
                o.humanApprovalRequired(), approved ? AutomationStatus.READY.name() : AutomationStatus.BLOCKED.name(), o.evidence())));
        store.addHistory(new ControlTowerDtos.HistoryResponse(store.nextAutomationId(), current.executionId(), current.action(), status,
                approved ? "Human approval granted." : "Human approval rejected.", LocalDateTime.now()));
        return updated;
    }

    public synchronized ControlTowerDtos.ExecutionResponse execute(long executionId) {
        ControlTowerDtos.OpportunityResponse opportunity = store.opportunity(executionId)
                .orElseThrow(() -> new ResourceNotFoundException("Execution " + executionId + " has no automation opportunity."));
        if (!opportunity.recommended() || opportunity.status().equals(AutomationStatus.BLOCKED.name())) {
            throw new InvalidControlActionException("Execution " + executionId + " is blocked and cannot be automated.");
        }
        if (opportunity.humanApprovalRequired()) {
            ControlTowerDtos.ApprovalResponse approval = store.approvalForExecution(executionId)
                    .orElseThrow(() -> new InvalidControlActionException("Required approval record is missing."));
            if (!approval.status().equals(ApprovalStatus.APPROVED.name())) {
                throw new InvalidControlActionException("Execution " + executionId + " requires approved human authorization.");
            }
        }
        long automationId = store.nextAutomationId();
        LocalDateTime now = LocalDateTime.now();
        // Safe reference executor: records orchestration success. Replace this boundary with Jenkins/Kubernetes adapter.
        store.addHistory(new ControlTowerDtos.HistoryResponse(automationId, executionId, opportunity.opportunityCode(), "SUCCESS",
                "Control Tower orchestration completed successfully.", now));
        store.saveOpportunity(new ControlTowerDtos.OpportunityResponse(opportunity.executionId(), opportunity.repositoryName(),
                opportunity.opportunityCode(), opportunity.title(), opportunity.confidence(), false,
                opportunity.humanApprovalRequired(), AutomationStatus.SUCCESS.name(), opportunity.evidence()));
        return new ControlTowerDtos.ExecutionResponse(automationId, executionId, true, opportunity.opportunityCode(), "SUCCESS",
                "Automation executed and audit history recorded.", now);
    }

    private String normalizeNote(String note) {
        return note == null || note.isBlank() ? "No decision note supplied." : note.trim();
    }
}
