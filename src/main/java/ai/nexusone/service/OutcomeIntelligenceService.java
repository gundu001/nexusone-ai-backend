package ai.nexusone.service;

import ai.nexusone.dto.*;
import ai.nexusone.entity.OutcomeRecord;
import ai.nexusone.enums.OutcomeStatus;
import ai.nexusone.exception.OutcomeNotFoundException;
import ai.nexusone.repository.OutcomeRecordRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OutcomeIntelligenceService {
 private final OutcomeRecordRepository repository;
 public OutcomeIntelligenceService(OutcomeRecordRepository repository){this.repository=repository;}

 @Transactional public OutcomeResponse recordOutcome(OutcomeRequest r){
   OutcomeRecord e=new OutcomeRecord();
   e.setExecutionId(r.executionId()); e.setDecisionId(r.decisionId()); e.setOutcomeName(r.outcomeName());
   e.setApplicationName(r.applicationName()); e.setEnvironment(r.environment().toUpperCase()); e.setOutcomeType(r.outcomeType());
   e.setAvailabilityScore(r.availabilityScore()); e.setPerformanceScore(r.performanceScore());
   e.setErrorReductionScore(r.errorReductionScore()); e.setCostEfficiencyScore(r.costEfficiencyScore());
   e.setBusinessKpiScore(r.businessKpiScore()); e.setMeasuredBy(r.measuredBy()); e.setEvidence(r.evidence());
   assess(e); return toResponse(repository.save(e));
 }

 @Transactional public OutcomeResponse reassess(Long id){ OutcomeRecord e=getEntity(id); assess(e); return toResponse(repository.save(e)); }
 public OutcomeResponse get(Long id){return toResponse(getEntity(id));}
 public List<OutcomeResponse> byExecution(Long executionId){return repository.findByExecutionIdOrderByCreatedAtDesc(executionId).stream().map(this::toResponse).toList();}
 public Page<OutcomeResponse> history(Pageable p){return repository.findAll(p).map(this::toResponse);}

 public OutcomeAnalyticsResponse analytics(){
   List<OutcomeRecord> all=repository.findAll(); long total=all.size();
   long success=repository.countByStatus(OutcomeStatus.SUCCESS), partial=repository.countByStatus(OutcomeStatus.PARTIAL_SUCCESS), failed=repository.countByStatus(OutcomeStatus.FAILED);
   double score=avg(all.stream().map(OutcomeRecord::getOverallScore).toList());
   double availability=avg(all.stream().map(OutcomeRecord::getAvailabilityScore).toList());
   double performance=avg(all.stream().map(OutcomeRecord::getPerformanceScore).toList());
   double cost=avg(all.stream().map(OutcomeRecord::getCostEfficiencyScore).toList());
   double rate=total==0?0:round(success*100.0/total);
   return new OutcomeAnalyticsResponse(total,success,partial,failed,score,availability,performance,cost,rate);
 }

 private void assess(OutcomeRecord e){
   double score=round(e.getAvailabilityScore()*.25+e.getPerformanceScore()*.20+e.getErrorReductionScore()*.20+e.getCostEfficiencyScore()*.15+e.getBusinessKpiScore()*.20);
   e.setOverallScore(score);
   if(score>=80){e.setStatus(OutcomeStatus.SUCCESS);e.setSummary("Execution produced a strong measurable outcome.");e.setRecommendation("Preserve evidence and reuse this execution pattern for similar governed actions.");}
   else if(score>=55){e.setStatus(OutcomeStatus.PARTIAL_SUCCESS);e.setSummary("Execution produced a partial outcome with optimization opportunities.");e.setRecommendation("Review weak metrics, tune the execution plan and reassess the outcome.");}
   else {e.setStatus(OutcomeStatus.FAILED);e.setSummary("Execution did not achieve the required outcome threshold.");e.setRecommendation("Investigate evidence, initiate corrective action or rollback, and feed findings into future decisions.");}
 }
 private double avg(List<Double> values){return values.isEmpty()?0:round(values.stream().mapToDouble(Double::doubleValue).average().orElse(0));}
 private double round(double v){return Math.round(v*100.0)/100.0;}
 private OutcomeRecord getEntity(Long id){return repository.findById(id).orElseThrow(()->new OutcomeNotFoundException(id));}
 private OutcomeResponse toResponse(OutcomeRecord e){return new OutcomeResponse(e.getId(),e.getExecutionId(),e.getDecisionId(),e.getOutcomeName(),e.getApplicationName(),e.getEnvironment(),e.getOutcomeType(),e.getAvailabilityScore(),e.getPerformanceScore(),e.getErrorReductionScore(),e.getCostEfficiencyScore(),e.getBusinessKpiScore(),e.getOverallScore(),e.getStatus(),e.getSummary(),e.getRecommendation(),e.getEvidence(),e.getMeasuredBy(),e.getMeasuredAt(),e.getCreatedAt(),e.getUpdatedAt());}
}
