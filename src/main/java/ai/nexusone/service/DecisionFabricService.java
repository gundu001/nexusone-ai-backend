package ai.nexusone.service;

import ai.nexusone.dto.request.DecisionApprovalRequest;
import ai.nexusone.dto.request.DecisionFabricRequest;
import ai.nexusone.dto.response.DecisionFabricAnalyticsResponse;
import ai.nexusone.dto.response.DecisionFabricOverviewResponse;
import ai.nexusone.entity.EnterpriseDecision;
import ai.nexusone.repository.EnterpriseDecisionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class DecisionFabricService {
    private static final Set<String> OUTCOMES = Set.of("APPROVE", "REJECT");
    private final EnterpriseDecisionRepository repository;
    public DecisionFabricService(EnterpriseDecisionRepository repository){this.repository=repository;}

    public EnterpriseDecision evaluate(DecisionFabricRequest r){
        EnterpriseDecision d=new EnterpriseDecision();
        d.setDecisionName(r.decisionName().trim()); d.setApplicationName(r.applicationName().trim());
        d.setEnvironment(norm(r.environment())); d.setDecisionType(norm(r.decisionType())); d.setSourceAgent(norm(r.sourceAgent()));
        d.setConfidenceScore(round(r.confidenceScore())); d.setRiskScore(r.riskScore()); d.setPolicyComplianceScore(r.policyComplianceScore());
        boolean blocked=r.riskScore()>=80 || r.policyComplianceScore()<60;
        boolean approval=!blocked && (r.approvalRequired() || r.riskScore()>=50 || r.confidenceScore()<85);
        d.setApprovalRequired(approval);
        if(blocked){ d.setStatus("BLOCKED"); d.setRecommendedDecision("DO_NOT_EXECUTE"); d.setRationale("Decision blocked because enterprise risk or policy compliance crossed the configured safety threshold."); d.setActionPlan("Reduce risk, resolve policy violations, collect evidence and submit a new decision evaluation."); }
        else if(approval){ d.setStatus("PENDING_APPROVAL"); d.setRecommendedDecision("REQUIRE_GOVERNED_APPROVAL"); d.setRationale("Decision requires a human governance checkpoint before execution."); d.setActionPlan("Review evidence, confirm accountable owner, approve or reject, then record the governed outcome."); }
        else { d.setStatus("APPROVED_AUTONOMOUSLY"); d.setRecommendedDecision("PROCEED_WITH_GOVERNED_EXECUTION"); d.setRationale("Confidence, risk and policy compliance satisfy the configured autonomous decision thresholds."); d.setActionPlan("Execute through the approved adapter, verify the outcome and preserve audit evidence."); d.setDecidedAt(LocalDateTime.now()); }
        return repository.save(d);
    }

    public EnterpriseDecision decide(Long id, DecisionApprovalRequest r){
        EnterpriseDecision d=get(id); if(!"PENDING_APPROVAL".equals(d.getStatus())) throw new IllegalStateException("Only PENDING_APPROVAL decisions can be approved or rejected.");
        String outcome=norm(r.outcome()); if(!OUTCOMES.contains(outcome)) throw new IllegalArgumentException("Outcome must be APPROVE or REJECT.");
        d.setApprovedBy(r.approvedBy().trim()); d.setApprovalComment(r.comment()==null?null:r.comment().trim()); d.setApprovedAt(LocalDateTime.now()); d.setDecidedAt(LocalDateTime.now());
        if("APPROVE".equals(outcome)){d.setStatus("APPROVED"); d.setRecommendedDecision("PROCEED_WITH_GOVERNED_EXECUTION");}
        else {d.setStatus("REJECTED"); d.setRecommendedDecision("DO_NOT_EXECUTE");}
        return repository.save(d);
    }
    @Transactional(readOnly=true) public EnterpriseDecision get(Long id){return repository.findById(id).orElseThrow(()->new NoSuchElementException("Decision not found: "+id));}
    @Transactional(readOnly=true) public Page<EnterpriseDecision> history(Pageable p){return repository.findAll(p);}
    @Transactional(readOnly=true) public List<EnterpriseDecision> pendingApprovals(){return repository.findByStatusOrderByCreatedAtDesc("PENDING_APPROVAL");}
    @Transactional(readOnly=true) public DecisionFabricOverviewResponse overview(){
        List<EnterpriseDecision> all=repository.findAll(); long total=all.size(), approved=all.stream().filter(x->Set.of("APPROVED","APPROVED_AUTONOMOUSLY").contains(x.getStatus())).count(); long pending=repository.countByStatus("PENDING_APPROVAL"), blocked=repository.countByStatus("BLOCKED");
        double confidence=avg(all.stream().map(EnterpriseDecision::getConfidenceScore).toList()); double policy=avg(all.stream().map(x->x.getPolicyComplianceScore().doubleValue()).toList());
        String status=total==0?"NO_DATA":blocked>0?"ATTENTION_REQUIRED":pending>0?"APPROVALS_PENDING":"FABRIC_READY";
        return new DecisionFabricOverviewResponse(total,approved,pending,blocked,confidence,policy,status);
    }
    @Transactional(readOnly=true) public DecisionFabricAnalyticsResponse analytics(){
        List<EnterpriseDecision> all=repository.findAll();
        long autonomous=repository.countByStatus("APPROVED_AUTONOMOUSLY"), approved=repository.countByStatus("APPROVED"), rejected=repository.countByStatus("REJECTED"), blocked=repository.countByStatus("BLOCKED"), governed=approved+rejected+repository.countByStatus("PENDING_APPROVAL");
        return new DecisionFabricAnalyticsResponse(all.size(),autonomous,governed,approved,rejected,blocked,avg(all.stream().map(EnterpriseDecision::getConfidenceScore).toList()),avg(all.stream().map(x->x.getRiskScore().doubleValue()).toList()),avg(all.stream().map(x->x.getPolicyComplianceScore().doubleValue()).toList()));
    }
    private static String norm(String v){return v.trim().toUpperCase(Locale.ROOT).replace(' ','_').replace('-','_');}
    private static double round(double v){return Math.round(v*100.0)/100.0;}
    private static double avg(List<Double> v){return round(v.stream().mapToDouble(Double::doubleValue).average().orElse(0));}
}
