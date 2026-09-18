package ai.nexusone.service;

import ai.nexusone.dto.request.RootCauseQueryRequest;
import ai.nexusone.dto.response.RootCauseAssessmentResponse;
import ai.nexusone.dto.response.RootCauseIncidentResponse;
import ai.nexusone.dto.response.RootCauseOverviewResponse;
import ai.nexusone.entity.RootCauseAnalysis;
import ai.nexusone.repository.RootCauseAnalysisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class RootCauseCopilotService {
    private final RootCauseAnalysisRepository repository;
    private final Map<String, RootCauseIncidentResponse> incidents;

    public RootCauseCopilotService(RootCauseAnalysisRepository repository) {
        this.repository = repository;
        this.incidents = seed();
    }

    public RootCauseOverviewResponse overview() {
        return new RootCauseOverviewResponse(
                incidents.size(), repository.countByConfidenceGreaterThanEqual(0.90),
                3, repository.count());
    }

    public List<RootCauseIncidentResponse> incidents() {
        return new ArrayList<>(incidents.values());
    }

    public RootCauseAssessmentResponse assessment(String incidentId) {
        return build(incident(incidentId), "Generate an evidence-backed root cause assessment.");
    }

    public RootCauseAssessmentResponse ask(RootCauseQueryRequest request) {
        RootCauseAssessmentResponse result = build(incident(request.incidentId()), request.question());
        repository.save(new RootCauseAnalysis(
                result.incidentId(), result.question(), result.rootCause(), result.summary(),
                result.confidence(), result.pattern()));
        return result;
    }

    public Page<RootCauseAnalysis> history(int page, int size) {
        return repository.findAllByOrderByCreatedAtDesc(pageable(page, size));
    }

    public Page<RootCauseAnalysis> historyByIncident(String incidentId, int page, int size) {
        incident(incidentId);
        return repository.findByIncidentIdOrderByCreatedAtDesc(incidentId, pageable(page, size));
    }

    private RootCauseIncidentResponse incident(String id) {
        RootCauseIncidentResponse value = incidents.get(id);
        if (value == null) throw new NoSuchElementException("Incident not found: " + id);
        return value;
    }

    private PageRequest pageable(int page, int size) {
        int safePage = Math.max(page, 0);
        int safeSize = Math.min(Math.max(size, 1), 100);
        return PageRequest.of(safePage, safeSize, Sort.by("createdAt").descending());
    }

    private RootCauseAssessmentResponse build(RootCauseIncidentResponse incident, String question) {
        if ("INC-6101".equals(incident.incidentId())) {
            return response(incident, question,
                    "Database connection pool exhaustion after deployment DEP-8842",
                    "The failure pattern correlates the deployment, exhausted database connections, increased HTTP 5xx responses, and pod restarts.",
                    0.92, "RESOURCE_EXHAUSTION",
                    List.of("5xx increased after DEP-8842", "DB pool reached its configured maximum", "Pod restarts temporarily restored traffic"),
                    List.of("Pool capacity below peak concurrency", "Retries amplified connection demand"),
                    List.of("INC-5981", "INC-5722"),
                    List.of("Pause the rollout", "Reduce traffic pressure", "Validate pool sizing with the database owner"),
                    List.of("Add pool saturation alerts", "Load-test peak concurrency", "Add a guarded rollout check"));
        }
        if ("INC-6102".equals(incident.incidentId())) {
            return response(incident, question,
                    "Worker capacity could not match the queue arrival rate",
                    "Queue lag, CPU saturation, and the maximum autoscaling limit increased together.",
                    0.87, "CAPACITY_SATURATION",
                    List.of("Queue lag exceeded threshold", "CPU saturation affected two pods", "HPA reached maximum replicas"),
                    List.of("Maximum replica limit constrained scaling"),
                    List.of("INC-5640"),
                    List.of("Increase approved worker capacity", "Drain backlog under monitoring", "Review HPA maximum"),
                    List.of("Capacity-test queue bursts", "Alert before HPA reaches maximum"));
        }
        return response(incident, question,
                "Missing staging configuration caused readiness probe failures",
                "The deployment failed readiness checks until rollback restored the previous configuration.",
                0.96, "CONFIGURATION_DRIFT",
                List.of("Probe logs identified a missing key", "Rollback restored health"),
                List.of("Pre-deployment configuration validation did not run"),
                List.of("INC-5407"),
                List.of("Restore the required key", "Run readiness and smoke tests"),
                List.of("Validate required keys in CI", "Compare environment configuration before rollout"));
    }

    private RootCauseAssessmentResponse response(RootCauseIncidentResponse i, String q, String cause,
            String summary, double confidence, String pattern, List<String> evidence,
            List<String> factors, List<String> similar, List<String> actions, List<String> prevention) {
        return new RootCauseAssessmentResponse(i.incidentId(), q, cause, summary, confidence, pattern,
                evidence, factors, similar, actions, prevention, Instant.now());
    }

    private Map<String, RootCauseIncidentResponse> seed() {
        Map<String, RootCauseIncidentResponse> values = new LinkedHashMap<>();
        values.put("INC-6101", new RootCauseIncidentResponse("INC-6101", "Checkout API error spike",
                "checkout-service", "production", "CRITICAL", "INVESTIGATING",
                Instant.parse("2026-09-15T05:40:00Z"), "DEP-8842",
                List.of("HTTP 5xx increased to 18%", "Pod restart count reached 12", "Database connection pool exhausted")));
        values.put("INC-6102", new RootCauseIncidentResponse("INC-6102", "Order worker processing delay",
                "order-worker", "production", "HIGH", "MITIGATED",
                Instant.parse("2026-09-15T04:15:00Z"), "DEP-8838",
                List.of("Queue lag exceeded threshold", "CPU saturation on two pods", "Autoscaler reached maximum")));
        values.put("INC-6103", new RootCauseIncidentResponse("INC-6103", "Staging deployment health check failure",
                "customer-api", "staging", "MEDIUM", "RESOLVED",
                Instant.parse("2026-09-14T16:30:00Z"), "DEP-8829",
                List.of("Readiness probe failed", "Configuration key was missing", "Rollback completed")));
        return values;
    }
}
