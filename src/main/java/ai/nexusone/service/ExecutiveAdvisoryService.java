package ai.nexusone.service;

import ai.nexusone.dto.ExecutiveAdvisoryAnalyticsResponse;
import ai.nexusone.dto.ExecutiveAdvisoryRequest;
import ai.nexusone.dto.ExecutiveAdvisoryResponse;
import ai.nexusone.dto.ExecutiveDecisionRequest;
import ai.nexusone.entity.ExecutiveAdvisory;
import ai.nexusone.enums.ExecutiveAdvisoryStatus;
import ai.nexusone.enums.ExecutivePriority;
import ai.nexusone.exception.ExecutiveAdvisoryNotFoundException;
import ai.nexusone.repository.ExecutiveAdvisoryRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExecutiveAdvisoryService {
    private final ExecutiveAdvisoryRepository repository;

    public ExecutiveAdvisoryService(ExecutiveAdvisoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ExecutiveAdvisoryResponse generate(ExecutiveAdvisoryRequest request) {
        ExecutiveAdvisory entity = new ExecutiveAdvisory();
        entity.setTitle(request.title());
        entity.setStrategicObjective(request.strategicObjective());
        entity.setBusinessChallenge(request.businessChallenge());
        entity.setExecutiveRecommendation(request.executiveRecommendation());
        entity.setInvestmentValue(request.investmentValue());
        entity.setStrategicAlignment(request.strategicAlignment());
        entity.setRiskScore(request.riskScore());
        entity.setPriority(request.priority());
        entity.setAdvisoryScore(score(request.strategicAlignment(), request.investmentValue(), request.riskScore()));
        entity.setStatus(ExecutiveAdvisoryStatus.GENERATED);
        entity.setCreatedBy(request.createdBy());
        return map(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public ExecutiveAdvisoryResponse get(Long id) { return map(find(id)); }

    @Transactional(readOnly = true)
    public Page<ExecutiveAdvisoryResponse> history(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<ExecutiveAdvisoryResponse> search(String keyword, ExecutivePriority priority,
                                                   ExecutiveAdvisoryStatus status, Pageable pageable) {
        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .findByTitleContainingIgnoreCaseOrStrategicObjectiveContainingIgnoreCaseOrExecutiveRecommendationContainingIgnoreCase(
                            keyword, keyword, keyword, pageable)
                    .map(this::map);
        }
        if (priority != null) return repository.findByPriority(priority, pageable).map(this::map);
        if (status != null) return repository.findByStatus(status, pageable).map(this::map);
        return history(pageable);
    }

    @Transactional
    public ExecutiveAdvisoryResponse recommend(Long id, ExecutiveDecisionRequest request) {
        ExecutiveAdvisory entity = find(id);
        if (entity.getStatus() != ExecutiveAdvisoryStatus.GENERATED) {
            throw new IllegalArgumentException("Only GENERATED advisories can be reviewed");
        }
        entity.setStatus(ExecutiveAdvisoryStatus.REVIEWED);
        entity.setReviewedBy(request.actionBy());
        entity.setReviewedAt(LocalDateTime.now());
        return map(repository.save(entity));
    }

    @Transactional
    public ExecutiveAdvisoryResponse accept(Long id, ExecutiveDecisionRequest request) {
        ExecutiveAdvisory entity = find(id);
        if (entity.getStatus() != ExecutiveAdvisoryStatus.REVIEWED) {
            throw new IllegalArgumentException("Only REVIEWED advisories can be accepted");
        }
        entity.setStatus(ExecutiveAdvisoryStatus.ACCEPTED);
        entity.setDecidedBy(request.actionBy());
        entity.setDecidedAt(LocalDateTime.now());
        return map(repository.save(entity));
    }

    @Transactional
    public ExecutiveAdvisoryResponse reject(Long id, ExecutiveDecisionRequest request) {
        ExecutiveAdvisory entity = find(id);
        if (entity.getStatus() != ExecutiveAdvisoryStatus.GENERATED
                && entity.getStatus() != ExecutiveAdvisoryStatus.REVIEWED) {
            throw new IllegalArgumentException("Only GENERATED or REVIEWED advisories can be rejected");
        }
        entity.setStatus(ExecutiveAdvisoryStatus.REJECTED);
        entity.setDecidedBy(request.actionBy());
        entity.setDecidedAt(LocalDateTime.now());
        return map(repository.save(entity));
    }

    @Transactional
    public ExecutiveAdvisoryResponse execute(Long id, ExecutiveDecisionRequest request) {
        ExecutiveAdvisory entity = find(id);
        if (entity.getStatus() != ExecutiveAdvisoryStatus.ACCEPTED) {
            throw new IllegalArgumentException("Only ACCEPTED advisories can be executed");
        }
        entity.setStatus(ExecutiveAdvisoryStatus.EXECUTED);
        entity.setExecutedBy(request.actionBy());
        entity.setExecutedAt(LocalDateTime.now());
        return map(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public ExecutiveAdvisoryAnalyticsResponse analytics() {
        List<ExecutiveAdvisory> all = repository.findAll();
        return new ExecutiveAdvisoryAnalyticsResponse(
                all.size(), count(ExecutiveAdvisoryStatus.GENERATED), count(ExecutiveAdvisoryStatus.REVIEWED),
                count(ExecutiveAdvisoryStatus.ACCEPTED), count(ExecutiveAdvisoryStatus.REJECTED),
                count(ExecutiveAdvisoryStatus.EXECUTED), average(all, "alignment"), average(all, "investment"),
                average(all, "risk"), round(all.stream().mapToDouble(ExecutiveAdvisory::getAdvisoryScore).average().orElse(0)));
    }

    private long count(ExecutiveAdvisoryStatus status) { return repository.countByStatus(status); }

    private double average(List<ExecutiveAdvisory> all, String field) {
        return round(all.stream().mapToDouble(item -> switch (field) {
            case "alignment" -> item.getStrategicAlignment();
            case "investment" -> item.getInvestmentValue();
            default -> item.getRiskScore();
        }).average().orElse(0));
    }

    private double score(double alignment, double investment, double risk) {
        return round(alignment * 0.45 + investment * 0.35 + (100 - risk) * 0.20);
    }

    private double round(double value) { return Math.round(value * 100.0) / 100.0; }

    private ExecutiveAdvisory find(Long id) {
        return repository.findById(id).orElseThrow(() -> new ExecutiveAdvisoryNotFoundException(id));
    }

    private ExecutiveAdvisoryResponse map(ExecutiveAdvisory entity) {
        return new ExecutiveAdvisoryResponse(entity.getId(), entity.getTitle(), entity.getStrategicObjective(),
                entity.getBusinessChallenge(), entity.getExecutiveRecommendation(), entity.getInvestmentValue(),
                entity.getStrategicAlignment(), entity.getRiskScore(), entity.getAdvisoryScore(), entity.getPriority(),
                entity.getStatus(), entity.getCreatedBy(), entity.getReviewedBy(), entity.getReviewedAt(),
                entity.getDecidedBy(), entity.getDecidedAt(), entity.getExecutedBy(), entity.getExecutedAt(),
                entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
