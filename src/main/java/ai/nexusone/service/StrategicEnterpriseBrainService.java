package ai.nexusone.service;
import ai.nexusone.dto.*; import ai.nexusone.entity.*; import ai.nexusone.enums.*; import ai.nexusone.exception.*; import ai.nexusone.repository.*;
import java.time.LocalDateTime; import java.util.*; import java.util.function.ToDoubleFunction;
import org.springframework.data.domain.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service public class StrategicEnterpriseBrainService{
 private final StrategicEnterpriseBrainRepository repo; public StrategicEnterpriseBrainService(StrategicEnterpriseBrainRepository r){repo=r;}
 @Transactional public StrategicEnterpriseBrainResponse generate(StrategicEnterpriseBrainRequest q){StrategicEnterpriseBrainReport r=new StrategicEnterpriseBrainReport();r.setTitle(q.title());
        r.setStrategicVision(q.strategicVision()); r.setStrategicVisionScore(q.strategicVisionScore());
        r.setEnterpriseContext(q.enterpriseContext()); r.setEnterpriseContextScore(q.enterpriseContextScore());
        r.setDecisionIntelligence(q.decisionIntelligence()); r.setDecisionIntelligenceScore(q.decisionIntelligenceScore());
        r.setScenarioPlanning(q.scenarioPlanning()); r.setScenarioPlanningScore(q.scenarioPlanningScore());
        r.setRiskAnticipation(q.riskAnticipation()); r.setRiskAnticipationScore(q.riskAnticipationScore());
        r.setExecutionAlignment(q.executionAlignment()); r.setExecutionAlignmentScore(q.executionAlignmentScore());
        r.setLearningAdaptation(q.learningAdaptation()); r.setLearningAdaptationScore(q.learningAdaptationScore());
        r.setInnovationIntelligence(q.innovationIntelligence()); r.setInnovationIntelligenceScore(q.innovationIntelligenceScore());
        r.setValueOrchestration(q.valueOrchestration()); r.setValueOrchestrationScore(q.valueOrchestrationScore());
        r.setStrategicRecommendations(q.strategicRecommendations());r.setStrategicEnterpriseBrainScore(score(q));r.setPriority(q.priority());r.setStatus(StrategicEnterpriseBrainStatus.GENERATED);r.setCreatedBy(q.createdBy());return map(repo.save(r));}
 @Transactional(readOnly=true) public StrategicEnterpriseBrainResponse get(Long id){return map(find(id));}
 @Transactional(readOnly=true) public Page<StrategicEnterpriseBrainResponse> history(Pageable p){return repo.findAll(p).map(this::map);}
 @Transactional(readOnly=true) public Page<StrategicEnterpriseBrainResponse> search(String k,StrategicEnterpriseBrainPriority p,StrategicEnterpriseBrainStatus s,Pageable pg){if(k!=null&&!k.isBlank())return repo.findByTitleContainingIgnoreCaseOrStrategicVisionContainingIgnoreCaseOrStrategicRecommendationsContainingIgnoreCase(k,k,k,pg).map(this::map);if(p!=null)return repo.findByPriority(p,pg).map(this::map);if(s!=null)return repo.findByStatus(s,pg).map(this::map);return history(pg);}
 @Transactional public StrategicEnterpriseBrainResponse review(Long id,StrategicEnterpriseBrainActionRequest q){var r=find(id);require(r,StrategicEnterpriseBrainStatus.GENERATED,"Only GENERATED reports can be reviewed");r.setStatus(StrategicEnterpriseBrainStatus.REVIEWED);r.setReviewedBy(q.actionBy());r.setReviewedAt(LocalDateTime.now());return map(repo.save(r));}
 @Transactional public StrategicEnterpriseBrainResponse approve(Long id,StrategicEnterpriseBrainActionRequest q){var r=find(id);require(r,StrategicEnterpriseBrainStatus.REVIEWED,"Only REVIEWED reports can be approved");r.setStatus(StrategicEnterpriseBrainStatus.APPROVED);r.setDecidedBy(q.actionBy());r.setDecidedAt(LocalDateTime.now());return map(repo.save(r));}
 @Transactional public StrategicEnterpriseBrainResponse reject(Long id,StrategicEnterpriseBrainActionRequest q){var r=find(id);if(r.getStatus()!=StrategicEnterpriseBrainStatus.GENERATED&&r.getStatus()!=StrategicEnterpriseBrainStatus.REVIEWED)throw new IllegalArgumentException("Only GENERATED or REVIEWED reports can be rejected");r.setStatus(StrategicEnterpriseBrainStatus.REJECTED);r.setDecidedBy(q.actionBy());r.setDecidedAt(LocalDateTime.now());return map(repo.save(r));}
 @Transactional public StrategicEnterpriseBrainResponse publish(Long id,StrategicEnterpriseBrainActionRequest q){var r=find(id);require(r,StrategicEnterpriseBrainStatus.APPROVED,"Only APPROVED reports can be published");r.setStatus(StrategicEnterpriseBrainStatus.PUBLISHED);r.setPublishedBy(q.actionBy());r.setPublishedAt(LocalDateTime.now());return map(repo.save(r));}
 @Transactional(readOnly=true) public StrategicEnterpriseBrainAnalyticsResponse analytics(){var all=repo.findAll();return new StrategicEnterpriseBrainAnalyticsResponse(all.size(),count(StrategicEnterpriseBrainStatus.GENERATED),count(StrategicEnterpriseBrainStatus.REVIEWED),count(StrategicEnterpriseBrainStatus.APPROVED),count(StrategicEnterpriseBrainStatus.REJECTED),count(StrategicEnterpriseBrainStatus.PUBLISHED),
                avg(all,StrategicEnterpriseBrainReport::getStrategicVisionScore),
                avg(all,StrategicEnterpriseBrainReport::getEnterpriseContextScore),
                avg(all,StrategicEnterpriseBrainReport::getDecisionIntelligenceScore),
                avg(all,StrategicEnterpriseBrainReport::getScenarioPlanningScore),
                avg(all,StrategicEnterpriseBrainReport::getRiskAnticipationScore),
                avg(all,StrategicEnterpriseBrainReport::getExecutionAlignmentScore),
                avg(all,StrategicEnterpriseBrainReport::getLearningAdaptationScore),
                avg(all,StrategicEnterpriseBrainReport::getInnovationIntelligenceScore),
                avg(all,StrategicEnterpriseBrainReport::getValueOrchestrationScore),avg(all,StrategicEnterpriseBrainReport::getStrategicEnterpriseBrainScore));}
 private double score(StrategicEnterpriseBrainRequest q){return round(q.strategicVisionScore()*0.14
                + q.enterpriseContextScore()*0.13
                + q.decisionIntelligenceScore()*0.12
                + q.scenarioPlanningScore()*0.11
                + q.riskAnticipationScore()*0.11
                + q.executionAlignmentScore()*0.11
                + q.learningAdaptationScore()*0.10
                + q.innovationIntelligenceScore()*0.09
                + q.valueOrchestrationScore()*0.09);}
 private double avg(List<StrategicEnterpriseBrainReport>a,ToDoubleFunction<StrategicEnterpriseBrainReport>f){return round(a.stream().mapToDouble(f).average().orElse(0));} private long count(StrategicEnterpriseBrainStatus s){return repo.countByStatus(s);} private double round(double v){return Math.round(v*100.0)/100.0;}
 private void require(StrategicEnterpriseBrainReport r,StrategicEnterpriseBrainStatus s,String m){if(r.getStatus()!=s)throw new IllegalArgumentException(m);} private StrategicEnterpriseBrainReport find(Long id){return repo.findById(id).orElseThrow(()->new StrategicEnterpriseBrainNotFoundException(id));}
 private StrategicEnterpriseBrainResponse map(StrategicEnterpriseBrainReport r){return new StrategicEnterpriseBrainResponse(r.getId(),r.getTitle(),
                r.getStrategicVision(),
                r.getEnterpriseContext(),
                r.getDecisionIntelligence(),
                r.getScenarioPlanning(),
                r.getRiskAnticipation(),
                r.getExecutionAlignment(),
                r.getLearningAdaptation(),
                r.getInnovationIntelligence(),
                r.getValueOrchestration(),
                r.getStrategicRecommendations(),
                r.getStrategicVisionScore(),
                r.getEnterpriseContextScore(),
                r.getDecisionIntelligenceScore(),
                r.getScenarioPlanningScore(),
                r.getRiskAnticipationScore(),
                r.getExecutionAlignmentScore(),
                r.getLearningAdaptationScore(),
                r.getInnovationIntelligenceScore(),
                r.getValueOrchestrationScore(),r.getStrategicEnterpriseBrainScore(),r.getPriority(),r.getStatus(),r.getCreatedBy(),r.getReviewedBy(),r.getReviewedAt(),r.getDecidedBy(),r.getDecidedAt(),r.getPublishedBy(),r.getPublishedAt(),r.getCreatedAt(),r.getUpdatedAt());}
}
