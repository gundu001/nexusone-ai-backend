package ai.nexusone.service;

import ai.nexusone.dto.request.IntelligenceMeshRequest;
import ai.nexusone.dto.response.IntelligenceMeshOverviewResponse;
import ai.nexusone.dto.response.IntelligenceMeshAnalyticsResponse;
import ai.nexusone.entity.IntelligenceMeshEvent;
import ai.nexusone.repository.IntelligenceMeshEventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Transactional
public class IntelligenceMeshService {

 private static final Set<String> AGENTS = Set.of(
         "INCIDENT_AGENT",
         "ROOT_CAUSE_AGENT",
         "SRE_AGENT",
         "RELEASE_AGENT",
         "SELF_HEALING_AGENT",
         "SECURITY_AGENT",
         "FINOPS_AGENT",
         "GOVERNANCE_AGENT",
         "EXECUTIVE_AGENT"
 );

 private final IntelligenceMeshEventRepository repository;

 public IntelligenceMeshService(IntelligenceMeshEventRepository repository) {
  this.repository = repository;
 }

 public IntelligenceMeshEvent coordinate(IntelligenceMeshRequest r) {
  String source = norm(r.sourceAgent());
  String target = norm(r.targetAgent());
  String type = norm(r.eventType());

  if (!AGENTS.contains(source) || !AGENTS.contains(target)) {
   throw new IllegalArgumentException("Unsupported agent. Allowed values: " + AGENTS);
  }

  if (source.equals(target)) {
   throw new IllegalArgumentException("Source and target agents must be different.");
  }

  double consensus = round((r.sourceConfidence() + r.targetConfidence()) / 2.0);
  boolean approval = r.approvalRequired() || consensus < 80;

  String status = consensus < 60 ? "BLOCKED" : approval ? "PENDING_APPROVAL" : "COORDINATED";
  String decision = consensus < 60 ? "COLLECT_MORE_EVIDENCE" : approval ? "REQUIRE_GOVERNED_APPROVAL" : "PROCEED_WITH_COORDINATED_ACTION";

  IntelligenceMeshEvent e = new IntelligenceMeshEvent();
  e.setSourceAgent(source);
  e.setTargetAgent(target);
  e.setEventType(type);
  e.setApplicationName(r.applicationName().trim());
  e.setEnvironment(r.environment().trim().toLowerCase(Locale.ROOT));
  e.setContextSummary(r.contextSummary().trim());
  e.setSourceConfidence(r.sourceConfidence());
  e.setTargetConfidence(r.targetConfidence());
  e.setConsensusScore(consensus);
  e.setApprovalRequired(approval);
  e.setStatus(status);
  e.setDecision(decision);
  e.setRecommendation(recommend(status, source, target));

  return repository.save(e);
 }

 @Transactional(readOnly = true)
 public Page<IntelligenceMeshEvent> history(Pageable p) {
  return repository.findAll(p);
 }

 @Transactional(readOnly = true)
 public IntelligenceMeshEvent get(Long id) {
  return repository.findById(id)
          .orElseThrow(() -> new NoSuchElementException("Mesh event not found: " + id));
 }

 @Transactional(readOnly = true)
 public IntelligenceMeshOverviewResponse overview() {
  List<IntelligenceMeshEvent> all = repository.findAll();
  long total = all.size();
  long coordinated = repository.countByStatus("COORDINATED");
  long blocked = repository.countByStatus("BLOCKED");
  long pending = repository.countByApprovalRequiredTrueAndStatus("PENDING_APPROVAL");

  double avg = round(all.stream()
          .mapToDouble(IntelligenceMeshEvent::getConsensusScore)
          .average()
          .orElse(0));

  String overall = total == 0 ? "NO_DATA"
          : blocked > 0 ? "ATTENTION_REQUIRED"
          : pending > 0 ? "APPROVALS_PENDING"
          : "MESH_READY";

  return new IntelligenceMeshOverviewResponse(total, coordinated, pending, blocked, avg, overall);
 }

 private static String recommend(String status, String s, String t) {
  return switch (status) {
   case "BLOCKED" -> "Collect additional evidence from " + s + " and " + t + " before coordination.";
   case "PENDING_APPROVAL" -> "Review shared context and approve the coordinated action before execution.";
   default -> "Proceed through governed orchestration and verify the outcome in both participating agents.";
  };
 }

 private static String norm(String v) {
  return v.trim().toUpperCase(Locale.ROOT).replace(' ', '_').replace('-', '_');
 }

 private static double round(double v) {
  return Math.round(v * 100.0) / 100.0;
 }

 @Transactional(readOnly = true)
 public IntelligenceMeshAnalyticsResponse getAnalytics() {

  List<IntelligenceMeshEvent> events = repository.findAll();

  if (events.isEmpty()) {
   return new IntelligenceMeshAnalyticsResponse(
           0,
           0,
           0,
           0,
           0,
           0
   );
  }

  int averageConsensus =
          (int) Math.round(
                  events.stream()
                          .mapToDouble(IntelligenceMeshEvent::getConsensusScore)
                          .average()
                          .orElse(0)
          );

  int highestConsensus =
          (int) events.stream()
                  .mapToDouble(IntelligenceMeshEvent::getConsensusScore)
                  .max()
                  .orElse(0);

  int lowestConsensus =
          (int) events.stream()
                  .mapToDouble(IntelligenceMeshEvent::getConsensusScore)
                  .min()
                  .orElse(0);

  long coordinatedActions =
          events.stream()
                  .filter(e -> "COORDINATED".equals(e.getStatus()))
                  .count();

  long pendingApprovals =
          events.stream()
                  .filter(e -> Boolean.TRUE.equals(e.getApprovalRequired()))
                  .count();

  return new IntelligenceMeshAnalyticsResponse(
          averageConsensus,
          highestConsensus,
          lowestConsensus,
          events.size(),
          coordinatedActions,
          pendingApprovals
  );
 }

 @Transactional(readOnly = true)
 public List<IntelligenceMeshEvent> pendingApprovals() {

  return repository.findAll()
          .stream()
          .filter(e -> Boolean.TRUE.equals(e.getApprovalRequired()))
          .toList();
 }

}
