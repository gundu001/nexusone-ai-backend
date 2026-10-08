package ai.nexusone.service;
import ai.nexusone.dto.*;
import ai.nexusone.entity.AutonomousEnterpriseValuationIntelligence;
import ai.nexusone.enums.*;
import ai.nexusone.exception.AutonomousEnterpriseValuationIntelligenceNotFoundException;
import ai.nexusone.repository.AutonomousEnterpriseValuationIntelligenceRepository;
import ai.nexusone.specification.AutonomousEnterpriseValuationIntelligenceSpecification;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service @Transactional
public class AutonomousEnterpriseValuationIntelligenceService {
    private final AutonomousEnterpriseValuationIntelligenceRepository repository;
    public AutonomousEnterpriseValuationIntelligenceService(AutonomousEnterpriseValuationIntelligenceRepository repository){this.repository=repository;}
    public AutonomousEnterpriseValuationIntelligenceResponse generate(AutonomousEnterpriseValuationIntelligenceRequest r){
        var e=new AutonomousEnterpriseValuationIntelligence();
        e.setTitle(r.title()); e.setValuationIntelligenceVision(r.valuationIntelligenceVision()); e.setDiscountedCashFlowStrategy(r.discountedCashFlowStrategy()); e.setComparableCompanyAnalysis(r.comparableCompanyAnalysis()); e.setMarketValuationStrategy(r.marketValuationStrategy()); e.setAssetValuationStrategy(r.assetValuationStrategy()); e.setIntangibleAssetValuationStrategy(r.intangibleAssetValuationStrategy()); e.setShareholderValueStrategy(r.shareholderValueStrategy()); e.setExecutiveValuationDecision(r.executiveValuationDecision());
        e.setEnterpriseValueScore(r.enterpriseValueScore()); e.setEquityValueScore(r.equityValueScore()); e.setCashFlowValueScore(r.cashFlowValueScore()); e.setMarketPositionScore(r.marketPositionScore()); e.setAssetQualityScore(r.assetQualityScore()); e.setIntangibleValueScore(r.intangibleValueScore()); e.setGrowthValueScore(r.growthValueScore()); e.setInvestorConfidenceScore(r.investorConfidenceScore());
        double avg=(r.enterpriseValueScore()+r.equityValueScore()+r.cashFlowValueScore()+r.marketPositionScore()+r.assetQualityScore()+r.intangibleValueScore()+r.growthValueScore()+r.investorConfidenceScore())/8.0;
        e.setEnterpriseValuationIntelligenceScore(Math.round(avg*100.0)/100.0); e.setPriority(r.priority()); e.setCreatedBy(r.createdBy()); e.setStatus(AutonomousEnterpriseValuationIntelligenceStatus.GENERATED);
        return map(repository.save(e));
    }
    @Transactional(readOnly=true) public AutonomousEnterpriseValuationIntelligenceResponse getById(Long id){return map(find(id));}
    @Transactional(readOnly=true) public Page<AutonomousEnterpriseValuationIntelligenceResponse> getAll(Pageable p){return repository.findAll(p).map(this::map);}
    @Transactional(readOnly=true) public Page<AutonomousEnterpriseValuationIntelligenceResponse> search(String k, AutonomousEnterpriseValuationIntelligencePriority p, AutonomousEnterpriseValuationIntelligenceStatus s, Pageable pageable){return repository.findAll(AutonomousEnterpriseValuationIntelligenceSpecification.filter(k,p,s),pageable).map(this::map);}
    private AutonomousEnterpriseValuationIntelligence find(Long id){return repository.findById(id).orElseThrow(()->new AutonomousEnterpriseValuationIntelligenceNotFoundException(id));}
    private AutonomousEnterpriseValuationIntelligenceResponse map(AutonomousEnterpriseValuationIntelligence e){return new AutonomousEnterpriseValuationIntelligenceResponse(e.getId(),e.getTitle(),e.getValuationIntelligenceVision(),e.getDiscountedCashFlowStrategy(),e.getComparableCompanyAnalysis(),e.getMarketValuationStrategy(),e.getAssetValuationStrategy(),e.getIntangibleAssetValuationStrategy(),e.getShareholderValueStrategy(),e.getExecutiveValuationDecision(),e.getEnterpriseValueScore(),e.getEquityValueScore(),e.getCashFlowValueScore(),e.getMarketPositionScore(),e.getAssetQualityScore(),e.getIntangibleValueScore(),e.getGrowthValueScore(),e.getInvestorConfidenceScore(),e.getEnterpriseValuationIntelligenceScore(),e.getPriority(),e.getStatus(),e.getCreatedBy(),e.getCreatedAt(),e.getUpdatedAt());}
}
