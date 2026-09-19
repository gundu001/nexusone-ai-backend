package ai.nexusone.service;

import ai.nexusone.dto.request.*; import ai.nexusone.dto.response.*; import ai.nexusone.entity.*; import ai.nexusone.repository.*;
import jakarta.transaction.Transactional; import org.springframework.stereotype.Service;
import java.time.LocalDateTime; import java.util.List;

@Service
public class CrossCloudFailoverService {
 private final ResiliencePlanRepository plans; private final CloudFailoverExecutionRepository executions;
 public CrossCloudFailoverService(ResiliencePlanRepository p, CloudFailoverExecutionRepository e){plans=p;executions=e;}
 public FailoverOverviewResponse overview(){var all=plans.findAll();var ex=executions.findAll();return new FailoverOverviewResponse(all.size(),all.stream().filter(p->"READY".equals(p.getStatus())).count(),ex.size(),ex.stream().filter(x->"SUCCESS".equals(x.getStatus())).count(),all.stream().mapToDouble(ResiliencePlan::getReadinessScore).average().orElse(0));}
 public List<ResiliencePlanResponse> getPlans(){return plans.findAll().stream().map(this::planResponse).toList();}
 public List<FailoverExecutionResponse> getExecutions(){return executions.findTop20ByOrderByStartedAtDesc().stream().map(this::executionResponse).toList();}
 @Transactional public ResiliencePlanResponse createPlan(ResiliencePlanRequest r){
  if(r.primaryProvider().equalsIgnoreCase(r.secondaryProvider())) throw new IllegalArgumentException("Primary and secondary providers must be different.");
  var p=new ResiliencePlan();p.setApplicationName(r.applicationName());p.setEnvironment(r.environment());p.setPrimaryProvider(r.primaryProvider().toUpperCase());p.setPrimaryRegion(r.primaryRegion());p.setSecondaryProvider(r.secondaryProvider().toUpperCase());p.setSecondaryRegion(r.secondaryRegion());p.setRtoMinutes(r.rtoMinutes());p.setRpoMinutes(r.rpoMinutes());
  double score=Math.max(60, Math.min(98, 100-(r.rtoMinutes()*.35)-(r.rpoMinutes()*.20)));p.setReadinessScore(Math.round(score*100.0)/100.0);p.setStatus(score>=80?"READY":"REVIEW_REQUIRED");return planResponse(plans.save(p));
 }
 @Transactional public FailoverExecutionResponse simulate(FailoverActionRequest r){return action(r,"SIMULATION",true);}
 @Transactional public FailoverExecutionResponse execute(FailoverActionRequest r){return action(r,"FAILOVER",false);}
 @Transactional public FailoverExecutionResponse rollback(FailoverActionRequest r){return action(r,"ROLLBACK",false);}
 private FailoverExecutionResponse action(FailoverActionRequest r,String action,boolean dryRun){
  var p=plans.findById(r.planId()).orElseThrow(()->new IllegalArgumentException("Resilience plan not found: "+r.planId()));
  if(!dryRun && !"READY".equals(p.getStatus())) throw new IllegalStateException("Plan must be READY before execution.");
  var x=new CloudFailoverExecution();x.setPlanId(p.getId());x.setApplicationName(p.getApplicationName());x.setAction(action);x.setDryRun(dryRun);x.setInitiatedBy(r.initiatedBy());x.setStartedAt(LocalDateTime.now());
  boolean rollback="ROLLBACK".equals(action);x.setSourceProvider(rollback?p.getSecondaryProvider():p.getPrimaryProvider());x.setTargetProvider(rollback?p.getPrimaryProvider():p.getSecondaryProvider());
  x.setStatus(dryRun?"SIMULATED":"SUCCESS");x.setMessage(dryRun?"Validation simulation completed. No cloud infrastructure was changed.":"Orchestration record completed. Connect a provider adapter before enabling real infrastructure changes.");x.setCompletedAt(LocalDateTime.now());return executionResponse(executions.save(x));
 }
 private ResiliencePlanResponse planResponse(ResiliencePlan p){return new ResiliencePlanResponse(p.getId(),p.getApplicationName(),p.getEnvironment(),p.getPrimaryProvider(),p.getPrimaryRegion(),p.getSecondaryProvider(),p.getSecondaryRegion(),p.getRtoMinutes(),p.getRpoMinutes(),p.getReadinessScore(),p.getStatus(),p.getCreatedAt());}
 private FailoverExecutionResponse executionResponse(CloudFailoverExecution x){return new FailoverExecutionResponse(x.getId(),x.getPlanId(),x.getApplicationName(),x.getSourceProvider(),x.getTargetProvider(),x.getAction(),x.getStatus(),x.isDryRun(),x.getInitiatedBy(),x.getMessage(),x.getStartedAt(),x.getCompletedAt());}
}
