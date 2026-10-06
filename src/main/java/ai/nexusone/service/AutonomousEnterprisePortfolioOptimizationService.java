package ai.nexusone.service;

import ai.nexusone.dto.AutonomousEnterprisePortfolioOptimizationRequest;
import ai.nexusone.dto.AutonomousEnterprisePortfolioOptimizationResponse;
import ai.nexusone.entity.AutonomousEnterprisePortfolioOptimization;
import ai.nexusone.enums.AutonomousEnterprisePortfolioOptimizationStatus;
import ai.nexusone.exception.AutonomousEnterprisePortfolioOptimizationNotFoundException;
import ai.nexusone.repository.AutonomousEnterprisePortfolioOptimizationRepository;

import ai.nexusone.enums.AutonomousEnterprisePortfolioOptimizationPriority;
import ai.nexusone.specification.AutonomousEnterprisePortfolioOptimizationSpecification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AutonomousEnterprisePortfolioOptimizationService {

    private final AutonomousEnterprisePortfolioOptimizationRepository repository;

    public AutonomousEnterprisePortfolioOptimizationService(
            AutonomousEnterprisePortfolioOptimizationRepository repository) {
        this.repository = repository;
    }

    public AutonomousEnterprisePortfolioOptimizationResponse generate(
            AutonomousEnterprisePortfolioOptimizationRequest request) {

        AutonomousEnterprisePortfolioOptimization entity =
                new AutonomousEnterprisePortfolioOptimization();

        entity.setTitle(request.title());
        entity.setValueRealizationVision(request.portfolioOptimizationVision());
        entity.setInitiativePortfolio(request.investmentPortfolio());
        entity.setStrategicObjective(request.strategicObjective());
        entity.setExpectedBusinessValue(request.rebalanceRecommendation());
        entity.setRealizedBusinessValue(request.dependencyAnalysis());
        entity.setValueLeakageAnalysis(request.concentrationRiskAnalysis());
        entity.setRoiAssessment(request.returnOptimizationAnalysis());
        entity.setExecutiveOutcomeAnalysis(request.executivePortfolioDecision());

        entity.setExpectedValueScore(request.strategicAlignmentScore());
        entity.setRealizedValueScore(request.expectedReturnScore());
        entity.setRoiScore(request.riskAdjustedValueScore());
        entity.setProductivityScore(request.portfolioBalanceScore());
        entity.setEfficiencyScore(request.dependencyHealthScore());
        entity.setInnovationScore(request.deliveryCapacityScore());
        entity.setCustomerImpactScore(request.resilienceScore());
        entity.setFinancialImpactScore(request.optimizationScore());

        double averageScore =
                (
                        request.strategicAlignmentScore()
                                + request.expectedReturnScore()
                                + request.riskAdjustedValueScore()
                                + request.portfolioBalanceScore()
                                + request.dependencyHealthScore()
                                + request.deliveryCapacityScore()
                                + request.resilienceScore()
                                + request.optimizationScore()
                ) / 8.0;

        entity.setEnterpriseValueScore(round(averageScore));

        entity.setPriority(request.priority());
        entity.setCreatedBy(request.createdBy());
        entity.setStatus(
                AutonomousEnterprisePortfolioOptimizationStatus.GENERATED);

        return mapToResponse(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public AutonomousEnterprisePortfolioOptimizationResponse getById(Long id) {
        return mapToResponse(find(id));
    }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterprisePortfolioOptimizationResponse> getAll(
            Pageable pageable) {

        return repository.findAll(pageable)
                .map(this::mapToResponse);
    }

    private AutonomousEnterprisePortfolioOptimization find(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new AutonomousEnterprisePortfolioOptimizationNotFoundException(id));
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterprisePortfolioOptimizationResponse> search(
            String keyword,
            AutonomousEnterprisePortfolioOptimizationPriority priority,
            AutonomousEnterprisePortfolioOptimizationStatus status,
            Pageable pageable) {

        return repository.findAll(
                        AutonomousEnterprisePortfolioOptimizationSpecification.filter(
                                keyword,
                                priority,
                                status),
                        pageable)
                .map(this::mapToResponse);
    }

    private AutonomousEnterprisePortfolioOptimizationResponse mapToResponse(
            AutonomousEnterprisePortfolioOptimization entity) {

        return new AutonomousEnterprisePortfolioOptimizationResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getValueRealizationVision(),
                entity.getInitiativePortfolio(),
                entity.getStrategicObjective(),
                entity.getExpectedBusinessValue(),
                entity.getRealizedBusinessValue(),
                entity.getValueLeakageAnalysis(),
                entity.getRoiAssessment(),
                entity.getExecutiveOutcomeAnalysis(),
                entity.getExpectedValueScore(),
                entity.getRealizedValueScore(),
                entity.getRoiScore(),
                entity.getProductivityScore(),
                entity.getEfficiencyScore(),
                entity.getInnovationScore(),
                entity.getCustomerImpactScore(),
                entity.getFinancialImpactScore(),
                entity.getEnterpriseValueScore(),
                entity.getPriority(),
                entity.getStatus(),
                entity.getCreatedBy(),
                entity.getReviewedBy(),
                entity.getReviewedAt(),
                entity.getDecidedBy(),
                entity.getDecidedAt(),
                entity.getPublishedBy(),
                entity.getPublishedAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}