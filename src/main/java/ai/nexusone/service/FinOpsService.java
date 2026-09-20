package ai.nexusone.service;

import ai.nexusone.dto.request.FinOpsAssessmentRequest;
import ai.nexusone.dto.response.FinOpsOverviewResponse;
import ai.nexusone.entity.FinOpsAssessment;
import ai.nexusone.repository.FinOpsAssessmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service @Transactional
public class FinOpsService {
 private final FinOpsAssessmentRepository repository;
 public FinOpsService(FinOpsAssessmentRepository repository){this.repository=repository;}

 public FinOpsAssessment analyze(FinOpsAssessmentRequest r){
  if(r.monthlyBudget()<=0) throw new IllegalArgumentException("monthlyBudget must be greater than zero");
  double budgetUtil=r.monthlySpend()*100.0/r.monthlyBudget();
  double spendChange=r.previousMonthSpend()==0?0:(r.monthlySpend()-r.previousMonthSpend())*100.0/r.previousMonthSpend();
  double forecast=Math.max(0,r.monthlySpend()*(1+Math.max(-50,Math.min(100,spendChange))/100.0));
  double idleSavings=r.monthlySpend()*Math.min(30,r.idleResources()*3)/100.0;
  double rightsizeSavings=r.monthlySpend()*Math.min(25,r.rightsizingCandidates()*2)/100.0;
  double storageSavings=r.monthlySpend()*(r.storageWastePercent()/100.0)*0.15;
  double savings=Math.min(r.monthlySpend(),idleSavings+rightsizeSavings+storageSavings);
  int costScore=(int)Math.round(Math.max(0,100-Math.max(0,budgetUtil-70)*1.8-Math.max(0,spendChange)*0.5));
  int optimization=(int)Math.round(Math.max(0,100-r.idleResources()*5-r.rightsizingCandidates()*3-r.storageWastePercent()*0.5+(r.reservedCapacityCoveragePercent()+r.taggedResourceCoveragePercent())*0.15));
  optimization=Math.min(100,optimization);
  String budgetStatus=budgetUtil>100||forecast>r.monthlyBudget()?"OVER_BUDGET":budgetUtil>=85?"AT_RISK":"HEALTHY";
  String risk="OVER_BUDGET".equals(budgetStatus)?"HIGH":"AT_RISK".equals(budgetStatus)?"MEDIUM":"LOW";
  String action=action(r,budgetStatus);
  FinOpsAssessment x=new FinOpsAssessment();
  x.setApplicationName(r.applicationName());x.setEnvironment(r.environment());x.setCloudProvider(r.cloudProvider());x.setCurrency(r.currency());
  x.setMonthlySpend(round(r.monthlySpend()));x.setMonthlyBudget(round(r.monthlyBudget()));x.setPreviousMonthSpend(round(r.previousMonthSpend()));x.setIdleResources(r.idleResources());x.setRightsizingCandidates(r.rightsizingCandidates());x.setStorageWastePercent(round(r.storageWastePercent()));x.setReservedCapacityCoveragePercent(round(r.reservedCapacityCoveragePercent()));x.setTaggedResourceCoveragePercent(round(r.taggedResourceCoveragePercent()));
  x.setBudgetUtilizationPercent(round(budgetUtil));x.setSpendChangePercent(round(spendChange));x.setForecastedMonthlySpend(round(forecast));x.setSavingsOpportunity(round(savings));x.setCostEfficiencyScore(costScore);x.setOptimizationScore(optimization);x.setBudgetStatus(budgetStatus);x.setRiskLevel(risk);x.setRecommendedAction(action);x.setRecommendation(recommendation(action));x.setOptimizationPlan(plan(action));
  return repository.save(x);
 }

 @Transactional(readOnly=true) public FinOpsOverviewResponse overview(){
  List<FinOpsAssessment> all=repository.findAll();
  double spend=all.stream().mapToDouble(FinOpsAssessment::getMonthlySpend).sum();
  double budget=all.stream().mapToDouble(FinOpsAssessment::getMonthlyBudget).sum();
  double forecast=all.stream().mapToDouble(FinOpsAssessment::getForecastedMonthlySpend).sum();
  double savings=all.stream().mapToDouble(FinOpsAssessment::getSavingsOpportunity).sum();
  double score=all.stream().mapToInt(FinOpsAssessment::getOptimizationScore).average().orElse(0);
  long risk=repository.countByBudgetStatus("AT_RISK")+repository.countByBudgetStatus("OVER_BUDGET");
  long critical=repository.countByRiskLevel("HIGH");
  String currency=all.isEmpty()?"USD":all.get(0).getCurrency();
  String status=all.isEmpty()?"NO_DATA":critical>0?"COST_ACTION_REQUIRED":risk>0?"BUDGET_RISK":"COST_OPTIMIZED";
  return new FinOpsOverviewResponse(all.size(),round(spend),round(budget),round(forecast),round(savings),round(score),risk,critical,currency,status);
 }
 @Transactional(readOnly=true) public Page<FinOpsAssessment> history(Pageable p){return repository.findAll(p);}
 @Transactional(readOnly=true) public List<FinOpsAssessment> recommendations(){return repository.findTop10ByOrderByAssessedAtDesc();}
 @Transactional(readOnly=true) public FinOpsAssessment get(Long id){return repository.findById(id).orElseThrow(()->new NoSuchElementException("FinOps assessment not found: "+id));}
 private String action(FinOpsAssessmentRequest r,String status){if("OVER_BUDGET".equals(status))return "ENFORCE_BUDGET_REMEDIATION";if(r.idleResources()>0)return "REMOVE_IDLE_RESOURCES";if(r.rightsizingCandidates()>0)return "RIGHTSIZE_RESOURCES";if(r.storageWastePercent()>=10)return "OPTIMIZE_STORAGE";if(r.reservedCapacityCoveragePercent()<70)return "INCREASE_RESERVED_CAPACITY";if(r.taggedResourceCoveragePercent()<90)return "IMPROVE_COST_ALLOCATION_TAGS";return "CONTINUE_COST_MONITORING";}
 private String recommendation(String a){return switch(a){case"ENFORCE_BUDGET_REMEDIATION"->"Freeze non-essential growth, review cost anomalies and assign accountable owners.";case"REMOVE_IDLE_RESOURCES"->"Validate and stop or remove idle resources after owner approval.";case"RIGHTSIZE_RESOURCES"->"Review utilization and resize over-provisioned compute resources.";case"OPTIMIZE_STORAGE"->"Apply lifecycle, retention and tiering policies to reduce storage waste.";case"INCREASE_RESERVED_CAPACITY"->"Evaluate stable workloads for committed-use or reserved-capacity coverage.";case"IMPROVE_COST_ALLOCATION_TAGS"->"Improve application, owner, environment and cost-center tag coverage.";default->"Cost indicators are healthy. Continue budget and anomaly monitoring.";};}
 private String plan(String a){return "Validate billing data; confirm resource ownership; model savings; obtain approval; apply optimization; verify service health; record realized savings. Recommended action: "+a;}
 private double round(double v){return Math.round(v*100.0)/100.0;}
}
