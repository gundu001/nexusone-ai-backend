package ai.nexusone.service;

import ai.nexusone.dto.AutonomousEnterpriseValueRealizationRequest;
import ai.nexusone.dto.AutonomousEnterpriseValueRealizationResponse;
import ai.nexusone.entity.AutonomousEnterpriseValueRealization;
import ai.nexusone.enums.AutonomousEnterpriseValueRealizationStatus;
import ai.nexusone.exception.AutonomousEnterpriseValueRealizationNotFoundException;
import ai.nexusone.repository.AutonomousEnterpriseValueRealizationRepository;

import ai.nexusone.enums.AutonomousEnterpriseValueRealizationPriority;
import ai.nexusone.specification.AutonomousEnterpriseValueRealizationSpecification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AutonomousEnterpriseValueRealizationService {

    private final AutonomousEnterpriseValueRealizationRepository repository;

    public AutonomousEnterpriseValueRealizationService(
            AutonomousEnterpriseValueRealizationRepository repository) {
        this.repository = repository;
    }

    public AutonomousEnterpriseValueRealizationResponse generate(
            AutonomousEnterpriseValueRealizationRequest request) {

        AutonomousEnterpriseValueRealization entity =
                new AutonomousEnterpriseValueRealization();

        entity.setTitle(request.title());
        entity.setValueRealizationVision(request.valueRealizationVision());
        entity.setInitiativePortfolio(request.initiativePortfolio());
        entity.setStrategicObjective(request.strategicObjective());
        entity.setExpectedBusinessValue(request.expectedBusinessValue());
        entity.setRealizedBusinessValue(request.realizedBusinessValue());
        entity.setValueLeakageAnalysis(request.valueLeakageAnalysis());
        entity.setRoiAssessment(request.roiAssessment());
        entity.setExecutiveOutcomeAnalysis(request.executiveOutcomeAnalysis());

        entity.setExpectedValueScore(request.expectedValueScore());
        entity.setRealizedValueScore(request.realizedValueScore());
        entity.setRoiScore(request.roiScore());
        entity.setProductivityScore(request.productivityScore());
        entity.setEfficiencyScore(request.efficiencyScore());
        entity.setInnovationScore(request.innovationScore());
        entity.setCustomerImpactScore(request.customerImpactScore());
        entity.setFinancialImpactScore(request.financialImpactScore());

        double averageScore =
                (
                        request.expectedValueScore()
                                + request.realizedValueScore()
                                + request.roiScore()
                                + request.productivityScore()
                                + request.efficiencyScore()
                                + request.innovationScore()
                                + request.customerImpactScore()
                                + request.financialImpactScore()
                ) / 8.0;

        entity.setEnterpriseValueScore(round(averageScore));

        entity.setPriority(request.priority());
        entity.setCreatedBy(request.createdBy());
        entity.setStatus(
                AutonomousEnterpriseValueRealizationStatus.GENERATED);

        return mapToResponse(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public AutonomousEnterpriseValueRealizationResponse getById(Long id) {
        return mapToResponse(find(id));
    }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseValueRealizationResponse> getAll(
            Pageable pageable) {

        return repository.findAll(pageable)
                .map(this::mapToResponse);
    }

    private AutonomousEnterpriseValueRealization find(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new AutonomousEnterpriseValueRealizationNotFoundException(id));
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Transactional(readOnly = true)
    public Page<AutonomousEnterpriseValueRealizationResponse> search(
            String keyword,
            AutonomousEnterpriseValueRealizationPriority priority,
            AutonomousEnterpriseValueRealizationStatus status,
            Pageable pageable) {

        return repository.findAll(
                        AutonomousEnterpriseValueRealizationSpecification.filter(
                                keyword,
                                priority,
                                status),
                        pageable)
                .map(this::mapToResponse);
    }

    private AutonomousEnterpriseValueRealizationResponse mapToResponse(
            AutonomousEnterpriseValueRealization entity) {

        return new AutonomousEnterpriseValueRealizationResponse(
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