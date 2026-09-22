package ai.nexusone.service;

import ai.nexusone.adapter.ExecutionAdapter;
import ai.nexusone.domain.*;
import ai.nexusone.dto.*;
import ai.nexusone.repository.ExecutionRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service @RequiredArgsConstructor
public class ExecutionFabricService {
    private final ExecutionRecordRepository repository;
    private final ExecutionAdapter adapter;

    @Transactional
    public ExecutionResponse execute(ExecutionRequest request) {
        return repository.findByIdempotencyKey(request.idempotencyKey())
            .map(this::toResponse)
            .orElseGet(() -> executeNew(request));
    }

    private ExecutionResponse executeNew(ExecutionRequest r) {
        validatePolicy(r);
        ExecutionRecord record = ExecutionRecord.builder()
            .decisionId(r.decisionId()).executionName(r.executionName())
            .applicationName(r.applicationName()).environment(r.environment().toUpperCase())
            .actionType(r.actionType()).targetType(r.targetType().toUpperCase())
            .targetName(r.targetName()).requestedBy(r.requestedBy())
            .approvalReference(r.approvalReference()).idempotencyKey(r.idempotencyKey())
            .parameters(r.parameters()).status(ExecutionStatus.RUNNING).progressPercentage(10)
            .startedAt(LocalDateTime.now()).build();
        record = repository.save(record);
        ExecutionAdapter.AdapterResult result = adapter.execute(record);
        record.setProgressPercentage(100);
        record.setCompletedAt(LocalDateTime.now());
        record.setStatus(result.successful() ? ExecutionStatus.SUCCEEDED : ExecutionStatus.FAILED);
        if (result.successful()) record.setResultMessage(result.message());
        else record.setErrorMessage(result.message());
        return toResponse(repository.save(record));
    }

    private void validatePolicy(ExecutionRequest r) {
        if ("PRODUCTION".equalsIgnoreCase(r.environment()) &&
            (r.approvalReference() == null || r.approvalReference().isBlank())) {
            throw new IllegalArgumentException("Production execution requires an approval reference.");
        }
    }

    @Transactional
    public ExecutionResponse rollback(Long id, String requestedBy) {
        ExecutionRecord source = getEntity(id);
        if (source.getStatus() != ExecutionStatus.SUCCEEDED)
            throw new IllegalStateException("Only SUCCEEDED executions can be rolled back.");
        ExecutionRecord rollback = ExecutionRecord.builder()
            .decisionId(source.getDecisionId()).executionName("Rollback: " + source.getExecutionName())
            .applicationName(source.getApplicationName()).environment(source.getEnvironment())
            .actionType(ActionType.ROLLBACK).targetType(source.getTargetType())
            .targetName(source.getTargetName()).requestedBy(requestedBy)
            .approvalReference(source.getApprovalReference())
            .idempotencyKey("rollback-" + source.getId() + "-" + System.nanoTime())
            .parameters(source.getParameters()).parentExecutionId(source.getId())
            .status(ExecutionStatus.RUNNING).progressPercentage(20).startedAt(LocalDateTime.now()).build();
        rollback = repository.save(rollback);
        ExecutionAdapter.AdapterResult result = adapter.rollback(source);
        rollback.setProgressPercentage(100); rollback.setCompletedAt(LocalDateTime.now());
        rollback.setStatus(result.successful() ? ExecutionStatus.ROLLED_BACK : ExecutionStatus.FAILED);
        if (result.successful()) rollback.setResultMessage(result.message()); else rollback.setErrorMessage(result.message());
        return toResponse(repository.save(rollback));
    }

    @Transactional
    public ExecutionResponse retry(Long id, String requestedBy) {
        ExecutionRecord source = getEntity(id);
        if (source.getStatus() != ExecutionStatus.FAILED)
            throw new IllegalStateException("Only FAILED executions can be retried.");
        return execute(new ExecutionRequest(source.getDecisionId(), "Retry: " + source.getExecutionName(),
            source.getApplicationName(), source.getEnvironment(), source.getActionType(), source.getTargetType(),
            source.getTargetName(), requestedBy, source.getApprovalReference(),
            "retry-" + source.getId() + "-" + System.nanoTime(), source.getParameters()));
    }

    @Transactional
    public ExecutionResponse cancel(Long id, String requestedBy) {
        ExecutionRecord record = getEntity(id);
        if (record.getStatus() != ExecutionStatus.PENDING && record.getStatus() != ExecutionStatus.RUNNING)
            throw new IllegalStateException("Only PENDING or RUNNING executions can be cancelled.");
        record.setStatus(ExecutionStatus.CANCELLED);
        record.setCompletedAt(LocalDateTime.now());
        record.setResultMessage("Cancelled by " + requestedBy);
        return toResponse(repository.save(record));
    }

    public ExecutionResponse get(Long id) { return toResponse(getEntity(id)); }
    public Page<ExecutionResponse> history(Pageable pageable) { return repository.findAll(pageable).map(this::toResponse); }

    public ExecutionAnalyticsResponse analytics() {
        long total = repository.count();
        long pending = repository.countByStatus(ExecutionStatus.PENDING);
        long running = repository.countByStatus(ExecutionStatus.RUNNING);
        long success = repository.countByStatus(ExecutionStatus.SUCCEEDED);
        long failed = repository.countByStatus(ExecutionStatus.FAILED);
        long cancelled = repository.countByStatus(ExecutionStatus.CANCELLED);
        long rolledBack = repository.countByStatus(ExecutionStatus.ROLLED_BACK);
        double rate = total == 0 ? 0 : Math.round((success * 10000.0 / total)) / 100.0;
        return new ExecutionAnalyticsResponse(total,pending,running,success,failed,cancelled,rolledBack,rate);
    }

    private ExecutionRecord getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Execution not found: " + id));
    }
    private ExecutionResponse toResponse(ExecutionRecord e) {
        return new ExecutionResponse(e.getId(),e.getDecisionId(),e.getExecutionName(),e.getApplicationName(),
            e.getEnvironment(),e.getActionType(),e.getTargetType(),e.getTargetName(),e.getRequestedBy(),
            e.getApprovalReference(),e.getIdempotencyKey(),e.getParameters(),e.getStatus(),e.getProgressPercentage(),
            e.getResultMessage(),e.getErrorMessage(),e.getParentExecutionId(),e.getStartedAt(),e.getCompletedAt(),
            e.getCreatedAt(),e.getUpdatedAt());
    }
}
