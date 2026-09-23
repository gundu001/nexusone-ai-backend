package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.EnterpriseMemoryRecord;
import ai.nexusone.enums.*;
import ai.nexusone.exception.MemoryNotFoundException;
import ai.nexusone.repository.EnterpriseMemoryRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EnterpriseMemoryReasoningService {

 private final EnterpriseMemoryRepository repository;

 public EnterpriseMemoryReasoningService(
         EnterpriseMemoryRepository repository) {
  this.repository = repository;
 }

 @Transactional
 public MemoryResponse create(MemoryRequest request) {

  EnterpriseMemoryRecord memory =
          new EnterpriseMemoryRecord();

  memory.setTitle(request.title());
  memory.setMemoryType(request.memoryType());
  memory.setSourceModule(request.sourceModule());

  memory.setKnowledgeId(request.knowledgeId());
  memory.setIncidentId(request.incidentId());
  memory.setDecisionId(request.decisionId());
  memory.setExecutionId(request.executionId());
  memory.setOutcomeId(request.outcomeId());
  memory.setLearningId(request.learningId());

  memory.setContext(request.context());
  memory.setObservation(request.observation());
  memory.setLearnedPattern(request.learnedPattern());
  memory.setReasoningRule(request.reasoningRule());
  memory.setRecommendedAction(request.recommendedAction());

  memory.setConfidenceScore(request.confidenceScore());
  memory.setImportanceScore(request.importanceScore());

  memory.setCreatedBy(request.createdBy());

  return map(repository.save(memory));
 }

 @Transactional
 public MemoryResponse get(Long id) {

  EnterpriseMemoryRecord memory = entity(id);

  memory.setAccessCount(
          memory.getAccessCount() + 1);

  memory.setLastAccessedAt(
          LocalDateTime.now());

  return map(repository.save(memory));
 }

 public Page<MemoryResponse> history(
         Pageable pageable) {

  return repository.findAll(pageable)
          .map(this::map);
 }

 public Page<MemoryResponse> search(
         String keyword,
         MemoryType type,
         MemoryStatus status,
         Pageable pageable) {

  if (keyword != null && !keyword.isBlank()) {

   return repository
           .findByTitleContainingIgnoreCaseOrContextContainingIgnoreCaseOrLearnedPatternContainingIgnoreCaseOrReasoningRuleContainingIgnoreCase(
                   keyword,
                   keyword,
                   keyword,
                   keyword,
                   pageable)
           .map(this::map);
  }

  if (type != null) {
   return repository.findByMemoryType(type, pageable)
           .map(this::map);
  }

  if (status != null) {
   return repository.findByStatus(status, pageable)
           .map(this::map);
  }

  return history(pageable);
 }

 @Transactional
 public MemoryResponse validate(
         Long id,
         ValidateMemoryRequest request) {

  EnterpriseMemoryRecord memory = entity(id);

  guard(memory);

  memory.setStatus(MemoryStatus.VALIDATED);
  memory.setValidatedBy(
          request.validatedBy());

  memory.setValidatedAt(
          LocalDateTime.now());

  return map(repository.save(memory));
 }

 @Transactional
 public MemoryResponse reinforce(Long id) {

  EnterpriseMemoryRecord memory = entity(id);

  guard(memory);

  memory.setReinforcementCount(
          memory.getReinforcementCount() + 1);

  memory.setConfidenceScore(
          Math.min(
                  100,
                  memory.getConfidenceScore() + 2
          ));

  memory.setStatus(
          MemoryStatus.REINFORCED);

  return map(repository.save(memory));
 }

 @Transactional
 public MemoryResponse archive(Long id) {

  EnterpriseMemoryRecord memory = entity(id);

  memory.setStatus(
          MemoryStatus.ARCHIVED);

  return map(repository.save(memory));
 }

 @Transactional
 public ReasoningResponse reason(
         ReasoningRequest request) {

  List<EnterpriseMemoryRecord> candidates =
          repository
                  .findTop10ByStatusNotOrderByImportanceScoreDescConfidenceScoreDesc(
                          MemoryStatus.ARCHIVED
                  );

  if (candidates.isEmpty()) {

   return new ReasoningResponse(
           request.query(),
           "No active enterprise memory is available for this query.",
           "Capture and validate relevant operational knowledge before automated reasoning.",
           0,
           List.of(),
           List.of()
   );
  }

  String queryText =
          (
                  request.query() + " "
                          + Optional.ofNullable(
                                  request.context())
                          .orElse("")
                          + " "
                          + String.join(
                          " ",
                          Optional.ofNullable(
                                          request.signals())
                                  .orElse(List.of())
                  )
          ).toLowerCase();

  var ranked =
          candidates.stream()
                  .map(memory ->
                          Map.entry(
                                  memory,
                                  score(memory, queryText)
                          ))
                  .filter(entry ->
                          entry.getValue() > 0)
                  .sorted(
                          Map.Entry
                                  .<EnterpriseMemoryRecord, Integer>comparingByValue()
                                  .reversed()
                  )
                  .limit(5)
                  .toList();

  if (ranked.isEmpty()) {

   ranked =
           candidates.stream()
                   .limit(3)
                   .map(memory ->
                           Map.entry(memory, 1))
                   .toList();
  }
  double confidence =
          round(
                  ranked.stream()
                          .mapToDouble(
                                  x -> x.getKey()
                                          .getConfidenceScore())
                          .average()
                          .orElse(0)
          );

  EnterpriseMemoryRecord bestMatch =
          ranked.get(0).getKey();

  List<Long> supportingIds =
          ranked.stream()
                  .map(x -> x.getKey().getId())
                  .toList();

  List<String> evidence =
          ranked.stream()
                  .map(x ->
                          x.getKey().getTitle()
                                  + ": "
                                  + x.getKey()
                                  .getLearnedPattern())
                  .toList();

  for (var item : ranked) {

   EnterpriseMemoryRecord memory =
           item.getKey();

   memory.setAccessCount(
           memory.getAccessCount() + 1);

   memory.setLastAccessedAt(
           LocalDateTime.now());

   repository.save(memory);
  }

  return new ReasoningResponse(
          request.query(),
          bestMatch.getReasoningRule(),
          bestMatch.getRecommendedAction(),
          confidence,
          supportingIds,
          evidence
  );
 }

 public MemoryAnalyticsResponse analytics() {

  List<EnterpriseMemoryRecord> all =
          repository.findAll();

  long total =
          all.size();

  long active =
          repository.countByStatus(
                  MemoryStatus.ACTIVE);

  long validated =
          repository.countByStatus(
                  MemoryStatus.VALIDATED);

  long reinforced =
          repository.countByStatus(
                  MemoryStatus.REINFORCED);

  long archived =
          repository.countByStatus(
                  MemoryStatus.ARCHIVED);

  long accesses =
          all.stream()
                  .mapToLong(
                          EnterpriseMemoryRecord::getAccessCount)
                  .sum();

  long reinforcements =
          all.stream()
                  .mapToLong(
                          EnterpriseMemoryRecord::getReinforcementCount)
                  .sum();

  double averageConfidence =
          all.isEmpty()
                  ? 0
                  : round(
                  all.stream()
                          .mapToDouble(
                                  EnterpriseMemoryRecord::getConfidenceScore)
                          .average()
                          .orElse(0)
          );

  double averageImportance =
          all.isEmpty()
                  ? 0
                  : round(
                  all.stream()
                          .mapToDouble(
                                  EnterpriseMemoryRecord::getImportanceScore)
                          .average()
                          .orElse(0)
          );

  return new MemoryAnalyticsResponse(
          total,
          active,
          validated,
          reinforced,
          archived,
          averageConfidence,
          averageImportance,
          accesses,
          reinforcements
  );
 }
 private int score(
         EnterpriseMemoryRecord memory,
         String query) {

  String text =
          (
                  memory.getTitle() + " " +
                          memory.getContext() + " " +
                          memory.getObservation() + " " +
                          memory.getLearnedPattern() + " " +
                          memory.getReasoningRule()
          ).toLowerCase();

  return (int) Arrays.stream(
                  query.split("\\\\W+"))
          .filter(word ->
                  word.length() > 2)
          .filter(text::contains)
          .count();
 }

 private void guard(
         EnterpriseMemoryRecord memory) {

  if (memory.getStatus()
          == MemoryStatus.ARCHIVED) {

   throw new IllegalArgumentException(
           "Archived memory cannot be changed: "
                   + memory.getId());
  }
 }

 private EnterpriseMemoryRecord entity(
         Long id) {

  return repository.findById(id)
          .orElseThrow(
                  () -> new MemoryNotFoundException(id));
 }

 private double round(double value) {
  return Math.round(value * 100.0) / 100.0;
 }

 private MemoryResponse map(
         EnterpriseMemoryRecord memory) {

  return new MemoryResponse(
          memory.getId(),
          memory.getTitle(),
          memory.getMemoryType(),
          memory.getSourceModule(),
          memory.getKnowledgeId(),
          memory.getIncidentId(),
          memory.getDecisionId(),
          memory.getExecutionId(),
          memory.getOutcomeId(),
          memory.getLearningId(),
          memory.getContext(),
          memory.getObservation(),
          memory.getLearnedPattern(),
          memory.getReasoningRule(),
          memory.getRecommendedAction(),
          memory.getConfidenceScore(),
          memory.getImportanceScore(),
          memory.getAccessCount(),
          memory.getReinforcementCount(),
          memory.getStatus(),
          memory.getCreatedBy(),
          memory.getValidatedBy(),
          memory.getValidatedAt(),
          memory.getLastAccessedAt(),
          memory.getCreatedAt(),
          memory.getUpdatedAt()
  );
 }
}