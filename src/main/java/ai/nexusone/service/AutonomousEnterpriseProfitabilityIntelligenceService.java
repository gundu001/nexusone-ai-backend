package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.AutonomousEnterpriseProfitabilityIntelligence;
import ai.nexusone.enums.*;
import ai.nexusone.exception.*;
import ai.nexusone.repository.*;
import ai.nexusone.specification.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AutonomousEnterpriseProfitabilityIntelligenceService {

    private final AutonomousEnterpriseProfitabilityIntelligenceRepository repository;

    public AutonomousEnterpriseProfitabilityIntelligenceService(AutonomousEnterpriseProfitabilityIntelligenceRepository repository) {
        this.repository = repository;
    }

    public AutonomousEnterpriseProfitabilityIntelligenceResponse generate(AutonomousEnterpriseProfitabilityIntelligenceRequest request) {
        AutonomousEnterpriseProfitabilityIntelligence entity = new AutonomousEnterpriseProfitabilityIntelligence();
        entity.setTitle(request.title());
        entity.setProfitabilityIntelligenceVision(request.profitabilityIntelligenceVision());
        entity.setMarginOptimizationStrategy(request.marginOptimizationStrategy());
        entity.setCostEfficiencyStrategy(request.costEfficiencyStrategy());
        entity.setProductProfitabilityStrategy(request.productProfitabilityStrategy());
        entity.setCustomerProfitabilityStrategy(request.customerProfitabilityStrategy());
        entity.setOperatingLeverageStrategy(request.operatingLeverageStrategy());
        entity.setCashFlowOptimizationStrategy(request.cashFlowOptimizationStrategy());
        entity.setExecutiveProfitabilityDecision(request.executiveProfitabilityDecision());
        entity.setGrossMarginScore(request.grossMarginScore());
        entity.setOperatingMarginScore(request.operatingMarginScore());
        entity.setCostEfficiencyScore(request.costEfficiencyScore());
        entity.setProductProfitabilityScore(request.productProfitabilityScore());
        entity.setCustomerProfitabilityScore(request.customerProfitabilityScore());
        entity.setOperatingLeverageScore(request.operatingLeverageScore());
        entity.setCashFlowStrengthScore(request.cashFlowStrengthScore());
        entity.setExecutionConfidenceScore(request.executionConfidenceScore());

        double average = (
                request.grossMarginScore() +
                request.operatingMarginScore() +
                request.costEfficiencyScore() +
                request.productProfitabilityScore() +
                request.customerProfitabilityScore() +
                request.operatingLeverageScore() +
                request.cashFlowStrengthScore() +
                request.executionConfidenceScore()) / 8.0;

        entity.setEnterpriseProfitabilityIntelligenceScore(Math.round(average * 100.0) / 100.0);
        entity.setPriority(request.priority());
        entity.setCreatedBy(request.createdBy());
        entity.setStatus(AutonomousEnterpriseProfitabilityIntelligenceStatus.GENERATED);
        return map(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public AutonomousEnterpriseProfitabilityIntelligenceResponse getById(Long id) { return map(find(id)); }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseProfitabilityIntelligenceResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseProfitabilityIntelligenceResponse> search(String keyword, AutonomousEnterpriseProfitabilityIntelligencePriority priority, AutonomousEnterpriseProfitabilityIntelligenceStatus status, Pageable pageable) {
        return repository.findAll(AutonomousEnterpriseProfitabilityIntelligenceSpecification.filter(keyword, priority, status), pageable).map(this::map);
    }

    private AutonomousEnterpriseProfitabilityIntelligence find(Long id) {
        return repository.findById(id).orElseThrow(() -> new AutonomousEnterpriseProfitabilityIntelligenceNotFoundException(id));
    }

    private AutonomousEnterpriseProfitabilityIntelligenceResponse map(AutonomousEnterpriseProfitabilityIntelligence entity) {
        return new AutonomousEnterpriseProfitabilityIntelligenceResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getProfitabilityIntelligenceVision(),
                entity.getMarginOptimizationStrategy(),
                entity.getCostEfficiencyStrategy(),
                entity.getProductProfitabilityStrategy(),
                entity.getCustomerProfitabilityStrategy(),
                entity.getOperatingLeverageStrategy(),
                entity.getCashFlowOptimizationStrategy(),
                entity.getExecutiveProfitabilityDecision(),
                entity.getGrossMarginScore(),
                entity.getOperatingMarginScore(),
                entity.getCostEfficiencyScore(),
                entity.getProductProfitabilityScore(),
                entity.getCustomerProfitabilityScore(),
                entity.getOperatingLeverageScore(),
                entity.getCashFlowStrengthScore(),
                entity.getExecutionConfidenceScore(),
                entity.getEnterpriseProfitabilityIntelligenceScore(),
                entity.getPriority(),
                entity.getStatus(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
