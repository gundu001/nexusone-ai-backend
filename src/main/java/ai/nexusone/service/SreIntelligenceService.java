package ai.nexusone.service;

import ai.nexusone.dto.request.SreAnalysisRequest;
import ai.nexusone.dto.response.SreOverviewResponse;
import ai.nexusone.entity.SreAssessment;
import ai.nexusone.repository.SreAssessmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SreIntelligenceService {
    private final SreAssessmentRepository repository;
    public SreIntelligenceService(SreAssessmentRepository repository) { this.repository = repository; }

    public SreAssessment analyze(SreAnalysisRequest r) {
        if (r.failedRequests() > r.totalRequests()) throw new IllegalArgumentException("failedRequests cannot exceed totalRequests");
        double errorRate = r.totalRequests() == 0 ? 0 : r.failedRequests() * 100.0 / r.totalRequests();
        double allowedDowntime = Math.max(0.0001, 100.0 - r.targetSloPercent());
        double consumed = Math.max(0, 100.0 - r.availabilityPercent());
        double errorBudgetRemaining = Math.max(0, 100.0 - (consumed / allowedDowntime * 100.0));
        double availabilityScore = Math.min(100, r.availabilityPercent());
        double errorScore = Math.max(0, 100 - errorRate * 10);
        double recoveryScore = Math.max(0, 100 - Math.min(100, r.mttrMinutes()));
        double stabilityScore = Math.min(100, r.mtbfHours());
        int score = (int)Math.round(availabilityScore * .40 + errorScore * .20 + recoveryScore * .20 + stabilityScore * .20);
        String sloStatus = r.availabilityPercent() >= r.targetSloPercent() ? "COMPLIANT" : "BREACHED";
        String risk = score >= 85 ? "LOW" : score >= 65 ? "MEDIUM" : "HIGH";
        String recommendation = buildRecommendation(sloStatus, errorBudgetRemaining, r.mttrMinutes(), errorRate);

        SreAssessment x = new SreAssessment();
        x.setApplicationName(r.applicationName()); x.setAvailabilityPercent(round(r.availabilityPercent()));
        x.setTotalRequests(r.totalRequests()); x.setFailedRequests(r.failedRequests()); x.setErrorRatePercent(round(errorRate));
        x.setMttrMinutes(round(r.mttrMinutes())); x.setMtbfHours(round(r.mtbfHours())); x.setTargetSloPercent(round(r.targetSloPercent()));
        x.setErrorBudgetPercent(round(errorBudgetRemaining)); x.setReliabilityScore(score); x.setRiskLevel(risk);
        x.setSloStatus(sloStatus); x.setRecommendation(recommendation);
        return repository.save(x);
    }

    @Transactional(readOnly = true)
    public SreOverviewResponse overview() {
        List<SreAssessment> all = repository.findAll();
        double score = all.stream().mapToInt(SreAssessment::getReliabilityScore).average().orElse(0);
        double availability = all.stream().mapToDouble(SreAssessment::getAvailabilityPercent).average().orElse(0);
        double mttr = all.stream().mapToDouble(SreAssessment::getMttrMinutes).average().orElse(0);
        double mtbf = all.stream().mapToDouble(SreAssessment::getMtbfHours).average().orElse(0);
        long compliant = all.stream().filter(x -> "COMPLIANT".equals(x.getSloStatus())).count();
        long highRisk = all.stream().filter(x -> "HIGH".equals(x.getRiskLevel())).count();
        String status = all.isEmpty() ? "NO_DATA" : score >= 85 ? "RELIABLE" : score >= 65 ? "AT_RISK" : "CRITICAL";
        return new SreOverviewResponse(all.size(), round(score), round(availability), round(mttr), round(mtbf), compliant, highRisk, status);
    }

    @Transactional(readOnly = true) public Page<SreAssessment> history(Pageable pageable) { return repository.findAll(pageable); }
    @Transactional(readOnly = true) public List<SreAssessment> recommendations() { return repository.findTop10ByOrderByAnalyzedAtDesc(); }
    private String buildRecommendation(String slo, double budget, double mttr, double errorRate) {
        if ("BREACHED".equals(slo)) return "SLO breached. Freeze risky releases, investigate availability loss, and execute the reliability improvement plan.";
        if (budget < 25) return "Error budget is nearly exhausted. Reduce change velocity and prioritize reliability work.";
        if (mttr > 60) return "MTTR is high. Improve runbooks, alert routing, diagnostics, and recovery automation.";
        if (errorRate > 1) return "Request failure rate is elevated. Review recent changes and dependent-service health.";
        return "Reliability indicators are healthy. Continue monitoring SLO compliance and error-budget consumption.";
    }
    private double round(double v) { return Math.round(v * 100.0) / 100.0; }
}
