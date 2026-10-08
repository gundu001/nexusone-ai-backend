package ai.nexusone.service;
import ai.nexusone.dto.*;
import ai.nexusone.entity.AutonomousEnterpriseMAIntelligence;
import ai.nexusone.enums.*;
import ai.nexusone.exception.AutonomousEnterpriseMAIntelligenceNotFoundException;
import ai.nexusone.repository.AutonomousEnterpriseMAIntelligenceRepository;
import ai.nexusone.specification.AutonomousEnterpriseMAIntelligenceSpecification;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service @Transactional
public class AutonomousEnterpriseMAIntelligenceService {
    private final AutonomousEnterpriseMAIntelligenceRepository repository;
    public AutonomousEnterpriseMAIntelligenceService(AutonomousEnterpriseMAIntelligenceRepository repository){this.repository=repository;}
    public AutonomousEnterpriseMAIntelligenceResponse generate(AutonomousEnterpriseMAIntelligenceRequest r){
        var e=new AutonomousEnterpriseMAIntelligence();
        e.setTitle(r.title()); e.setValuationIntelligenceVision(r.maIntelligenceVision()); e.setDiscountedCashFlowStrategy(r.acquisitionStrategy()); e.setComparableCompanyAnalysis(r.mergerSynergyAnalysis()); e.setMarketValuationStrategy(r.targetCompanyAssessment()); e.setAssetValuationStrategy(r.financialDueDiligence()); e.setIntangibleAssetValuationStrategy(r.operationalDueDiligence()); e.setShareholderValueStrategy(r.culturalIntegrationStrategy()); e.setExecutiveValuationDecision(r.executiveMADecision());
        e.setEnterpriseValueScore(r.synergyScore()); e.setEquityValueScore(r.financialStrengthScore()); e.setCashFlowValueScore(r.strategicFitScore()); e.setMarketPositionScore(r.integrationReadinessScore()); e.setAssetQualityScore(r.riskAssessmentScore()); e.setIntangibleValueScore(r.targetQualityScore()); e.setGrowthValueScore(r.valueCreationScore()); e.setInvestorConfidenceScore(r.executionConfidenceScore());
        double avg=(r.synergyScore()+r.financialStrengthScore()+r.strategicFitScore()+r.integrationReadinessScore()+r.riskAssessmentScore()+r.targetQualityScore()+r.valueCreationScore()+r.executionConfidenceScore())/8.0;
        e.setEnterpriseValuationIntelligenceScore(Math.round(avg*100.0)/100.0); e.setPriority(r.priority()); e.setCreatedBy(r.createdBy()); e.setStatus(AutonomousEnterpriseMAIntelligenceStatus.GENERATED);
        return map(repository.save(e));
    }
    @Transactional(readOnly=true) public AutonomousEnterpriseMAIntelligenceResponse getById(Long id){return map(find(id));}
    @Transactional(readOnly=true) public Page<AutonomousEnterpriseMAIntelligenceResponse> getAll(Pageable p){return repository.findAll(p).map(this::map);}
    @Transactional(readOnly=true) public Page<AutonomousEnterpriseMAIntelligenceResponse> search(String k, AutonomousEnterpriseMAIntelligencePriority p, AutonomousEnterpriseMAIntelligenceStatus s, Pageable pageable){return repository.findAll(AutonomousEnterpriseMAIntelligenceSpecification.filter(k,p,s),pageable).map(this::map);}
    private AutonomousEnterpriseMAIntelligence find(Long id){return repository.findById(id).orElseThrow(()->new AutonomousEnterpriseMAIntelligenceNotFoundException(id));}
    private AutonomousEnterpriseMAIntelligenceResponse map(AutonomousEnterpriseMAIntelligence e){return new AutonomousEnterpriseMAIntelligenceResponse(e.getId(),e.getTitle(),e.getValuationIntelligenceVision(),e.getDiscountedCashFlowStrategy(),e.getComparableCompanyAnalysis(),e.getMarketValuationStrategy(),e.getAssetValuationStrategy(),e.getIntangibleAssetValuationStrategy(),e.getShareholderValueStrategy(),e.getExecutiveValuationDecision(),e.getEnterpriseValueScore(),e.getEquityValueScore(),e.getCashFlowValueScore(),e.getMarketPositionScore(),e.getAssetQualityScore(),e.getIntangibleValueScore(),e.getGrowthValueScore(),e.getInvestorConfidenceScore(),e.getEnterpriseValuationIntelligenceScore(),e.getPriority(),e.getStatus(),e.getCreatedBy(),e.getCreatedAt(),e.getUpdatedAt());}
}
