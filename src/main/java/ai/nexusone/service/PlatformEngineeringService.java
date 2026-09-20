package ai.nexusone.service;

import ai.nexusone.dto.request.PlatformEngineeringAssessmentRequest;
import ai.nexusone.dto.response.PlatformEngineeringOverviewResponse;
import ai.nexusone.entity.PlatformEngineeringAssessment;
import ai.nexusone.repository.PlatformEngineeringAssessmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PlatformEngineeringService {
    private final PlatformEngineeringAssessmentRepository repository;
    public PlatformEngineeringService(PlatformEngineeringAssessmentRepository repository){this.repository=repository;}

    public PlatformEngineeringAssessment assess(PlatformEngineeringAssessmentRequest r){
        double resourceScore = 100.0 - ((r.cpuUtilizationPercent()+r.memoryUtilizationPercent()+r.storageUtilizationPercent())/3.0);
        resourceScore = Math.max(0, Math.min(100, resourceScore + 35));
        double workloadScore = Math.max(0, 100 - (r.failedWorkloads()*10.0));
        double latencyScore = Math.max(0, 100 - Math.max(0, r.averageLatencyMs()-200)/10.0);
        int score = (int)Math.round(resourceScore*.25 + r.deploymentSuccessPercent()*.25 + workloadScore*.20 + latencyScore*.10 + r.automationCoveragePercent()*.20);
        String status = score>=85?"HEALTHY":score>=65?"AT_RISK":"CRITICAL";
        String risk = score>=85?"LOW":score>=65?"MEDIUM":"HIGH";
        String action = decideAction(r);

        PlatformEngineeringAssessment x=new PlatformEngineeringAssessment();
        x.setPlatformName(r.platformName()); x.setEnvironment(r.environment()); x.setClusterName(r.clusterName());
        x.setCpuUtilizationPercent(round(r.cpuUtilizationPercent())); x.setMemoryUtilizationPercent(round(r.memoryUtilizationPercent()));
        x.setStorageUtilizationPercent(round(r.storageUtilizationPercent())); x.setDeploymentSuccessPercent(round(r.deploymentSuccessPercent()));
        x.setFailedWorkloads(r.failedWorkloads()); x.setAverageLatencyMs(round(r.averageLatencyMs()));
        x.setAutomationCoveragePercent(round(r.automationCoveragePercent())); x.setPlatformScore(score);
        x.setRiskLevel(risk); x.setStatus(status); x.setRecommendedAction(action);
        x.setRecommendation(recommendation(action)); x.setOptimizationPlan(plan(action));
        return repository.save(x);
    }

    @Transactional(readOnly=true)
    public PlatformEngineeringOverviewResponse overview(){
        List<PlatformEngineeringAssessment> all=repository.findAll();
        double score=all.stream().mapToInt(PlatformEngineeringAssessment::getPlatformScore).average().orElse(0);
        double success=all.stream().mapToDouble(PlatformEngineeringAssessment::getDeploymentSuccessPercent).average().orElse(0);
        double automation=all.stream().mapToDouble(PlatformEngineeringAssessment::getAutomationCoveragePercent).average().orElse(0);
        long healthy=repository.countByStatus("HEALTHY"); long atRisk=repository.countByStatus("AT_RISK"); long critical=repository.countByStatus("CRITICAL");
        String state=all.isEmpty()?"NO_DATA":critical>0?"CRITICAL":atRisk>0?"OPTIMIZATION_REQUIRED":"HEALTHY";
        return new PlatformEngineeringOverviewResponse(all.size(),round(score),round(success),round(automation),healthy,atRisk,critical,state);
    }

    @Transactional(readOnly=true) public Page<PlatformEngineeringAssessment> history(Pageable p){return repository.findAll(p);}
    @Transactional(readOnly=true) public List<PlatformEngineeringAssessment> recommendations(){return repository.findTop10ByOrderByAssessedAtDesc();}
    @Transactional(readOnly=true) public PlatformEngineeringAssessment get(Long id){return repository.findById(id).orElseThrow(()->new NoSuchElementException("Platform assessment not found: "+id));}

    private String decideAction(PlatformEngineeringAssessmentRequest r){
        if(r.failedWorkloads()>=3) return "REPAIR_FAILED_WORKLOADS";
        if(r.cpuUtilizationPercent()>=85||r.memoryUtilizationPercent()>=90) return "SCALE_PLATFORM_CAPACITY";
        if(r.storageUtilizationPercent()>=85) return "EXPAND_OR_CLEAN_STORAGE";
        if(r.deploymentSuccessPercent()<95) return "HARDEN_DEPLOYMENT_PIPELINE";
        if(r.automationCoveragePercent()<70) return "INCREASE_PLATFORM_AUTOMATION";
        if(r.averageLatencyMs()>1000) return "OPTIMIZE_PLATFORM_LATENCY";
        return "CONTINUE_PLATFORM_MONITORING";
    }
    private String recommendation(String a){return switch(a){
        case "REPAIR_FAILED_WORKLOADS"->"Investigate failed workloads, review events and logs, then restore healthy replicas.";
        case "SCALE_PLATFORM_CAPACITY"->"Review requests and limits, validate capacity, and scale the constrained platform tier.";
        case "EXPAND_OR_CLEAN_STORAGE"->"Review storage growth, retention and persistent volumes before capacity is exhausted.";
        case "HARDEN_DEPLOYMENT_PIPELINE"->"Improve deployment checks, readiness validation and rollback automation.";
        case "INCREASE_PLATFORM_AUTOMATION"->"Automate repeatable provisioning, policy checks and operational runbooks.";
        case "OPTIMIZE_PLATFORM_LATENCY"->"Inspect service dependencies, network paths and resource contention.";
        default->"Platform indicators are healthy. Continue monitoring capacity, reliability and automation coverage.";};}
    private String plan(String a){return "1. Validate telemetry; 2. Confirm impact; 3. Review recommendation; 4. Apply through approved platform workflow; 5. Verify health; 6. Record outcome and rollback evidence. Recommended action: "+a;}
    private double round(double v){return Math.round(v*100.0)/100.0;}
}
