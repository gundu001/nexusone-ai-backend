package ai.nexusone.service;

import ai.nexusone.dto.request.*;
import ai.nexusone.dto.response.AutonomousOperationsOverviewResponse;
import ai.nexusone.entity.AutonomousOperation;
import ai.nexusone.repository.AutonomousOperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AutonomousOperationsService {
    private final AutonomousOperationRepository repository;
    public AutonomousOperationsService(AutonomousOperationRepository repository){this.repository=repository;}

    public AutonomousOperation analyze(AutonomousOperationRequest r){
        AutonomousOperation x=new AutonomousOperation();
        x.setApplicationName(r.applicationName()); x.setEnvironment(r.environment()); x.setSignalType(r.signalType());
        x.setSeverityScore(r.severityScore()); x.setConfidenceScore(r.confidenceScore());
        x.setCurrentCpuPercent(r.currentCpuPercent()); x.setCurrentMemoryPercent(r.currentMemoryPercent());
        x.setErrorRatePercent(r.errorRatePercent()); x.setResponseTimeMs(r.responseTimeMs());
        String action=decideAction(r); x.setRecommendedAction(action);
        String risk=r.severityScore()>=80?"HIGH":r.severityScore()>=50?"MEDIUM":"LOW"; x.setRiskLevel(risk);
        String mode="LOW".equals(risk)&&r.confidenceScore()>=90?"AUTO":"APPROVAL_REQUIRED"; x.setExecutionMode(mode);
        x.setStatus("AUTO".equals(mode)?"APPROVED":"PENDING_APPROVAL");
        x.setReasoning(reason(r, action));
        x.setExecutionPlan(plan(action));
        x.setRollbackPlan(rollback(action));
        return repository.save(x);
    }

    public AutonomousOperation approve(Long id, OperationApprovalRequest r){
        AutonomousOperation x=get(id);
        if(!"PENDING_APPROVAL".equals(x.getStatus())) throw new IllegalArgumentException("Only PENDING_APPROVAL actions can be approved");
        x.setStatus("APPROVED"); x.setApprovedBy(r.approvedBy()); x.setApprovalComments(r.comments()); x.setApprovedAt(LocalDateTime.now());
        return repository.save(x);
    }

    public AutonomousOperation execute(Long id, OperationExecutionRequest r){
        AutonomousOperation x=get(id);
        if(!"APPROVED".equals(x.getStatus())) throw new IllegalArgumentException("Action must be APPROVED before execution");
        x.setExecutedBy(r.executedBy()); x.setDryRun(r.dryRun()); x.setExecutedAt(LocalDateTime.now());
        x.setStatus(r.dryRun()?"DRY_RUN_COMPLETED":"EXECUTED");
        return repository.save(x);
    }

    @Transactional(readOnly=true) public AutonomousOperationsOverviewResponse overview(){
        var all=repository.findAll();
        double avg=all.stream().mapToInt(AutonomousOperation::getConfidenceScore).average().orElse(0);
        long pending=repository.countByStatus("PENDING_APPROVAL");
        long approved=repository.countByStatus("APPROVED");
        long executed=repository.countByStatus("EXECUTED")+repository.countByStatus("DRY_RUN_COMPLETED");
        long failed=repository.countByStatus("FAILED");
        String state=failed>0?"ATTENTION_REQUIRED":pending>0?"GOVERNED_ACTION_PENDING":"STABLE";
        return new AutonomousOperationsOverviewResponse(all.size(),pending,approved,executed,failed,Math.round(avg*100.0)/100.0,state);
    }
    @Transactional(readOnly=true) public Page<AutonomousOperation> history(Pageable p){return repository.findAll(p);}
    @Transactional(readOnly=true) public AutonomousOperation get(Long id){return repository.findById(id).orElseThrow(()->new NoSuchElementException("Autonomous operation not found: "+id));}

    private String decideAction(AutonomousOperationRequest r){
        if(r.errorRatePercent()>=5) return "ROLLBACK_DEPLOYMENT";
        if(r.currentCpuPercent()>=85||r.currentMemoryPercent()>=90) return "SCALE_OUT";
        if(r.responseTimeMs()>=1500) return "RESTART_SERVICE";
        return "CONTINUE_MONITORING";
    }
    private String reason(AutonomousOperationRequest r,String action){return "Action "+action+" selected from signal "+r.signalType()+", severity "+r.severityScore()+", confidence "+r.confidenceScore()+".";}
    private String plan(String action){return switch(action){case "ROLLBACK_DEPLOYMENT"->"Validate target version; drain traffic; rollback; verify health; restore traffic.";case "SCALE_OUT"->"Validate capacity; add replicas; verify readiness; rebalance traffic; monitor saturation.";case "RESTART_SERVICE"->"Capture diagnostics; restart one instance; verify health; continue rolling restart if required.";default->"Continue monitoring and reassess when a threshold is crossed.";};}
    private String rollback(String action){return switch(action){case "SCALE_OUT"->"Return replica count to the previous value after validation.";case "ROLLBACK_DEPLOYMENT"->"Redeploy the prior known-good version if rollback validation fails.";case "RESTART_SERVICE"->"Stop rolling restart and route traffic to healthy instances.";default->"No operational rollback required.";};}
}
