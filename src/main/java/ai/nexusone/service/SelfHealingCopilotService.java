package ai.nexusone.service;

import ai.nexusone.dto.request.*;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.SelfHealingExecution;
import ai.nexusone.repository.SelfHealingExecutionRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class SelfHealingCopilotService {
    private final SelfHealingExecutionRepository repository;
    private final Map<String, SelfHealingCandidateResponse> candidates;

    public SelfHealingCopilotService(SelfHealingExecutionRepository repository) {
        this.repository=repository; this.candidates=seed();
    }
    public SelfHealingOverviewResponse overview(){
        long high=candidates.values().stream().filter(c->c.confidence()>=0.90).count();
        return new SelfHealingOverviewResponse(candidates.size(), candidates.size(), high, repository.count());
    }
    public List<SelfHealingCandidateResponse> candidates(){ return new ArrayList<>(candidates.values()); }
    public SelfHealingPlanResponse plan(String application){
        return build(candidate(application), "Generate a safe self-healing plan.");
    }
    public SelfHealingPlanResponse ask(SelfHealingQueryRequest request){
        return build(candidate(request.application()), request.question());
    }
    public SelfHealingExecutionResponse execute(SelfHealingExecuteRequest request){
        SelfHealingCandidateResponse c=candidate(request.application());
        String expected=c.recommendedAction();
        if(!expected.equalsIgnoreCase(request.action().trim()))
            throw new IllegalArgumentException("Unsupported action for "+request.application()+": "+request.action());
        String status=request.dryRun()?"DRY_RUN_VALIDATED":"SIMULATED_SUCCESS";
        String result=request.dryRun()
                ? "Safety checks passed. No infrastructure change was executed."
                : "Demo remediation workflow completed and post-action validation passed.";
        SelfHealingExecution saved=repository.save(new SelfHealingExecution(c.application(), expected, request.dryRun(), status, result));
        return new SelfHealingExecutionResponse(saved.getId(), saved.getApplication(), saved.getAction(),
                saved.isDryRun(), saved.getStatus(), saved.getResult(), saved.getExecutedAt());
    }
    public Page<SelfHealingExecution> history(int page,int size){ return repository.findAllByOrderByExecutedAtDesc(pageable(page,size)); }
    public Page<SelfHealingExecution> historyByApplication(String app,int page,int size){
        candidate(app); return repository.findByApplicationOrderByExecutedAtDesc(app,pageable(page,size));
    }
    private PageRequest pageable(int page,int size){return PageRequest.of(Math.max(0,page),Math.min(100,Math.max(1,size)),Sort.by("executedAt").descending());}
    private SelfHealingCandidateResponse candidate(String app){
        SelfHealingCandidateResponse c=candidates.get(app); if(c==null) throw new NoSuchElementException("Application not found: "+app); return c;
    }
    private SelfHealingPlanResponse build(SelfHealingCandidateResponse c,String q){
        if("checkout-service".equals(c.application())) return response(c,q,"Connection demand exceeds safe pool capacity",
                "The safest response is controlled traffic reduction followed by connection-pool validation.",
                List.of("DB pool utilization is 94%","Retry volume is increasing","5xx trend is rising"),
                List.of("Enable temporary request throttling","Reduce retry concurrency","Validate database recovery"),
                List.of("5xx rate returns below threshold","Pool utilization falls below 75%","Checkout smoke test passes"),
                List.of("Remove temporary throttle","Restore previous retry policy","Escalate to database owner"));
        if("order-worker".equals(c.application())) return response(c,q,"Worker capacity is below queue arrival rate",
                "A guarded worker scale-out can reduce queue lag without changing application code.",
                List.of("Queue lag exceeded threshold","CPU is above 82%","HPA is near maximum"),
                List.of("Raise approved replica ceiling","Scale workers by two replicas","Monitor queue drain rate"),
                List.of("Queue lag decreases","Error rate remains stable","CPU falls below 70%"),
                List.of("Restore previous replica ceiling","Scale down added replicas","Escalate capacity review"));
        return response(c,q,"Required staging configuration is missing",
                "Restore the validated key and restart only the unhealthy staging workload.",
                List.of("Readiness probe failed","Manifest drift detected","Required key is absent"),
                List.of("Restore the validated configuration key","Restart the affected deployment","Run readiness checks"),
                List.of("All pods become ready","Smoke tests pass","Configuration drift clears"),
                List.of("Restore previous manifest","Roll back the deployment","Open configuration incident"));
    }
    private SelfHealingPlanResponse response(SelfHealingCandidateResponse c,String q,String diagnosis,String summary,
            List<String> evidence,List<String> steps,List<String> checks,List<String> rollback){
        return new SelfHealingPlanResponse(c.application(),q,diagnosis,c.recommendedAction(),summary,c.confidence(),
                c.riskLevel(),evidence,steps,checks,rollback,Instant.now());
    }
    private Map<String,SelfHealingCandidateResponse> seed(){
        Map<String,SelfHealingCandidateResponse> m=new LinkedHashMap<>();
        m.put("checkout-service",new SelfHealingCandidateResponse("HEAL-6701","checkout-service","production","Database pool saturation","Apply guarded traffic throttling",0.94,"CRITICAL","READY_FOR_APPROVAL",Instant.parse("2026-09-18T11:00:00Z"),List.of("DB pool 94%","Retry volume +38%","5xx trend rising")));
        m.put("order-worker",new SelfHealingCandidateResponse("HEAL-6702","order-worker","production","Queue backlog growth","Scale workers by two replicas",0.88,"HIGH","PLAN_READY",Instant.parse("2026-09-18T10:55:00Z"),List.of("Queue lag increasing","CPU above 82%","HPA near maximum")));
        m.put("customer-api",new SelfHealingCandidateResponse("HEAL-6703","customer-api","staging","Readiness configuration drift","Restore validated configuration and restart",0.96,"CRITICAL","READY_FOR_APPROVAL",Instant.parse("2026-09-18T10:50:00Z"),List.of("Required key missing","Probe failed","Manifest drift detected")));
        return m;
    }
}
