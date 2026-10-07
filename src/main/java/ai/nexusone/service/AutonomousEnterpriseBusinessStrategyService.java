package ai.nexusone.service;

import ai.nexusone.dto.AutonomousEnterpriseBusinessStrategyRequest;
import ai.nexusone.dto.AutonomousEnterpriseBusinessStrategyResponse;
import ai.nexusone.entity.AutonomousEnterpriseBusinessStrategy;
import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyPriority;
import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyStatus;
import ai.nexusone.exception.AutonomousEnterpriseBusinessStrategyNotFoundException;
import ai.nexusone.repository.AutonomousEnterpriseBusinessStrategyRepository;
import ai.nexusone.specification.AutonomousEnterpriseBusinessStrategySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AutonomousEnterpriseBusinessStrategyService {

    private final AutonomousEnterpriseBusinessStrategyRepository repository;

    public AutonomousEnterpriseBusinessStrategyService(
            AutonomousEnterpriseBusinessStrategyRepository repository) {
        this.repository = repository;
    }

    public AutonomousEnterpriseBusinessStrategyResponse generate(
            AutonomousEnterpriseBusinessStrategyRequest request) {
        AutonomousEnterpriseBusinessStrategy entity = new AutonomousEnterpriseBusinessStrategy();
        entity.setTitle(request.title());
        entity.setBusinessStrategyVision(request.businessStrategyVision());
        entity.setStrategicObjectives(request.strategicObjectives());
        entity.setCompetitivePositioningStrategy(request.competitivePositioningStrategy());
        entity.setCustomerValueStrategy(request.customerValueStrategy());
        entity.setOperatingModelStrategy(request.operatingModelStrategy());
        entity.setDigitalBusinessStrategy(request.digitalBusinessStrategy());
        entity.setGrowthExecutionRoadmap(request.growthExecutionRoadmap());
        entity.setExecutiveStrategyDecision(request.executiveStrategyDecision());
        entity.setStrategicAlignmentScore(request.strategicAlignmentScore());
        entity.setMarketPositionScore(request.marketPositionScore());
        entity.setCustomerValueScore(request.customerValueScore());
        entity.setOperatingModelScore(request.operatingModelScore());
        entity.setDigitalReadinessScore(request.digitalReadinessScore());
        entity.setGrowthPotentialScore(request.growthPotentialScore());
        entity.setRiskResilienceScore(request.riskResilienceScore());
        entity.setExecutionConfidenceScore(request.executionConfidenceScore());

        double average = (
                request.strategicAlignmentScore()
                + request.marketPositionScore()
                + request.customerValueScore()
                + request.operatingModelScore()
                + request.digitalReadinessScore()
                + request.growthPotentialScore()
                + request.riskResilienceScore()
                + request.executionConfidenceScore()) / 8.0;

        entity.setEnterpriseBusinessStrategyScore(Math.round(average * 100.0) / 100.0);
        entity.setPriority(request.priority());
        entity.setCreatedBy(request.createdBy());
        entity.setStatus(AutonomousEnterpriseBusinessStrategyStatus.GENERATED);
        return map(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public AutonomousEnterpriseBusinessStrategyResponse getById(Long id) {
        return map(find(id));
    }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseBusinessStrategyResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseBusinessStrategyResponse> search(
            String keyword,
            AutonomousEnterpriseBusinessStrategyPriority priority,
            AutonomousEnterpriseBusinessStrategyStatus status,
            Pageable pageable) {
        return repository.findAll(
                AutonomousEnterpriseBusinessStrategySpecification.filter(keyword, priority, status),
                pageable).map(this::map);
    }

    private AutonomousEnterpriseBusinessStrategy find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AutonomousEnterpriseBusinessStrategyNotFoundException(id));
    }

    private AutonomousEnterpriseBusinessStrategyResponse map(
            AutonomousEnterpriseBusinessStrategy entity) {
        return new AutonomousEnterpriseBusinessStrategyResponse(
                entity.getId(), entity.getTitle(), entity.getBusinessStrategyVision(),
                entity.getStrategicObjectives(), entity.getCompetitivePositioningStrategy(),
                entity.getCustomerValueStrategy(), entity.getOperatingModelStrategy(),
                entity.getDigitalBusinessStrategy(), entity.getGrowthExecutionRoadmap(),
                entity.getExecutiveStrategyDecision(), entity.getStrategicAlignmentScore(),
                entity.getMarketPositionScore(), entity.getCustomerValueScore(),
                entity.getOperatingModelScore(), entity.getDigitalReadinessScore(),
                entity.getGrowthPotentialScore(), entity.getRiskResilienceScore(),
                entity.getExecutionConfidenceScore(), entity.getEnterpriseBusinessStrategyScore(),
                entity.getPriority(), entity.getStatus(), entity.getCreatedBy(),
                entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
