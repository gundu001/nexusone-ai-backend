package ai.nexusone.service;

import ai.nexusone.dto.request.AgentExecutionRequest;
import ai.nexusone.dto.response.AgenticEnterpriseOverviewResponse;
import ai.nexusone.entity.AgentExecution;
import ai.nexusone.repository.AgentExecutionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Transactional
public class AgenticEnterpriseService {
    private static final Set<String> AGENT_TYPES = Set.of(
            "SRE_AGENT", "RELEASE_AGENT", "FINOPS_AGENT", "GOVERNANCE_AGENT", "EXECUTIVE_AGENT"
    );
    private static final Set<String> RISK_LEVELS = Set.of("LOW", "MEDIUM", "HIGH", "CRITICAL");

    private final AgentExecutionRepository repository;

    public AgenticEnterpriseService(AgentExecutionRepository repository) {
        this.repository = repository;
    }

    public AgentExecution execute(AgentExecutionRequest request) {
        String agentType = normalized(request.agentType());
        String riskLevel = normalized(request.riskLevel());
        validate(agentType, riskLevel);

        boolean approvalRequired = request.approvalRequired() || "HIGH".equals(riskLevel) || "CRITICAL".equals(riskLevel);
        boolean confidenceReady = request.confidenceScore() >= 80;
        boolean healthReady = request.targetHealthScore() >= 75;
        boolean automated = !approvalRequired && confidenceReady && healthReady && !"CRITICAL".equals(riskLevel);

        String approvalStatus = approvalRequired ? "PENDING_APPROVAL" : "NOT_REQUIRED";
        String executionStatus;
        String decision;
        String result;

        if ("CRITICAL".equals(riskLevel)) {
            executionStatus = "BLOCKED";
            decision = "ESCALATE_FOR_EXECUTIVE_APPROVAL";
            result = "Execution blocked because the proposed action has critical risk.";
        } else if (approvalRequired) {
            executionStatus = "PENDING_APPROVAL";
            decision = "REQUIRE_GOVERNED_APPROVAL";
            result = "Execution is pending governed approval. No infrastructure action was performed.";
        } else if (!confidenceReady) {
            executionStatus = "RECOMMENDATION_ONLY";
            decision = "INCREASE_AGENT_CONFIDENCE";
            result = "Confidence is below the autonomous execution threshold. Recommendation recorded only.";
        } else if (!healthReady) {
            executionStatus = "RECOMMENDATION_ONLY";
            decision = "IMPROVE_TARGET_HEALTH";
            result = "Target health is below the safe execution threshold. Recommendation recorded only.";
        } else {
            executionStatus = "SIMULATED_SUCCESS";
            decision = "PROCEED_WITH_GOVERNED_AUTOMATION";
            result = "Approved simulation completed successfully. Verify through an authenticated execution adapter before production use.";
        }

        AgentExecution execution = new AgentExecution();
        execution.setAgentType(agentType);
        execution.setApplicationName(request.applicationName().trim());
        execution.setEnvironment(request.environment().trim().toLowerCase(Locale.ROOT));
        execution.setProposedAction(normalized(request.proposedAction()));
        execution.setRiskLevel(riskLevel);
        execution.setApprovalRequired(approvalRequired);
        execution.setConfidenceScore(request.confidenceScore());
        execution.setTargetHealthScore(request.targetHealthScore());
        execution.setAutomated(automated);
        execution.setExecutionStatus(executionStatus);
        execution.setApprovalStatus(approvalStatus);
        execution.setRecommendedDecision(decision);
        execution.setExecutionResult(result);
        execution.setRecommendation(recommendation(agentType, decision, execution.getProposedAction()));
        execution.setVerificationPlan("Capture pre-action health; perform dry run; execute through an authenticated approved adapter; verify SLO, logs and target health; record evidence; rollback when verification fails.");
        return repository.save(execution);
    }

    @Transactional(readOnly = true)
    public AgenticEnterpriseOverviewResponse overview() {
        List<AgentExecution> all = repository.findAll();
        long total = all.size();
        long successful = repository.countByExecutionStatus("SIMULATED_SUCCESS");
        long failed = repository.countByExecutionStatus("FAILED") + repository.countByExecutionStatus("BLOCKED");
        long pending = repository.countByApprovalStatus("PENDING_APPROVAL");
        double automationCoverage = total == 0 ? 0 : round(repository.countByAutomatedTrue() * 100.0 / total);
        double confidence = all.stream().mapToDouble(AgentExecution::getConfidenceScore).average().orElse(0);
        String status = total == 0 ? "NO_DATA" : failed > 0 ? "ATTENTION_REQUIRED" :
                pending > 0 ? "APPROVALS_PENDING" : "AGENTIC_PLATFORM_READY";
        return new AgenticEnterpriseOverviewResponse(total, successful, failed, pending,
                automationCoverage, round(confidence), status);
    }

    @Transactional(readOnly = true)
    public Page<AgentExecution> history(Pageable pageable) { return repository.findAll(pageable); }

    @Transactional(readOnly = true)
    public List<AgentExecution> recommendations() { return repository.findTop10ByOrderByCreatedAtDesc(); }

    @Transactional(readOnly = true)
    public AgentExecution get(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Agent execution not found: " + id));
    }

    private void validate(String agentType, String riskLevel) {
        if (!AGENT_TYPES.contains(agentType)) {
            throw new IllegalArgumentException("Unsupported agentType. Allowed values: " + AGENT_TYPES);
        }
        if (!RISK_LEVELS.contains(riskLevel)) {
            throw new IllegalArgumentException("Unsupported riskLevel. Allowed values: " + RISK_LEVELS);
        }
    }

    private String recommendation(String agentType, String decision, String action) {
        if ("REQUIRE_GOVERNED_APPROVAL".equals(decision)) {
            return "Review evidence, assign an approver and authorize " + action + " before execution.";
        }
        if ("ESCALATE_FOR_EXECUTIVE_APPROVAL".equals(decision)) {
            return "Escalate the critical-risk action to governance and executive owners before execution.";
        }
        if ("INCREASE_AGENT_CONFIDENCE".equals(decision)) {
            return "Collect additional operational evidence before authorizing autonomous execution.";
        }
        if ("IMPROVE_TARGET_HEALTH".equals(decision)) {
            return "Stabilize the target application and repeat readiness checks before execution.";
        }
        return switch (agentType) {
            case "SRE_AGENT" -> "Execute the approved reliability action and verify SLO and service health.";
            case "RELEASE_AGENT" -> "Apply the approved release decision with canary checks and rollback readiness.";
            case "FINOPS_AGENT" -> "Apply the verified cost action and measure realized savings without reducing reliability.";
            case "GOVERNANCE_AGENT" -> "Record approval evidence, policy checks and an immutable audit outcome.";
            case "EXECUTIVE_AGENT" -> "Record the strategic decision, accountable owner and measurable business outcome.";
            default -> "Proceed through governed automation and verify the outcome.";
        };
    }

    private static String normalized(String value) {
        return value.trim().toUpperCase(Locale.ROOT).replace(' ', '_').replace('-', '_');
    }

    private static double round(double value) { return Math.round(value * 100.0) / 100.0; }
}
