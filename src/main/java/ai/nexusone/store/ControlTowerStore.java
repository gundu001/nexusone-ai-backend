package ai.nexusone.store;

import ai.nexusone.dto.ControlTowerDtos;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ControlTowerStore {
    private final Map<Long, ControlTowerDtos.OpportunityResponse> opportunities = new ConcurrentHashMap<>();
    private final Map<Long, ControlTowerDtos.ApprovalResponse> approvals = new ConcurrentHashMap<>();
    private final List<ControlTowerDtos.HistoryResponse> history = new CopyOnWriteArrayList<>();
    private final AtomicLong approvalSequence = new AtomicLong(1000);
    private final AtomicLong automationSequence = new AtomicLong(5000);

    public List<ControlTowerDtos.OpportunityResponse> opportunities() {
        return opportunities.values().stream()
                .sorted(Comparator.comparingInt(ControlTowerDtos.OpportunityResponse::confidence).reversed())
                .toList();
    }
    public Optional<ControlTowerDtos.OpportunityResponse> opportunity(long executionId) {
        return Optional.ofNullable(opportunities.get(executionId));
    }
    public void saveOpportunity(ControlTowerDtos.OpportunityResponse value) { opportunities.put(value.executionId(), value); }
    public List<ControlTowerDtos.ApprovalResponse> approvals() {
        return approvals.values().stream()
                .sorted(Comparator.comparing(ControlTowerDtos.ApprovalResponse::createdAt).reversed())
                .toList();
    }
    public Optional<ControlTowerDtos.ApprovalResponse> approval(long id) { return Optional.ofNullable(approvals.get(id)); }
    public void saveApproval(ControlTowerDtos.ApprovalResponse value) { approvals.put(value.approvalId(), value); }
    public Optional<ControlTowerDtos.ApprovalResponse> approvalForExecution(long executionId) {
        return approvals.values().stream().filter(a -> a.executionId() == executionId).findFirst();
    }
    public List<ControlTowerDtos.HistoryResponse> history() {
        List<ControlTowerDtos.HistoryResponse> copy = new ArrayList<>(history);
        copy.sort(Comparator.comparing(ControlTowerDtos.HistoryResponse::occurredAt).reversed());
        return copy;
    }
    public void addHistory(ControlTowerDtos.HistoryResponse value) { history.add(value); }
    public long nextApprovalId() { return approvalSequence.incrementAndGet(); }
    public long nextAutomationId() { return automationSequence.incrementAndGet(); }
}
