package ai.nexusone.service;

import ai.nexusone.dto.request.CopilotQueryRequest;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.IncidentAnalysis;
import ai.nexusone.enums.*;
import ai.nexusone.repository.IncidentAnalysisRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.cache.annotation.Cacheable;
import java.time.Instant;
import java.util.*;

@Service
public class IncidentCopilotService {

 private final IncidentAnalysisRepository history;
 private final Map<String, IncidentResponse> incidents;


 public IncidentCopilotService(IncidentAnalysisRepository history) {
  this.history = history;
  this.incidents = seed();
 }

 private Map<String, IncidentResponse> seed() {
  Map<String, IncidentResponse> m = new LinkedHashMap<>();

  m.put("INC-6101", new IncidentResponse(
          "INC-6101",
          "Checkout API error spike",
          "checkout-service",
          "production",
          IncidentSeverity.CRITICAL,
          IncidentStatus.INVESTIGATING,
          Instant.parse("2026-09-15T05:40:00Z"),
          "DEP-8842",
          List.of("HTTP 5xx increased to 18%", "Pod restart count reached 12", "Database connection pool exhausted")
  ));

  m.put("INC-6102", new IncidentResponse(
          "INC-6102",
          "Order worker processing delay",
          "order-worker",
          "production",
          IncidentSeverity.HIGH,
          IncidentStatus.MITIGATED,
          Instant.parse("2026-09-15T04:15:00Z"),
          "DEP-8838",
          List.of("Queue lag exceeded threshold", "CPU saturation on two pods", "Autoscaler reached configured maximum")
  ));

  m.put("INC-6103", new IncidentResponse(
          "INC-6103",
          "Staging deployment health check failure",
          "customer-api",
          "staging",
          IncidentSeverity.MEDIUM,
          IncidentStatus.RESOLVED,
          Instant.parse("2026-09-14T16:30:00Z"),
          "DEP-8829",
          List.of("Readiness probe failed", "Configuration key was missing", "Rollback completed")
  ));

  return m;
 }

 public List<IncidentResponse> incidents() {
  return new ArrayList<>(incidents.values());
 }

 public IncidentResponse incident(String id) {
  IncidentResponse i = incidents.get(id);
  if (i == null) {
   throw new NoSuchElementException("Incident not found: " + id);
  }
  return i;
 }

 public IncidentOverviewResponse overview() {
  var all = incidents();
  return new IncidentOverviewResponse(
          all.size(),
          all.stream().filter(i -> i.severity() == IncidentSeverity.CRITICAL).count(),
          all.stream().filter(i -> i.status() != IncidentStatus.RESOLVED).count(),
          all.stream().filter(i -> i.status() == IncidentStatus.RESOLVED).count()
  );
 }

 public RcaResponse rca(String id) {
  incident(id);

  if (id.equals("INC-6101")) {
   return new RcaResponse(
           id,
           "Database connection pool exhaustion after the latest deployment",
           0.92,
           "Checkout requests failed and pods restarted under load",
           List.of("5xx spike started after DEP-8842", "Active DB connections reached pool maximum", "Restarting pods temporarily restored traffic"),
           List.of("Pool size lower than peak concurrency", "Retry amplification increased connection demand")
   );
  }

  if (id.equals("INC-6102")) {
   return new RcaResponse(
           id,
           "Worker capacity was insufficient for queue arrival rate",
           0.87,
           "Order processing latency increased",
           List.of("Queue lag and CPU saturation moved together", "HPA reached maximum replica count"),
           List.of("Maximum replica limit constrained scaling")
   );
  }

  return new RcaResponse(
          id,
          "Missing staging configuration caused readiness probe failures",
          0.96,
          "Deployment was unavailable until rollback",
          List.of("Probe log names missing key", "Rollback restored prior configuration"),
          List.of("Configuration validation did not run before deployment")
  );
 }

 public RemediationResponse remediation(String id) {
  RcaResponse r = rca(id);

  if (id.equals("INC-6101")) {
   return new RemediationResponse(
           id,
           "Stabilize traffic, restore DB capacity, then validate a guarded configuration change",
           "HIGH",
           true,
           List.of(
                   new RemediationResponse.RemediationStep(1, "Pause further rollout", "kubectl rollout pause deployment/checkout-service -n production", true),
                   new RemediationResponse.RemediationStep(2, "Scale pods to reduce request pressure", "kubectl scale deployment/checkout-service --replicas=8 -n production", false),
                   new RemediationResponse.RemediationStep(3, "Increase and validate connection pool limits", "Update datasource pool configuration after DBA approval", false)
           )
   );
  }

  return new RemediationResponse(
          id,
          "Apply the evidence-backed recovery plan and verify service health",
          "MEDIUM",
          true,
          List.of(
                  new RemediationResponse.RemediationStep(1, "Capture current state", "kubectl get pods -A", true),
                  new RemediationResponse.RemediationStep(2, "Apply approved corrective action", r.rootCause(), false),
                  new RemediationResponse.RemediationStep(3, "Verify health signals", "Run readiness and smoke tests", true)
          )
  );
 }

 @CacheEvict(
         value = "incidentHistory",
         allEntries = true
 )
 @Cacheable(
         value = "incident-answer",
         key = "#q.incidentId.concat('-').concat(#q.question)"
 )
 public CopilotAnswerResponse ask(
         CopilotQueryRequest q) {
  IncidentResponse i = incident(q.incidentId());
  RcaResponse r = rca(q.incidentId());
  RemediationResponse rem = remediation(q.incidentId());

  String text = ("Incident %s affects %s in %s. Most likely cause: %s. Current impact: %s. Recommended next action: %s. Human approval is %s before change execution.")
          .formatted(
                  i.incidentId(),
                  i.application(),
                  i.environment(),
                  r.rootCause(),
                  r.impact(),
                  rem.steps().get(0).action(),
                  rem.approvalRequired() ? "required" : "not required"
          );

  history.save(new IncidentAnalysis(i.incidentId(), q.question(), text));

  return new CopilotAnswerResponse(
          i.incidentId(),
          q.question(),
          text,
          r.confidence(),
          List.of("incident-signals", "deployment-correlation", "kubernetes-health"),
          Instant.now()
  );
 }

 public Page<IncidentAnalysis> history(
         int page,
         int size) {

  Pageable pageable =
          PageRequest.of(
                  page,
                  size,
                  Sort.by("createdAt")
                          .descending()
          );

  return history
          .findAllByOrderByCreatedAtDesc(
                  pageable
          );
 }

 @Cacheable(
         value = "incidentHistory",
         key = "#incidentId"
 )
 public Page<IncidentAnalysis> historyByIncident(
         String incidentId,
         int page,
         int size) {

  System.out.println(
          "Loading Incident History from DB : "
                  + incidentId
  );

  Pageable pageable =
          PageRequest.of(
                  page,
                  size,
                  Sort.by("createdAt")
                          .descending()
          );

  return history
          .findByIncidentIdOrderByCreatedAtDesc(
                  incidentId,
                  pageable
          );
 }

}