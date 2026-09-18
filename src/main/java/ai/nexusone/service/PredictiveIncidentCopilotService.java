package ai.nexusone.service;

import ai.nexusone.dto.request.PredictiveIncidentQueryRequest;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.PredictiveIncidentAnalysis;
import ai.nexusone.repository.PredictiveIncidentAnalysisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class PredictiveIncidentCopilotService {
    private final PredictiveIncidentAnalysisRepository repository;
    private final Map<String, PredictiveIncidentResponse> predictions;

    public PredictiveIncidentCopilotService(PredictiveIncidentAnalysisRepository repository) {
        this.repository = repository;
        this.predictions = seed();
    }

    public PredictiveIncidentOverviewResponse overview() {
        long highRisk = predictions.values().stream()
                .filter(p -> p.riskScore() >= 0.80).count();
        return new PredictiveIncidentOverviewResponse(
                predictions.size(), highRisk, predictions.size(), repository.count());
    }

    public List<PredictiveIncidentResponse> predictions() {
        return new ArrayList<>(predictions.values());
    }

    public PredictiveIncidentAssessmentResponse assessment(String application) {
        return build(prediction(application), "Generate a proactive incident prevention forecast.");
    }

    public PredictiveIncidentAssessmentResponse forecast(PredictiveIncidentQueryRequest request) {
        PredictiveIncidentAssessmentResponse result = build(prediction(request.application()), request.question());
        repository.save(new PredictiveIncidentAnalysis(
                result.application(), result.question(), result.predictedIncident(), result.summary(),
                result.probability(), result.riskLevel(), result.predictionWindow()));
        return result;
    }

    public Page<PredictiveIncidentAnalysis> history(int page, int size) {
        return repository.findAllByOrderByCreatedAtDesc(pageable(page, size));
    }

    public Page<PredictiveIncidentAnalysis> historyByApplication(String application, int page, int size) {
        prediction(application);
        return repository.findByApplicationOrderByCreatedAtDesc(application, pageable(page, size));
    }

    private PredictiveIncidentResponse prediction(String application) {
        PredictiveIncidentResponse result = predictions.get(application);
        if (result == null) throw new NoSuchElementException("Application not found: " + application);
        return result;
    }

    private PageRequest pageable(int page, int size) {
        return PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 100),
                Sort.by("createdAt").descending());
    }

    private PredictiveIncidentAssessmentResponse build(PredictiveIncidentResponse p, String question) {
        if ("checkout-service".equals(p.application())) {
            return response(p, question,
                    "Database connection pool exhaustion",
                    "Current saturation and retry signals indicate a likely checkout outage unless connection demand is reduced.",
                    List.of("DB pool utilization reached 91%", "HTTP retry volume increased 34%", "Recent deployment changed query concurrency"),
                    List.of("Peak traffic is approaching", "Retry amplification increases connection demand"),
                    List.of("Reduce retry pressure", "Increase approved pool capacity", "Pause risky rollout changes"),
                    List.of("Add pool saturation guardrails", "Load-test peak concurrency", "Apply progressive delivery checks"));
        }
        if ("order-worker".equals(p.application())) {
            return response(p, question,
                    "Queue backlog and worker capacity saturation",
                    "Queue growth and maximum replica utilization indicate processing delay risk during the next traffic peak.",
                    List.of("Queue lag increased for three intervals", "CPU remained above 82%", "HPA is near maximum replicas"),
                    List.of("Maximum replica ceiling", "Bursting order volume"),
                    List.of("Increase worker ceiling", "Drain backlog under monitoring", "Review CPU requests"),
                    List.of("Capacity-test queue bursts", "Alert before HPA reaches maximum"));
        }
        return response(p, question,
                "Configuration drift causing readiness failures",
                "Configuration comparison detected a missing required key before the next staging rollout.",
                List.of("Required key missing in staging", "Readiness probe references the key", "Deployment manifest changed"),
                List.of("Configuration validation did not run"),
                List.of("Restore the required key", "Block deployment until validation passes"),
                List.of("Validate required keys in CI", "Compare environment configuration before rollout"));
    }

    private PredictiveIncidentAssessmentResponse response(PredictiveIncidentResponse p, String question,
            String incident, String summary, List<String> evidence, List<String> factors,
            List<String> recommendations, List<String> prevention) {
        return new PredictiveIncidentAssessmentResponse(
                p.application(), question, incident, summary, p.riskScore(), p.riskLevel(),
                p.predictionWindow(), evidence, factors, recommendations, prevention, Instant.now());
    }

    private Map<String, PredictiveIncidentResponse> seed() {
        Map<String, PredictiveIncidentResponse> values = new LinkedHashMap<>();
        values.put("checkout-service", new PredictiveIncidentResponse(
                "PRED-6601", "checkout-service", "production", "Database connection pool exhaustion",
                0.87, "HIGH", "Next 24 hours", "ACTION_REQUIRED", Instant.parse("2026-09-18T08:30:00Z"),
                List.of("DB pool utilization 91%", "Retry volume +34%", "5xx trend increasing")));
        values.put("order-worker", new PredictiveIncidentResponse(
                "PRED-6602", "order-worker", "production", "Queue processing delay",
                0.76, "MEDIUM", "Next 48 hours", "MONITORING", Instant.parse("2026-09-18T08:20:00Z"),
                List.of("Queue lag increasing", "CPU above 82%", "HPA near maximum")));
        values.put("customer-api", new PredictiveIncidentResponse(
                "PRED-6603", "customer-api", "staging", "Readiness probe failure",
                0.93, "CRITICAL", "Next deployment", "BLOCK_RECOMMENDED", Instant.parse("2026-09-18T08:10:00Z"),
                List.of("Required configuration key missing", "Manifest drift detected", "Probe validation failed")));
        return values;
    }
}
