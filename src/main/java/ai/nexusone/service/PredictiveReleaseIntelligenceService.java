package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.DeploymentExecutionEntity;
import ai.nexusone.enums.DeploymentStatus;
import ai.nexusone.repository.DeploymentExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PredictiveReleaseIntelligenceService {
    private final DeploymentExecutionRepository repository;

    public PredictiveReleaseIntelligenceService(DeploymentExecutionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public PredictiveOverviewResponse getOverview() {
        List<DeploymentExecutionEntity> all = load();
        long success = count(all, DeploymentStatus.SUCCESS);
        long failed = count(all, DeploymentStatus.FAILED);
        long aborted = count(all, DeploymentStatus.ABORTED);
        long active = all.stream().filter(e -> isActive(e.getStatus())).count();
        long terminal = success + failed + aborted;
        double successProbability = terminal == 0 ? baselineForActive(active) : clamp(success * 100.0 / terminal);
        double failureProbability = round(100.0 - successProbability);
        double confidence = confidence(all.size(), terminal);
        String risk = riskLevel(failureProbability);
        String status = all.isEmpty() ? "NO_DATA" : active > 0 ? "ACTIVE_FORECAST" : "STABLE_FORECAST";
        return new PredictiveOverviewResponse(all.size(), successProbability, failureProbability, risk,
                confidence, active, terminal, status, LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public ReleaseSuccessPredictionResponse predictReleaseSuccess(Long executionId) {
        DeploymentExecutionEntity e = find(executionId);
        List<String> signals = new ArrayList<>();
        double probability = probabilityFor(e, signals);
        return new ReleaseSuccessPredictionResponse(e.getId(), safe(e.getRepositoryName()), name(e.getStatus()),
                probability, round(100.0 - probability), confidence(load().size(), terminalCount(load())),
                probability >= 80 ? "LIKELY_SUCCESS" : probability >= 55 ? "UNCERTAIN" : "LIKELY_FAILURE",
                List.copyOf(signals), LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public FailureProbabilityResponse getFailureProbability(Long executionId) {
        DeploymentExecutionEntity e = find(executionId);
        List<String> signals = new ArrayList<>();
        double failure = round(100.0 - probabilityFor(e, signals));
        List<String> mitigations = new ArrayList<>();
        if (e.getBuildNumber() == null) mitigations.add("Wait for Jenkins build assignment before promotion.");
        if (e.getStatus() != DeploymentStatus.SUCCESS) mitigations.add("Require a successful terminal execution before release approval.");
        if (!notBlank(e.getJenkinsMessage())) mitigations.add("Capture Jenkins execution evidence for higher-confidence analysis.");
        if (mitigations.isEmpty()) mitigations.add("Continue smoke validation and controlled monitoring.");
        String band = failure >= 70 ? "VERY_HIGH" : failure >= 45 ? "HIGH" : failure >= 20 ? "MODERATE" : "LOW";
        return new FailureProbabilityResponse(e.getId(), failure, band, riskLevel(failure),
                List.copyOf(signals), List.copyOf(mitigations), LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<RiskForecastResponse> getRiskForecast() {
        PredictiveOverviewResponse o = getOverview();
        List<RiskForecastResponse> result = new ArrayList<>();
        for (int day = 1; day <= 7; day++) {
            double uncertainty = Math.min(12.0, day * 1.5);
            double projectedFailure = clamp(o.predictedFailureProbability() + uncertainty);
            result.add(new RiskForecastResponse(LocalDate.now().plusDays(day), riskLevel(projectedFailure),
                    projectedFailure, round(100.0 - projectedFailure), o.analyzedDeployments(),
                    "Projection applies increasing uncertainty to the current historical execution baseline."));
        }
        return List.copyOf(result);
    }

    @Transactional(readOnly = true)
    public DeploymentCapacityResponse getDeploymentCapacity() {
        List<DeploymentExecutionEntity> all = load();
        long active = all.stream().filter(e -> isActive(e.getStatus())).count();
        long terminal = terminalCount(all);
        double load = percentage(active, Math.max(1, all.size()));
        double throughput = throughput(all);
        long limit = Math.max(1, Math.min(10, Math.round(Math.max(throughput, 1.0))));
        String status = load >= 80 ? "SATURATED" : load >= 50 ? "BUSY" : "AVAILABLE";
        String recommendation = load >= 80 ? "Delay non-critical releases until active executions complete."
                : "Capacity is available for controlled release execution.";
        return new DeploymentCapacityResponse(all.size(), active, terminal, load, throughput, status,
                limit, recommendation, LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public ChangeImpactResponse getChangeImpact(Long executionId) {
        DeploymentExecutionEntity e = find(executionId);
        List<String> signals = new ArrayList<>();
        double score = 0;
        if (e.getStatus() == DeploymentStatus.FAILED) { score += 55; signals.add("Execution is failed."); }
        else if (e.getStatus() == DeploymentStatus.ABORTED) { score += 45; signals.add("Execution was aborted."); }
        else if (isActive(e.getStatus())) { score += 30; signals.add("Execution is still active."); }
        else { signals.add("Execution completed successfully."); }
        if (e.getBuildNumber() == null) { score += 20; signals.add("Build evidence is unavailable."); }
        if (!notBlank(e.getJenkinsMessage())) { score += 10; signals.add("Detailed Jenkins message is unavailable."); }
        if (e.getCompletedAt() == null) { score += 15; signals.add("Completion evidence is unavailable."); }
        score = clamp(score);
        String level = score >= 70 ? "CRITICAL" : score >= 45 ? "HIGH" : score >= 20 ? "MEDIUM" : "LOW";
        List<String> controls = score >= 45 ? List.of("Require human approval.", "Review RCA and governance evidence.", "Prepare rollback readiness.")
                : List.of("Continue standard approval and post-deployment validation.");
        return new ChangeImpactResponse(e.getId(), safe(e.getRepositoryName()), score, level, score >= 45,
                List.copyOf(signals), controls, LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<AiPredictionResponse> getAiPredictions() {
        PredictiveOverviewResponse o = getOverview();
        DeploymentCapacityResponse c = getDeploymentCapacity();
        List<AiPredictionResponse> result = new ArrayList<>();
        if (o.activeDeployments() > 0) result.add(prediction("ACTIVE_RELEASE_UNCERTAINTY", "FORECAST", "MEDIUM",
                "Active releases increase forecast uncertainty", o.activeDeployments() + " execution(s) have not reached a terminal state.",
                List.of("Wait for terminal results before finalizing promotion decisions.", "Refresh forecasts after Jenkins synchronization.")));
        if (o.predictedFailureProbability() >= 45) result.add(prediction("FAILURE_RISK_ELEVATED", "RISK", "HIGH",
                "Predicted failure exposure is elevated", "The deterministic historical baseline indicates " + o.predictedFailureProbability() + "% failure exposure.",
                List.of("Review failed and aborted executions.", "Apply governance and RCA controls before promotion.")));
        if ("SATURATED".equals(c.capacityStatus())) result.add(prediction("CAPACITY_PRESSURE", "CAPACITY", "HIGH",
                "Deployment capacity is under pressure", "Active deployment load is " + c.activeLoadPercentage() + "%.",
                List.of("Delay non-critical releases.", "Complete active executions before increasing concurrency.")));
        if (result.isEmpty()) result.add(prediction("PREDICTIVE_SIGNALS_STABLE", "PLATFORM", "LOW",
                "Predictive signals are stable", "No elevated deterministic forecast signal was detected.",
                List.of("Continue controlled delivery and periodic forecast refresh.")));
        return List.copyOf(result);
    }

    private AiPredictionResponse prediction(String code,String category,String severity,String title,String narrative,List<String> actions) {
        return new AiPredictionResponse(code,category,severity,title,narrative,actions,LocalDateTime.now());
    }
    private double probabilityFor(DeploymentExecutionEntity e, List<String> signals) {
        double score = 50;
        if (e.getStatus() == DeploymentStatus.SUCCESS) { score += 45; signals.add("Execution completed successfully."); }
        else if (e.getStatus() == DeploymentStatus.FAILED) { score -= 45; signals.add("Execution failed."); }
        else if (e.getStatus() == DeploymentStatus.ABORTED) { score -= 35; signals.add("Execution was aborted."); }
        else if (isActive(e.getStatus())) { score += 5; signals.add("Execution is active and outcome is not final."); }
        if (e.getBuildNumber() != null) { score += 5; signals.add("Jenkins build evidence is available."); }
        else { score -= 10; signals.add("Jenkins build evidence is unavailable."); }
        if (e.getCompletedAt() != null) score += 5;
        String message = e.getJenkinsMessage() == null ? "" : e.getJenkinsMessage().toLowerCase(Locale.ROOT);
        if (message.contains("fail") || message.contains("error") || message.contains("timeout")) { score -= 20; signals.add("Jenkins message contains a failure indicator."); }
        return clamp(score);
    }
    private List<DeploymentExecutionEntity> load(){ return repository.findAllByOrderByStartedAtDesc(); }
    private DeploymentExecutionEntity find(Long id){ if(id==null) throw new IllegalArgumentException("Execution ID is required"); return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Deployment execution not found for id: "+id)); }
    private long count(List<DeploymentExecutionEntity> all,DeploymentStatus s){ return all.stream().filter(e -> e.getStatus()==s).count(); }
    private long terminalCount(List<DeploymentExecutionEntity> all){ return count(all,DeploymentStatus.SUCCESS)+count(all,DeploymentStatus.FAILED)+count(all,DeploymentStatus.ABORTED); }
    private boolean isActive(DeploymentStatus s){ return s==DeploymentStatus.PENDING||s==DeploymentStatus.QUEUED||s==DeploymentStatus.RUNNING; }
    private double baselineForActive(long active){ return active>0?50.0:0.0; }
    private double confidence(long samples,long terminal){ if(samples==0)return 0; return clamp(35.0+Math.min(40.0,samples*5.0)+percentage(terminal,samples)*0.25); }
    private String riskLevel(double failure){ return failure>=70?"CRITICAL":failure>=45?"HIGH":failure>=20?"MEDIUM":"LOW"; }
    private double throughput(List<DeploymentExecutionEntity> all){ List<LocalDate> dates=all.stream().filter(e->e.getStartedAt()!=null).map(e->e.getStartedAt().toLocalDate()).sorted().toList(); if(dates.isEmpty())return 0; long days=Math.max(1,ChronoUnit.DAYS.between(dates.get(0),dates.get(dates.size()-1))+1); return round(all.size()*1.0/days); }
    private boolean notBlank(String s){ return s!=null&&!s.isBlank(); }
    private String safe(String s){ return notBlank(s)?s:"not available"; }
    private String name(DeploymentStatus s){ return s==null?"UNKNOWN":s.name(); }
    private static double percentage(long v,long t){ return t==0?0:round(v*100.0/t); }
    private static double clamp(double v){ return round(Math.max(0,Math.min(100,v))); }
    private static double round(double v){ return Math.round(v*100.0)/100.0; }
}
