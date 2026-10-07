package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.AutonomousEnterpriseRevenueIntelligence;
import ai.nexusone.enums.*;
import ai.nexusone.exception.*;
import ai.nexusone.repository.*;
import ai.nexusone.specification.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class AutonomousEnterpriseRevenueIntelligenceService {
    private final AutonomousEnterpriseRevenueIntelligenceRepository repository;
    public AutonomousEnterpriseRevenueIntelligenceService(AutonomousEnterpriseRevenueIntelligenceRepository repository) { this.repository = repository; }
    public AutonomousEnterpriseRevenueIntelligenceResponse generate(AutonomousEnterpriseRevenueIntelligenceRequest request) {
        AutonomousEnterpriseRevenueIntelligence entity = new AutonomousEnterpriseRevenueIntelligence();
        entity.setTitle(request.title());
        entity.setRevenueIntelligenceVision(request.revenueIntelligenceVision());
        entity.setRevenueGrowthStrategy(request.revenueGrowthStrategy());
        entity.setRecurringRevenueStrategy(request.recurringRevenueStrategy());
        entity.setCustomerRevenueStrategy(request.customerRevenueStrategy());
        entity.setPricingOptimizationStrategy(request.pricingOptimizationStrategy());
        entity.setSalesEffectivenessStrategy(request.salesEffectivenessStrategy());
        entity.setRevenueDiversificationStrategy(request.revenueDiversificationStrategy());
        entity.setExecutiveRevenueDecision(request.executiveRevenueDecision());
        entity.setRevenueGrowthScore(request.revenueGrowthScore());
        entity.setRecurringRevenueScore(request.recurringRevenueScore());
        entity.setCustomerRevenueScore(request.customerRevenueScore());
        entity.setPricingOptimizationScore(request.pricingOptimizationScore());
        entity.setSalesEffectivenessScore(request.salesEffectivenessScore());
        entity.setRevenueDiversificationScore(request.revenueDiversificationScore());
        entity.setForecastAccuracyScore(request.forecastAccuracyScore());
        entity.setExecutionConfidenceScore(request.executionConfidenceScore());
        double average = (request.revenueGrowthScore() +
                request.recurringRevenueScore() +
                request.customerRevenueScore() +
                request.pricingOptimizationScore() +
                request.salesEffectivenessScore() +
                request.revenueDiversificationScore() +
                request.forecastAccuracyScore() +
                request.executionConfidenceScore()) / 8.0;
        entity.setEnterpriseRevenueIntelligenceScore(Math.round(average * 100.0) / 100.0);
        entity.setPriority(request.priority()); entity.setCreatedBy(request.createdBy()); entity.setStatus(AutonomousEnterpriseRevenueIntelligenceStatus.GENERATED);
        return map(repository.save(entity));
    }
    @Transactional(readOnly = true) public AutonomousEnterpriseRevenueIntelligenceResponse getById(Long id) { return map(find(id)); }
    @Transactional(readOnly = true) public Page<AutonomousEnterpriseRevenueIntelligenceResponse> getAll(Pageable pageable) { return repository.findAll(pageable).map(this::map); }
    @Transactional(readOnly = true) public Page<AutonomousEnterpriseRevenueIntelligenceResponse> search(String keyword, AutonomousEnterpriseRevenueIntelligencePriority priority, AutonomousEnterpriseRevenueIntelligenceStatus status, Pageable pageable) { return repository.findAll(AutonomousEnterpriseRevenueIntelligenceSpecification.filter(keyword, priority, status), pageable).map(this::map); }
    private AutonomousEnterpriseRevenueIntelligence find(Long id) { return repository.findById(id).orElseThrow(() -> new AutonomousEnterpriseRevenueIntelligenceNotFoundException(id)); }
    private AutonomousEnterpriseRevenueIntelligenceResponse map(AutonomousEnterpriseRevenueIntelligence entity) { return new AutonomousEnterpriseRevenueIntelligenceResponse(
                entity.getId(), entity.getTitle(), entity.getRevenueIntelligenceVision(), entity.getRevenueGrowthStrategy(), entity.getRecurringRevenueStrategy(), entity.getCustomerRevenueStrategy(), entity.getPricingOptimizationStrategy(), entity.getSalesEffectivenessStrategy(), entity.getRevenueDiversificationStrategy(), entity.getExecutiveRevenueDecision(), entity.getRevenueGrowthScore(), entity.getRecurringRevenueScore(), entity.getCustomerRevenueScore(), entity.getPricingOptimizationScore(), entity.getSalesEffectivenessScore(), entity.getRevenueDiversificationScore(), entity.getForecastAccuracyScore(), entity.getExecutionConfidenceScore(), entity.getEnterpriseRevenueIntelligenceScore(), entity.getPriority(), entity.getStatus(), entity.getCreatedBy(), entity.getCreatedAt(), entity.getUpdatedAt()); }
}
