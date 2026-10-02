package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.AutonomousEnterpriseAgiCoordinationReport;
import ai.nexusone.enums.*;
import ai.nexusone.exception.AutonomousEnterpriseAgiCoordinationNotFoundException;
import ai.nexusone.repository.AutonomousEnterpriseAgiCoordinationRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.ToDoubleFunction;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutonomousEnterpriseAgiCoordinationService {
    private final AutonomousEnterpriseAgiCoordinationRepository repository;
    public AutonomousEnterpriseAgiCoordinationService(AutonomousEnterpriseAgiCoordinationRepository repository) { this.repository = repository; }

    @Transactional
    public AutonomousEnterpriseAgiCoordinationResponse generate(AutonomousEnterpriseAgiCoordinationRequest request) {
        AutonomousEnterpriseAgiCoordinationReport report = new AutonomousEnterpriseAgiCoordinationReport();
        report.setTitle(request.title());
        report.setAgentCoordination(request.agentCoordination());
        report.setAgentCoordinationScore(request.agentCoordinationScore());
        report.setGoalOrchestration(request.goalOrchestration());
        report.setGoalOrchestrationScore(request.goalOrchestrationScore());
        report.setTaskDelegation(request.taskDelegation());
        report.setTaskDelegationScore(request.taskDelegationScore());
        report.setSharedContext(request.sharedContext());
        report.setSharedContextScore(request.sharedContextScore());
        report.setReasoningAlignment(request.reasoningAlignment());
        report.setReasoningAlignmentScore(request.reasoningAlignmentScore());
        report.setConflictResolution(request.conflictResolution());
        report.setConflictResolutionScore(request.conflictResolutionScore());
        report.setHumanOversight(request.humanOversight());
        report.setHumanOversightScore(request.humanOversightScore());
        report.setSafetyGovernance(request.safetyGovernance());
        report.setSafetyGovernanceScore(request.safetyGovernanceScore());
        report.setOutcomeSynchronization(request.outcomeSynchronization());
        report.setOutcomeSynchronizationScore(request.outcomeSynchronizationScore());
        report.setCoordinationRecommendations(request.coordinationRecommendations());
        report.setAutonomousEnterpriseAgiCoordinationScore(calculateScore(request));
        report.setPriority(request.priority());
        report.setStatus(AutonomousEnterpriseAgiCoordinationStatus.GENERATED);
        report.setCreatedBy(request.createdBy());
        return map(repository.save(report));
    }

    @Transactional(readOnly = true) public AutonomousEnterpriseAgiCoordinationResponse get(Long id) { return map(find(id)); }
    @Transactional(readOnly = true) public Page<AutonomousEnterpriseAgiCoordinationResponse> history(Pageable p) { return repository.findAll(p).map(this::map); }
    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseAgiCoordinationResponse> search(String keyword, AutonomousEnterpriseAgiCoordinationPriority priority, AutonomousEnterpriseAgiCoordinationStatus status, Pageable p) {
        if (keyword != null && !keyword.isBlank()) return repository.findByTitleContainingIgnoreCaseOrAgentCoordinationContainingIgnoreCaseOrCoordinationRecommendationsContainingIgnoreCase(keyword, keyword, keyword, p).map(this::map);
        if (priority != null) return repository.findByPriority(priority, p).map(this::map);
        if (status != null) return repository.findByStatus(status, p).map(this::map);
        return history(p);
    }

    @Transactional public AutonomousEnterpriseAgiCoordinationResponse review(Long id, AutonomousEnterpriseAgiCoordinationActionRequest request) {
        var report = find(id); require(report, AutonomousEnterpriseAgiCoordinationStatus.GENERATED, "Only GENERATED reports can be reviewed");
        report.setStatus(AutonomousEnterpriseAgiCoordinationStatus.REVIEWED); report.setReviewedBy(request.actionBy()); report.setReviewedAt(LocalDateTime.now()); return map(repository.save(report));
    }
    @Transactional public AutonomousEnterpriseAgiCoordinationResponse approve(Long id, AutonomousEnterpriseAgiCoordinationActionRequest request) {
        var report = find(id); require(report, AutonomousEnterpriseAgiCoordinationStatus.REVIEWED, "Only REVIEWED reports can be approved");
        report.setStatus(AutonomousEnterpriseAgiCoordinationStatus.APPROVED); report.setDecidedBy(request.actionBy()); report.setDecidedAt(LocalDateTime.now()); return map(repository.save(report));
    }
    @Transactional public AutonomousEnterpriseAgiCoordinationResponse reject(Long id, AutonomousEnterpriseAgiCoordinationActionRequest request) {
        var report = find(id);
        if (report.getStatus() != AutonomousEnterpriseAgiCoordinationStatus.GENERATED && report.getStatus() != AutonomousEnterpriseAgiCoordinationStatus.REVIEWED) throw new IllegalArgumentException("Only GENERATED or REVIEWED reports can be rejected");
        report.setStatus(AutonomousEnterpriseAgiCoordinationStatus.REJECTED); report.setDecidedBy(request.actionBy()); report.setDecidedAt(LocalDateTime.now()); return map(repository.save(report));
    }
    @Transactional public AutonomousEnterpriseAgiCoordinationResponse publish(Long id, AutonomousEnterpriseAgiCoordinationActionRequest request) {
        var report = find(id); require(report, AutonomousEnterpriseAgiCoordinationStatus.APPROVED, "Only APPROVED reports can be published");
        report.setStatus(AutonomousEnterpriseAgiCoordinationStatus.PUBLISHED); report.setPublishedBy(request.actionBy()); report.setPublishedAt(LocalDateTime.now()); return map(repository.save(report));
    }

    @Transactional(readOnly = true)
    public AutonomousEnterpriseAgiCoordinationAnalyticsResponse analytics() {
        List<AutonomousEnterpriseAgiCoordinationReport> reports = repository.findAll();
        return new AutonomousEnterpriseAgiCoordinationAnalyticsResponse(reports.size(), count(AutonomousEnterpriseAgiCoordinationStatus.GENERATED), count(AutonomousEnterpriseAgiCoordinationStatus.REVIEWED), count(AutonomousEnterpriseAgiCoordinationStatus.APPROVED), count(AutonomousEnterpriseAgiCoordinationStatus.REJECTED), count(AutonomousEnterpriseAgiCoordinationStatus.PUBLISHED),
                average(reports, AutonomousEnterpriseAgiCoordinationReport::getAgentCoordinationScore),
                average(reports, AutonomousEnterpriseAgiCoordinationReport::getGoalOrchestrationScore),
                average(reports, AutonomousEnterpriseAgiCoordinationReport::getTaskDelegationScore),
                average(reports, AutonomousEnterpriseAgiCoordinationReport::getSharedContextScore),
                average(reports, AutonomousEnterpriseAgiCoordinationReport::getReasoningAlignmentScore),
                average(reports, AutonomousEnterpriseAgiCoordinationReport::getConflictResolutionScore),
                average(reports, AutonomousEnterpriseAgiCoordinationReport::getHumanOversightScore),
                average(reports, AutonomousEnterpriseAgiCoordinationReport::getSafetyGovernanceScore),
                average(reports, AutonomousEnterpriseAgiCoordinationReport::getOutcomeSynchronizationScore),
                average(reports, AutonomousEnterpriseAgiCoordinationReport::getAutonomousEnterpriseAgiCoordinationScore));
    }

    private double calculateScore(AutonomousEnterpriseAgiCoordinationRequest request) { return round(request.agentCoordinationScore() * 0.15
                + request.goalOrchestrationScore() * 0.13
                + request.taskDelegationScore() * 0.12
                + request.sharedContextScore() * 0.11
                + request.reasoningAlignmentScore() * 0.11
                + request.conflictResolutionScore() * 0.10
                + request.humanOversightScore() * 0.10
                + request.safetyGovernanceScore() * 0.10
                + request.outcomeSynchronizationScore() * 0.08); }
    private double average(List<AutonomousEnterpriseAgiCoordinationReport> reports, ToDoubleFunction<AutonomousEnterpriseAgiCoordinationReport> field) { return round(reports.stream().mapToDouble(field).average().orElse(0)); }
    private long count(AutonomousEnterpriseAgiCoordinationStatus status) { return repository.countByStatus(status); }
    private double round(double value) { return Math.round(value * 100.0) / 100.0; }
    private void require(AutonomousEnterpriseAgiCoordinationReport report, AutonomousEnterpriseAgiCoordinationStatus expected, String message) { if (report.getStatus() != expected) throw new IllegalArgumentException(message); }
    private AutonomousEnterpriseAgiCoordinationReport find(Long id) { return repository.findById(id).orElseThrow(() -> new AutonomousEnterpriseAgiCoordinationNotFoundException(id)); }
    private AutonomousEnterpriseAgiCoordinationResponse map(AutonomousEnterpriseAgiCoordinationReport report) {
        return new AutonomousEnterpriseAgiCoordinationResponse(report.getId(), report.getTitle(),
                report.getAgentCoordination(),
                report.getGoalOrchestration(),
                report.getTaskDelegation(),
                report.getSharedContext(),
                report.getReasoningAlignment(),
                report.getConflictResolution(),
                report.getHumanOversight(),
                report.getSafetyGovernance(),
                report.getOutcomeSynchronization(),
                report.getCoordinationRecommendations(),
                report.getAgentCoordinationScore(),
                report.getGoalOrchestrationScore(),
                report.getTaskDelegationScore(),
                report.getSharedContextScore(),
                report.getReasoningAlignmentScore(),
                report.getConflictResolutionScore(),
                report.getHumanOversightScore(),
                report.getSafetyGovernanceScore(),
                report.getOutcomeSynchronizationScore(),
                report.getAutonomousEnterpriseAgiCoordinationScore(), report.getPriority(), report.getStatus(), report.getCreatedBy(), report.getReviewedBy(), report.getReviewedAt(), report.getDecidedBy(), report.getDecidedAt(), report.getPublishedBy(), report.getPublishedAt(), report.getCreatedAt(), report.getUpdatedAt());
    }
}
