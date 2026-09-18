package ai.nexusone.service;

import ai.nexusone.dto.request.ChangeRiskCopilotQueryRequest;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.ChangeRiskCopilotAnalysis;
import ai.nexusone.repository.ChangeRiskCopilotAnalysisRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ChangeRiskCopilotService {
    private final ChangeRiskPredictionService predictionService;
    private final ChangeRiskCopilotAnalysisRepository historyRepository;

    public ChangeRiskCopilotService(ChangeRiskPredictionService predictionService,
                                    ChangeRiskCopilotAnalysisRepository historyRepository) {
        this.predictionService = predictionService;
        this.historyRepository = historyRepository;
    }

    public ChangeRiskCopilotOverviewResponse overview() {
        ChangeRiskOverviewResponse o = predictionService.getOverview();
        return new ChangeRiskCopilotOverviewResponse(
                o.totalChanges(), o.highRiskChanges(), o.requiredApprovals(),
                o.averageRiskScore(), historyRepository.count());
    }

    public List<ChangeRiskAssessmentResponse> changes() {
        return predictionService.getAssessments();
    }

    public ChangeRiskCopilotAnswerResponse assess(Long changeId) {
        return buildAnswer(changeId, "Assess this change and recommend safe deployment controls.");
    }

    public ChangeRiskCopilotAnswerResponse ask(ChangeRiskCopilotQueryRequest request) {
        ChangeRiskCopilotAnswerResponse response = buildAnswer(request.changeId(), request.question());
        historyRepository.save(new ChangeRiskCopilotAnalysis(
                response.changeId(), response.question(), response.answer(),
                response.confidence(), response.riskLevel()));
        return response;
    }

    public Page<ChangeRiskCopilotAnalysis> history(int page, int size) {
        Pageable pageable = PageRequest.of(safePage(page), safeSize(size), Sort.by("createdAt").descending());
        return historyRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Page<ChangeRiskCopilotAnalysis> historyByChange(Long changeId, int page, int size) {
        findChange(changeId);
        Pageable pageable = PageRequest.of(safePage(page), safeSize(size), Sort.by("createdAt").descending());
        return historyRepository.findByChangeIdOrderByCreatedAtDesc(changeId, pageable);
    }

    private ChangeRiskCopilotAnswerResponse buildAnswer(Long changeId, String question) {
        ChangeRiskAssessmentResponse change = findChange(changeId);
        ImpactAnalysisResponse impact = predictionService.getImpactAnalysis().stream()
                .filter(i -> i.changeId().equals(changeId)).findFirst().orElse(null);
        ChangeRiskRecommendationResponse recommendation = predictionService.getRecommendations().stream()
                .filter(r -> r.changeId().equals(changeId)).findFirst().orElse(null);

        boolean approval = "HIGH".equalsIgnoreCase(change.riskLevel())
                || "REQUIRES_APPROVAL".equalsIgnoreCase(change.status());
        double confidence = change.historicalFailures() > 0 ? 0.91 : 0.86;
        String impactText = impact == null ? "Impact data is not available"
                : "%d services, approximately %d users, and %d minutes estimated downtime"
                .formatted(impact.affectedServices(), impact.estimatedAffectedUsers(), impact.estimatedDowntimeMinutes());
        String recommendationText = recommendation == null
                ? "Run automated tests, verify rollback, and use a monitored rollout."
                : recommendation.suggestedAction();
        String answer = ("Change %d for %s has a %.1f%% %s risk score. Scope: %d files and %d lines. "
                + "Predicted impact: %s. Recommendation: %s Human approval is %s.")
                .formatted(change.changeId(), change.application(), change.riskScore(), change.riskLevel(),
                        change.filesChanged(), change.linesChanged(), impactText, recommendationText,
                        approval ? "required" : "not required");

        List<String> evidence = List.of(
                "Risk score: %.1f%%".formatted(change.riskScore()),
                "Historical failures: " + change.historicalFailures(),
                "Change scope: %d files and %d lines".formatted(change.filesChanged(), change.linesChanged()),
                impactText);
        List<String> actions = approval
                ? List.of("Obtain change approval", "Run integration and regression tests",
                          "Use canary rollout", "Verify rollback before production")
                : List.of("Run automated tests", "Use monitored deployment", "Verify post-deployment health");
        return new ChangeRiskCopilotAnswerResponse(changeId, question, answer, confidence,
                change.riskLevel(), approval, evidence, actions, Instant.now());
    }

    private ChangeRiskAssessmentResponse findChange(Long changeId) {
        return predictionService.getAssessments().stream()
                .filter(c -> c.changeId().equals(changeId)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Change not found: " + changeId));
    }

    private int safePage(int page) { return Math.max(0, page); }
    private int safeSize(int size) { return Math.min(100, Math.max(1, size)); }
}
