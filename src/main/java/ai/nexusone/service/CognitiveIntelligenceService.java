package ai.nexusone.service;

import ai.nexusone.dto.ApproveInsightRequest;
import ai.nexusone.dto.CognitiveAnalyticsResponse;
import ai.nexusone.dto.CognitiveInsightRequest;
import ai.nexusone.dto.CognitiveInsightResponse;
import ai.nexusone.dto.GenerateInsightRequest;
import ai.nexusone.dto.ReviewInsightRequest;
import ai.nexusone.entity.CognitiveInsight;
import ai.nexusone.enums.InsightStatus;
import ai.nexusone.enums.InsightType;
import ai.nexusone.exception.CognitiveInsightNotFoundException;
import ai.nexusone.repository.CognitiveInsightRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CognitiveIntelligenceService {

    private final CognitiveInsightRepository repository;

    public CognitiveIntelligenceService(CognitiveInsightRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CognitiveInsightResponse create(CognitiveInsightRequest request) {
        CognitiveInsight insight = new CognitiveInsight();
        applyRequest(insight, request);
        insight.setIntelligenceScore(calculateIntelligenceScore(
                request.confidenceScore(),
                request.impactScore()
        ));
        return map(repository.save(insight));
    }

    @Transactional
    public CognitiveInsightResponse generate(GenerateInsightRequest request) {
        List<String> signals = Optional.ofNullable(request.signals())
                .orElse(List.of());
        List<Long> memoryIds = Optional.ofNullable(request.supportingMemoryIds())
                .orElse(List.of());
        List<String> modules = Optional.ofNullable(request.sourceModules())
                .orElse(List.of("Phase 8.9 Memory & Reasoning Fabric"));

        int signalCount = signals.size();
        int memoryCount = memoryIds.size();

        double confidence = clamp(65.0 + signalCount * 4.0 + memoryCount * 3.0);
        double impact = clamp(60.0 + signalCount * 3.0 + memoryCount * 2.0);

        CognitiveInsight insight = new CognitiveInsight();
        insight.setTitle(request.title());
        insight.setInsightType(request.insightType());
        insight.setSourceModules(String.join(", ", modules));
        insight.setMemoryId(memoryIds.isEmpty() ? null : memoryIds.get(0));
        insight.setBusinessContext(request.businessContext());
        insight.setObservation(request.observation());
        insight.setCorrelationSummary(buildCorrelationSummary(signals, memoryIds));
        insight.setCognitiveReasoning(buildReasoning(request, signals));
        insight.setPrediction(buildPrediction(request.insightType(), signals));
        insight.setRecommendation(buildRecommendation(request.insightType()));
        insight.setConfidenceScore(confidence);
        insight.setImpactScore(impact);
        insight.setIntelligenceScore(calculateIntelligenceScore(confidence, impact));
        insight.setCreatedBy(request.createdBy());

        return map(repository.save(insight));
    }

    @Transactional(readOnly = true)
    public Page<CognitiveInsightResponse> history(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<CognitiveInsightResponse> search(
            String keyword,
            InsightType type,
            InsightStatus status,
            Pageable pageable) {

        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .searchByKeyword(keyword, pageable)
                    .map(this::map);
        }


        if (type != null) {
            return repository.findByInsightType(type, pageable).map(this::map);
        }

        if (status != null) {
            return repository.findByStatus(status, pageable).map(this::map);
        }

        return history(pageable);
    }

    @Transactional(readOnly = true)
    public CognitiveInsightResponse get(Long id) {
        return map(findEntity(id));
    }

    @Transactional
    public CognitiveInsightResponse review(
            Long id,
            ReviewInsightRequest request) {

        CognitiveInsight insight = findEntity(id);
        ensureNotArchived(insight);

        insight.setStatus(InsightStatus.REVIEWED);
        insight.setReviewedBy(request.reviewedBy());
        insight.setReviewedAt(LocalDateTime.now());
        insight.setReviewCount(insight.getReviewCount() + 1);

        return map(repository.save(insight));
    }

    @Transactional
    public CognitiveInsightResponse approve(
            Long id,
            ApproveInsightRequest request) {

        CognitiveInsight insight = findEntity(id);
        ensureNotArchived(insight);

        if (insight.getStatus() != InsightStatus.REVIEWED) {
            throw new IllegalArgumentException(
                    "Only REVIEWED cognitive insights can be approved. Current status: "
                            + insight.getStatus()
            );
        }

        insight.setStatus(InsightStatus.APPROVED);
        insight.setApprovedBy(request.approvedBy());
        insight.setApprovedAt(LocalDateTime.now());

        return map(repository.save(insight));
    }

    @Transactional
    public CognitiveInsightResponse archive(Long id) {
        CognitiveInsight insight = findEntity(id);
        insight.setStatus(InsightStatus.ARCHIVED);
        return map(repository.save(insight));
    }

    @Transactional(readOnly = true)
    public CognitiveAnalyticsResponse analytics() {
        List<CognitiveInsight> insights = repository.findAll();

        long total = insights.size();
        long active = repository.countByStatus(InsightStatus.ACTIVE);
        long reviewed = repository.countByStatus(InsightStatus.REVIEWED);
        long approved = repository.countByStatus(InsightStatus.APPROVED);
        long archived = repository.countByStatus(InsightStatus.ARCHIVED);
        long predictions = repository.countByInsightType(InsightType.RISK_PREDICTION);
        long correlations = repository.countByInsightType(InsightType.ROOT_CAUSE_CORRELATION);

        double averageConfidence = insights.stream()
                .mapToDouble(CognitiveInsight::getConfidenceScore)
                .average()
                .orElse(0.0);

        double averageImpact = insights.stream()
                .mapToDouble(CognitiveInsight::getImpactScore)
                .average()
                .orElse(0.0);

        double enterpriseScore = insights.stream()
                .filter(insight -> insight.getStatus() != InsightStatus.ARCHIVED)
                .mapToDouble(CognitiveInsight::getIntelligenceScore)
                .average()
                .orElse(0.0);

        return new CognitiveAnalyticsResponse(
                total,
                active,
                reviewed,
                approved,
                archived,
                predictions,
                correlations,
                round(averageConfidence),
                round(averageImpact),
                round(enterpriseScore)
        );
    }

    private void applyRequest(
            CognitiveInsight insight,
            CognitiveInsightRequest request) {

        insight.setTitle(request.title());
        insight.setInsightType(request.insightType());
        insight.setSourceModules(request.sourceModules());
        insight.setMemoryId(request.memoryId());
        insight.setKnowledgeId(request.knowledgeId());
        insight.setIncidentId(request.incidentId());
        insight.setDecisionId(request.decisionId());
        insight.setExecutionId(request.executionId());
        insight.setOutcomeId(request.outcomeId());
        insight.setLearningId(request.learningId());
        insight.setBusinessContext(request.businessContext());
        insight.setObservation(request.observation());
        insight.setCorrelationSummary(request.correlationSummary());
        insight.setCognitiveReasoning(request.cognitiveReasoning());
        insight.setPrediction(request.prediction());
        insight.setRecommendation(request.recommendation());
        insight.setConfidenceScore(request.confidenceScore());
        insight.setImpactScore(request.impactScore());
        insight.setCreatedBy(request.createdBy());
    }

    private String buildCorrelationSummary(
            List<String> signals,
            List<Long> memoryIds) {

        String signalText = signals.isEmpty()
                ? "No explicit signals supplied"
                : String.join(", ", signals);

        String memoryText = memoryIds.isEmpty()
                ? "no supporting memory references"
                : "supporting memory IDs " + memoryIds;

        return "Correlated signals: " + signalText + "; " + memoryText + ".";
    }

    private String buildReasoning(
            GenerateInsightRequest request,
            List<String> signals) {

        return "The cognitive engine evaluated the business context, observation and "
                + signals.size()
                + " supplied signals for "
                + request.insightType()
                + ".";
    }

    private String buildPrediction(
            InsightType insightType,
            List<String> signals) {

        String signalText = signals.isEmpty()
                ? "the supplied operational context"
                : String.join(", ", signals);

        return switch (insightType) {
            case RISK_PREDICTION ->
                    "Operational risk may increase when these signals recur: " + signalText + ".";
            case ANOMALY_DETECTION ->
                    "A deviation should be investigated when observed behavior differs from: " + signalText + ".";
            case ROOT_CAUSE_CORRELATION ->
                    "The supplied signals may share a common operational cause: " + signalText + ".";
            case IMPACT_ANALYSIS ->
                    "The observed condition may affect dependent business or technology services.";
            case DECISION_GUIDANCE ->
                    "A governed decision is recommended before automated execution.";
            case PATTERN_DISCOVERY ->
                    "The supplied evidence indicates a potentially reusable enterprise pattern.";
        };
    }

    private String buildRecommendation(InsightType insightType) {
        return switch (insightType) {
            case RISK_PREDICTION ->
                    "Validate current telemetry and prepare a governed preventive action.";
            case ANOMALY_DETECTION ->
                    "Compare the anomaly with validated baselines and enterprise memory.";
            case ROOT_CAUSE_CORRELATION ->
                    "Verify the strongest correlation before selecting remediation.";
            case IMPACT_ANALYSIS ->
                    "Assess affected applications, services, customers and SLA exposure.";
            case DECISION_GUIDANCE ->
                    "Review evidence, confidence and expected impact before approval.";
            case PATTERN_DISCOVERY ->
                    "Validate the pattern and promote it into reusable enterprise knowledge.";
        };
    }

    private double calculateIntelligenceScore(
            double confidenceScore,
            double impactScore) {

        return round((confidenceScore * 0.6) + (impactScore * 0.4));
    }

    private double clamp(double value) {
        return Math.max(0.0, Math.min(100.0, value));
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private void ensureNotArchived(CognitiveInsight insight) {
        if (insight.getStatus() == InsightStatus.ARCHIVED) {
            throw new IllegalArgumentException(
                    "Archived cognitive insight cannot be modified: " + insight.getId()
            );
        }
    }

    private CognitiveInsight findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CognitiveInsightNotFoundException(id));
    }

    private CognitiveInsightResponse map(CognitiveInsight insight) {
        return new CognitiveInsightResponse(
                insight.getId(),
                insight.getTitle(),
                insight.getInsightType(),
                insight.getSourceModules(),
                insight.getMemoryId(),
                insight.getKnowledgeId(),
                insight.getIncidentId(),
                insight.getDecisionId(),
                insight.getExecutionId(),
                insight.getOutcomeId(),
                insight.getLearningId(),
                insight.getBusinessContext(),
                insight.getObservation(),
                insight.getCorrelationSummary(),
                insight.getCognitiveReasoning(),
                insight.getPrediction(),
                insight.getRecommendation(),
                insight.getConfidenceScore(),
                insight.getImpactScore(),
                insight.getIntelligenceScore(),
                insight.getReviewCount(),
                insight.getStatus(),
                insight.getCreatedBy(),
                insight.getReviewedBy(),
                insight.getReviewedAt(),
                insight.getApprovedBy(),
                insight.getApprovedAt(),
                insight.getCreatedAt(),
                insight.getUpdatedAt()
        );
    }
}
