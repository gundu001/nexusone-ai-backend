package ai.nexusone.service;

import ai.nexusone.dto.orchestrator.*;
import ai.nexusone.enums.OrchestrationDecision;
import ai.nexusone.enums.OrchestrationStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReleaseOrchestratorService {
    private final Map<Long, List<OrchestrationHistoryResponse>> historyStore = new ConcurrentHashMap<>();
    private final AtomicLong orchestrationSequence = new AtomicLong(5700);

    public OrchestratorOverviewResponse getOverview(Long executionId) {
        validateExecutionId(executionId);
        ReleaseAnalysisResponse analysis = buildAnalysis(executionId);
        return new OrchestratorOverviewResponse(executionId, "AI Release Orchestrator",
                analysis.getReadinessScore(), analysis.getComplianceScore(),
                analysis.getPredictedSuccessRate(), analysis.getRiskLevel(),
                determineDecision(analysis).name(), historyStore.getOrDefault(executionId, List.of()).size());
    }

    public ReleaseAnalysisResponse analyzeRelease(Long executionId) {
        validateExecutionId(executionId);
        ReleaseAnalysisResponse result = buildAnalysis(executionId);
        addHistory(executionId, "ANALYSIS_COMPLETED", "Release analysis completed.", OrchestrationStatus.COMPLETED.name());
        return result;
    }

    public OrchestrationDecisionResponse executeOrchestration(Long executionId) {
        validateExecutionId(executionId);
        ReleaseAnalysisResponse analysis = buildAnalysis(executionId);
        OrchestrationDecision decision = determineDecision(analysis);
        long id = orchestrationSequence.incrementAndGet();
        int confidence = Math.min(99, Math.max(60, (analysis.getReadinessScore() + analysis.getComplianceScore() + analysis.getPredictedSuccessRate()) / 3));
        String recommendation = switch (decision) {
            case APPROVED -> "SAFE_TO_DEPLOY";
            case APPROVED_WITH_MONITORING -> "DEPLOY_WITH_MONITORING";
            case REJECTED -> "HOLD_RELEASE";
        };
        String message = switch (decision) {
            case APPROVED -> "AI Release Orchestrator approved the release.";
            case APPROVED_WITH_MONITORING -> "Release approved with enhanced monitoring.";
            case REJECTED -> "Release blocked because required thresholds were not met.";
        };
        addHistory(executionId, "ORCHESTRATION_DECISION", message, OrchestrationStatus.COMPLETED.name());
        return new OrchestrationDecisionResponse(id, executionId, decision.name(), confidence,
                recommendation, analysis.getPredictedSuccessRate(), true, OffsetDateTime.now(), message);
    }

    public List<OrchestrationHistoryResponse> getHistory(Long executionId) {
        validateExecutionId(executionId);
        List<OrchestrationHistoryResponse> items = new ArrayList<>(historyStore.getOrDefault(executionId, List.of()));
        items.sort(Comparator.comparing(OrchestrationHistoryResponse::getTimestamp).reversed());
        return items;
    }

    public List<ReleaseFlowResponse> getFlows() {
        return List.of(
            new ReleaseFlowResponse(1L, "STANDARD_RELEASE", "Standard validation and deployment flow", 5, true,
                    List.of("READINESS", "COMPLIANCE", "RISK", "DECISION", "DEPLOYMENT")),
            new ReleaseFlowResponse(2L, "HIGH_RISK_RELEASE", "Extended validation with approval gate", 7, true,
                    List.of("READINESS", "COMPLIANCE", "RISK", "SECURITY", "APPROVAL", "DECISION", "DEPLOYMENT")),
            new ReleaseFlowResponse(3L, "EMERGENCY_RELEASE", "Accelerated flow with mandatory audit trail", 5, true,
                    List.of("IMPACT", "APPROVAL", "DECISION", "DEPLOYMENT", "AUDIT"))
        );
    }

    public List<WorkflowDefinitionResponse> getWorkflows() {
        return List.of(
            new WorkflowDefinitionResponse(1001L, "AI Safe Deployment Workflow", "Release Request", "ACTIVE", 1L),
            new WorkflowDefinitionResponse(1002L, "High Risk Governance Workflow", "High Risk Detected", "ACTIVE", 2L),
            new WorkflowDefinitionResponse(1003L, "Emergency Release Workflow", "Critical Incident", "ACTIVE", 3L)
        );
    }

    public WorkflowStartResponse startWorkflow(Long executionId) {
        validateExecutionId(executionId);
        ReleaseAnalysisResponse analysis = buildAnalysis(executionId);
        long workflowId = "HIGH".equals(analysis.getRiskLevel()) ? 1002L : 1001L;
        String workflowName = workflowId == 1002L ? "High Risk Governance Workflow" : "AI Safe Deployment Workflow";
        long orchestrationId = orchestrationSequence.incrementAndGet();
        addHistory(executionId, "WORKFLOW_STARTED", workflowName + " started.", OrchestrationStatus.STARTED.name());
        return new WorkflowStartResponse(orchestrationId, executionId, workflowId, workflowName,
                OrchestrationStatus.STARTED.name(), "Governance Validation", 1, 5,
                OffsetDateTime.now(), "AI release orchestration initiated successfully.");
    }

    private ReleaseAnalysisResponse buildAnalysis(Long executionId) {
        // Replace these deterministic values with calls to your Phase 5.3, 5.4 and 5.6 services/repositories.
        int readiness = 88 + (int) (executionId % 8);
        int compliance = 86 + (int) (executionId % 10);
        int success = 84 + (int) (executionId % 11);
        int failure = 100 - success;
        int rollback = Math.max(2, failure - 3);
        String risk = success >= 90 ? "LOW" : success >= 75 ? "MEDIUM" : "HIGH";
        List<String> findings = new ArrayList<>();
        if (readiness < 90) findings.add("Readiness score requires review.");
        if (compliance < 90) findings.add("Compliance score requires review.");
        if (findings.isEmpty()) findings.add("All orchestration gates meet target thresholds.");
        return new ReleaseAnalysisResponse(executionId, readiness, compliance, success, failure,
                rollback, risk, true, findings, OffsetDateTime.now());
    }

    private OrchestrationDecision determineDecision(ReleaseAnalysisResponse a) {
        if (a.getComplianceScore() >= 90 && a.getReadinessScore() >= 90 && a.getPredictedSuccessRate() >= 85 && a.isAutomationSuccessful())
            return OrchestrationDecision.APPROVED;
        if (a.getComplianceScore() >= 75 && a.getReadinessScore() >= 75 && a.getPredictedSuccessRate() >= 70)
            return OrchestrationDecision.APPROVED_WITH_MONITORING;
        return OrchestrationDecision.REJECTED;
    }

    private void addHistory(Long executionId, String action, String details, String status) {
        OrchestrationHistoryResponse item = new OrchestrationHistoryResponse(
                orchestrationSequence.incrementAndGet(), executionId, action, status, details, OffsetDateTime.now());
        historyStore.computeIfAbsent(executionId, key -> Collections.synchronizedList(new ArrayList<>())).add(item);
    }

    private void validateExecutionId(Long executionId) {
        if (executionId == null || executionId <= 0) throw new IllegalArgumentException("executionId must be greater than zero");
    }
}
