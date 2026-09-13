package ai.nexusone.service;

import ai.nexusone.dto.AutonomousActionResponse;
import ai.nexusone.dto.CommandCenterOverviewResponse;
import ai.nexusone.dto.OperationalAlertResponse;
import ai.nexusone.dto.RecommendationResponse;
import ai.nexusone.dto.ReleaseHealthResponse;
import ai.nexusone.dto.ReleasePriorityResponse;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DeliveryCommandCenterService {
    private final DeploymentExecutionRepository repository;

    public DeliveryCommandCenterService(DeploymentExecutionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public CommandCenterOverviewResponse getOverview() {
        List<DeploymentExecutionEntity> all = load();
        long healthy = count(all, DeploymentStatus.SUCCESS);
        long active = all.stream().filter(e -> isActive(e.getStatus())).count();
        long attention = all.size() - healthy;
        List<OperationalAlertResponse> alerts = getOperationalAlerts();
        long critical = alerts.stream().filter(a -> "CRITICAL".equals(a.severity()) || "HIGH".equals(a.severity())).count();
        long pendingActions = getAutonomousActions().stream().filter(a -> !"COMPLETED".equals(a.status())).count();
        double score = all.isEmpty() ? 0.0 : clamp(percentage(healthy, all.size()) + percentage(active, all.size()) * 0.15 - percentage(critical, Math.max(1, alerts.size())) * 0.20);
        String status = all.isEmpty() ? "NO_DATA" : critical > 0 ? "ACTION_REQUIRED" : active > 0 ? "MONITORING" : "STABLE";
        return new CommandCenterOverviewResponse(all.size(), healthy, active, attention, critical, pendingActions, score, status, LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<ReleaseHealthResponse> getReleaseHealth() {
        return load().stream().map(this::health).toList();
    }

    @Transactional(readOnly = true)
    public List<AutonomousActionResponse> getAutonomousActions() {
        List<AutonomousActionResponse> result = new ArrayList<>();
        for (DeploymentExecutionEntity e : load()) {
            if (e.getStatus() == DeploymentStatus.SUCCESS) {
                result.add(action(e, "POST_DEPLOYMENT_VALIDATE", "Run post-deployment validation", "VALIDATE", "LOW", "RECOMMENDED", false, false,
                        "Successful execution is eligible for smoke validation.", "Use the approved smoke-test workflow."));
            } else if (e.getStatus() == DeploymentStatus.FAILED) {
                result.add(action(e, "OPEN_RCA", "Open RCA investigation", "INVESTIGATE", "HIGH", "PENDING_APPROVAL", false, true,
                        "Failed execution requires diagnosis before retry or promotion.", "GET /api/deployment-rca/" + e.getId()));
            } else if (e.getStatus() == DeploymentStatus.ABORTED) {
                result.add(action(e, "REVIEW_ABORT", "Review aborted execution", "REVIEW", "HIGH", "PENDING_APPROVAL", false, true,
                        "Aborted execution requires operator review.", "Review Jenkins audit and timeout history."));
            } else if (isActive(e.getStatus())) {
                result.add(action(e, "MONITOR_EXECUTION", "Monitor active execution", "MONITOR", "MEDIUM", "IN_PROGRESS", true, false,
                        "Execution has not reached a terminal state.", "GET /api/executions/" + e.getId()));
            } else {
                result.add(action(e, "VERIFY_STATE", "Verify unknown execution state", "INVESTIGATE", "HIGH", "PENDING_APPROVAL", false, true,
                        "Execution state is not sufficiently reliable for release action.", "Synchronize the execution from Jenkins."));
            }
        }
        return List.copyOf(result);
    }

    @Transactional(readOnly = true)
    public List<RecommendationResponse> getRecommendations() {
        List<RecommendationResponse> result = new ArrayList<>();
        for (DeploymentExecutionEntity e : load()) {
            if (e.getStatus() == DeploymentStatus.SUCCESS) {
                result.add(recommendation(e, "CONTROLLED_PROMOTION", "RELEASE", "LOW", "Proceed through controlled promotion",
                        "The execution is successful and has a terminal result.", List.of("Run smoke validation.", "Confirm application health.", "Use the approved promotion workflow.")));
            } else if (isActive(e.getStatus())) {
                result.add(recommendation(e, "HOLD_FOR_TERMINAL_RESULT", "OPERATIONS", "MEDIUM", "Hold promotion while execution is active",
                        "A final release decision is premature until the execution completes.", List.of("Continue monitoring.", "Refresh governance and predictive signals after completion.")));
            } else {
                result.add(recommendation(e, "REMEDIATE_BEFORE_PROMOTION", "RELIABILITY", "HIGH", "Remediate before promotion",
                        "The execution is not successful and should not be promoted.", List.of("Review RCA.", "Review self-healing guidance.", "Obtain human approval before retry.")));
            }
        }
        return List.copyOf(result);
    }

    @Transactional(readOnly = true)
    public List<ReleasePriorityResponse> getReleasePriorities() {
        List<ScoredRelease> scored = load().stream().map(e -> new ScoredRelease(e, priorityScore(e))).sorted(Comparator.comparingDouble(ScoredRelease::score).reversed().thenComparing(x -> x.execution().getId(), Comparator.reverseOrder())).toList();
        List<ReleasePriorityResponse> result = new ArrayList<>();
        int rank = 1;
        for (ScoredRelease item : scored) {
            double score = item.score();
            String band = score >= 80 ? "P1" : score >= 60 ? "P2" : score >= 40 ? "P3" : "HOLD";
            String disposition = item.execution().getStatus() == DeploymentStatus.SUCCESS ? "PROMOTE_AFTER_VALIDATION" : isActive(item.execution().getStatus()) ? "MONITOR" : "REMEDIATE";
            result.add(new ReleasePriorityResponse(item.execution().getId(), safe(item.execution().getRepositoryName()), safe(item.execution().getJobName()), score, band, rank++, disposition, rationale(item.execution())));
        }
        return List.copyOf(result);
    }

    @Transactional(readOnly = true)
    public List<OperationalAlertResponse> getOperationalAlerts() {
        List<OperationalAlertResponse> alerts = new ArrayList<>();
        for (DeploymentExecutionEntity e : load()) {
            if (e.getStatus() == DeploymentStatus.FAILED) alerts.add(alert(e, "DEPLOYMENT_FAILED", "CRITICAL", "RELIABILITY", "Deployment failed", "A deployment reached FAILED status.", "Review RCA and block promotion.", true));
            else if (e.getStatus() == DeploymentStatus.ABORTED) alerts.add(alert(e, "DEPLOYMENT_ABORTED", "HIGH", "OPERATIONS", "Deployment aborted", "A deployment ended before successful completion.", "Review abort cause and environment state.", true));
            else if (e.getStatus() == DeploymentStatus.UNKNOWN) alerts.add(alert(e, "STATE_UNKNOWN", "HIGH", "OBSERVABILITY", "Execution state unknown", "The final Jenkins state is unavailable.", "Synchronize Jenkins state before further action.", true));
            else if (isActive(e.getStatus())) alerts.add(alert(e, "ACTIVE_EXECUTION", "MEDIUM", "OPERATIONS", "Execution still active", "A deployment is pending, queued, or running.", "Continue monitoring until terminal completion.", false));
            if (e.getBuildNumber() == null) alerts.add(alert(e, "BUILD_EVIDENCE_MISSING", "MEDIUM", "TRACEABILITY", "Build evidence missing", "Jenkins build number is not recorded.", "Refresh the execution details from Jenkins.", false));
        }
        return List.copyOf(alerts);
    }

    private ReleaseHealthResponse health(DeploymentExecutionEntity e) {
        List<String> signals = new ArrayList<>();
        double score = 40;
        if (e.getStatus() == DeploymentStatus.SUCCESS) { score += 45; signals.add("Execution completed successfully."); }
        else if (isActive(e.getStatus())) { score += 15; signals.add("Execution remains active."); }
        else { score -= 30; signals.add("Execution is not successful."); }
        if (e.getBuildNumber() != null) { score += 10; signals.add("Build evidence is available."); }
        else signals.add("Build evidence is missing.");
        if (e.getCompletedAt() != null) score += 5;
        score = clamp(score);
        String level = score >= 85 ? "HEALTHY" : score >= 60 ? "WATCH" : "UNHEALTHY";
        return new ReleaseHealthResponse(e.getId(), safe(e.getRepositoryName()), safe(e.getJobName()), name(e.getStatus()), score, level, e.getStatus() == DeploymentStatus.SUCCESS && e.getBuildNumber() != null, List.copyOf(signals), LocalDateTime.now());
    }

    private double priorityScore(DeploymentExecutionEntity e) {
        double score = 30;
        if (e.getStatus() == DeploymentStatus.SUCCESS) score += 55;
        else if (isActive(e.getStatus())) score += 25;
        else score -= 20;
        if (e.getBuildNumber() != null) score += 10;
        if (e.getCompletedAt() != null) score += 5;
        return clamp(score);
    }
    private String rationale(DeploymentExecutionEntity e) { return e.getStatus() == DeploymentStatus.SUCCESS ? "Successful terminal execution with available delivery evidence." : isActive(e.getStatus()) ? "Active execution requires monitoring before promotion." : "Non-successful execution requires remediation."; }
    private AutonomousActionResponse action(DeploymentExecutionEntity e,String code,String title,String type,String priority,String status,boolean automated,boolean approval,String rationale,String hint){return new AutonomousActionResponse(e.getId(),code,title,type,priority,status,automated,approval,rationale,hint,LocalDateTime.now());}
    private RecommendationResponse recommendation(DeploymentExecutionEntity e,String code,String category,String severity,String title,String summary,List<String> steps){return new RecommendationResponse(e.getId(),code,category,severity,title,summary,steps,LocalDateTime.now());}
    private OperationalAlertResponse alert(DeploymentExecutionEntity e,String code,String severity,String category,String title,String description,String action,boolean ack){return new OperationalAlertResponse(code,e.getId(),severity,category,title,description,action,ack,LocalDateTime.now());}
    private List<DeploymentExecutionEntity> load(){return repository.findAllByOrderByStartedAtDesc();}
    private long count(List<DeploymentExecutionEntity> all,DeploymentStatus s){return all.stream().filter(e->e.getStatus()==s).count();}
    private boolean isActive(DeploymentStatus s){return s==DeploymentStatus.PENDING||s==DeploymentStatus.QUEUED||s==DeploymentStatus.RUNNING;}
    private String name(DeploymentStatus s){return s==null?"UNKNOWN":s.name();}
    private String safe(String s){return s==null||s.isBlank()?"not available":s;}
    private static double percentage(long value,long total){return total==0?0:round(value*100.0/total);}
    private static double clamp(double value){return round(Math.max(0,Math.min(100,value)));}
    private static double round(double value){return Math.round(value*100.0)/100.0;}
    private record ScoredRelease(DeploymentExecutionEntity execution,double score){}
}
