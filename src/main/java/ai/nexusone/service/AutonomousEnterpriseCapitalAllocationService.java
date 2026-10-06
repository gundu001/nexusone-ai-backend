package ai.nexusone.service;

import ai.nexusone.dto.AutonomousEnterpriseCapitalAllocationRequest;
import ai.nexusone.dto.AutonomousEnterpriseCapitalAllocationResponse;
import ai.nexusone.entity.AutonomousEnterpriseCapitalAllocation;
import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationPriority;
import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationStatus;
import ai.nexusone.exception.AutonomousEnterpriseCapitalAllocationNotFoundException;
import ai.nexusone.repository.AutonomousEnterpriseCapitalAllocationRepository;
import ai.nexusone.specification.AutonomousEnterpriseCapitalAllocationSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AutonomousEnterpriseCapitalAllocationService {

    private final AutonomousEnterpriseCapitalAllocationRepository repository;

    public AutonomousEnterpriseCapitalAllocationService(
            AutonomousEnterpriseCapitalAllocationRepository repository) {
        this.repository = repository;
    }

    public AutonomousEnterpriseCapitalAllocationResponse generate(
            AutonomousEnterpriseCapitalAllocationRequest request) {

        AutonomousEnterpriseCapitalAllocation entity =
                new AutonomousEnterpriseCapitalAllocation();

        entity.setTitle(request.title());
        entity.setCapitalAllocationVision(request.capitalAllocationVision());
        entity.setInvestmentPortfolio(request.investmentPortfolio());
        entity.setStrategicPriorities(request.strategicPriorities());
        entity.setFundingRecommendation(request.fundingRecommendation());
        entity.setRiskAdjustedAllocation(request.riskAdjustedAllocation());
        entity.setLiquidityAssessment(request.liquidityAssessment());
        entity.setReturnOptimizationAnalysis(request.returnOptimizationAnalysis());
        entity.setExecutiveCapitalDecision(request.executiveCapitalDecision());
        entity.setStrategicAlignmentScore(request.strategicAlignmentScore());
        entity.setExpectedReturnScore(request.expectedReturnScore());
        entity.setRiskAdjustedReturnScore(request.riskAdjustedReturnScore());
        entity.setLiquidityScore(request.liquidityScore());
        entity.setPortfolioBalanceScore(request.portfolioBalanceScore());
        entity.setGrowthCapacityScore(request.growthCapacityScore());
        entity.setResilienceScore(request.resilienceScore());
        entity.setCapitalEfficiencyScore(request.capitalEfficiencyScore());

        double averageScore = (
                request.strategicAlignmentScore()
                        + request.expectedReturnScore()
                        + request.riskAdjustedReturnScore()
                        + request.liquidityScore()
                        + request.portfolioBalanceScore()
                        + request.growthCapacityScore()
                        + request.resilienceScore()
                        + request.capitalEfficiencyScore()
        ) / 8.0;

        entity.setEnterpriseCapitalAllocationScore(round(averageScore));
        entity.setPriority(request.priority());
        entity.setCreatedBy(request.createdBy());
        entity.setStatus(AutonomousEnterpriseCapitalAllocationStatus.GENERATED);

        return mapToResponse(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public AutonomousEnterpriseCapitalAllocationResponse getById(Long id) {
        return mapToResponse(find(id));
    }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseCapitalAllocationResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseCapitalAllocationResponse> search(
            String keyword,
            AutonomousEnterpriseCapitalAllocationPriority priority,
            AutonomousEnterpriseCapitalAllocationStatus status,
            Pageable pageable) {
        return repository.findAll(
                        AutonomousEnterpriseCapitalAllocationSpecification.filter(
                                keyword,
                                priority,
                                status
                        ),
                        pageable
                )
                .map(this::mapToResponse);
    }

    private AutonomousEnterpriseCapitalAllocation find(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new AutonomousEnterpriseCapitalAllocationNotFoundException(id));
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private AutonomousEnterpriseCapitalAllocationResponse mapToResponse(
            AutonomousEnterpriseCapitalAllocation entity) {
        return new AutonomousEnterpriseCapitalAllocationResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getCapitalAllocationVision(),
                entity.getInvestmentPortfolio(),
                entity.getStrategicPriorities(),
                entity.getFundingRecommendation(),
                entity.getRiskAdjustedAllocation(),
                entity.getLiquidityAssessment(),
                entity.getReturnOptimizationAnalysis(),
                entity.getExecutiveCapitalDecision(),
                entity.getStrategicAlignmentScore(),
                entity.getExpectedReturnScore(),
                entity.getRiskAdjustedReturnScore(),
                entity.getLiquidityScore(),
                entity.getPortfolioBalanceScore(),
                entity.getGrowthCapacityScore(),
                entity.getResilienceScore(),
                entity.getCapitalEfficiencyScore(),
                entity.getEnterpriseCapitalAllocationScore(),
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
