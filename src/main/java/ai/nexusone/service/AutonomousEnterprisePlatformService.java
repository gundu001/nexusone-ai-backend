package ai.nexusone.service;

import ai.nexusone.dto.request.AutonomousEnterpriseRequest;
import ai.nexusone.dto.response.AutonomousEnterpriseOverviewResponse;
import ai.nexusone.entity.AutonomousEnterpriseAssessment;
import ai.nexusone.repository.AutonomousEnterpriseAssessmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service @Transactional
public class AutonomousEnterprisePlatformService {
 private final AutonomousEnterpriseAssessmentRepository repository;
 public AutonomousEnterprisePlatformService(AutonomousEnterpriseAssessmentRepository repository){this.repository=repository;}

 public AutonomousEnterpriseAssessment analyze(AutonomousEnterpriseRequest r){
  int health=clamp((int)Math.round(r.sreScore()*.20+r.platformScore()*.18+r.governanceSecurityScore()*.17+r.finOpsScore()*.10+r.disasterRecoveryScore()*.15+r.autonomousOperationsScore()*.10+r.executiveReadinessScore()*.10));
  int readiness=clamp((int)Math.round(r.sreScore()*.15+r.platformScore()*.15+r.governanceSecurityScore()*.20+r.disasterRecoveryScore()*.20+r.autonomousOperationsScore()*.15+r.executiveReadinessScore()*.15));
  int autonomy=clamp((int)Math.round(r.autonomousOperationsScore()*.35+r.platformScore()*.15+r.sreScore()*.15+r.governanceSecurityScore()*.15+r.executiveReadinessScore()*.20));
  int risk=clamp(100-health+r.openCriticalRisks()*15+r.pendingGovernedActions()*3);
  String riskLevel=risk>=60?"CRITICAL":risk>=35?"HIGH":risk>=20?"MEDIUM":"LOW";
  String status="CRITICAL".equals(riskLevel)?"EXECUTIVE_INTERVENTION_REQUIRED":r.pendingGovernedActions()>0?"GOVERNED_ACTION_PENDING":health>=85&&readiness>=85?"ENTERPRISE_READY":"IMPROVEMENT_REQUIRED";
  String decision=decision(r,health,readiness);
  AutonomousEnterpriseAssessment x=new AutonomousEnterpriseAssessment();
  x.setOrganizationName(r.organizationName());x.setEnvironment(r.environment());x.setSreScore(r.sreScore());x.setPlatformScore(r.platformScore());x.setGovernanceSecurityScore(r.governanceSecurityScore());x.setFinOpsScore(r.finOpsScore());x.setDisasterRecoveryScore(r.disasterRecoveryScore());x.setAutonomousOperationsScore(r.autonomousOperationsScore());x.setExecutiveReadinessScore(r.executiveReadinessScore());x.setOpenCriticalRisks(r.openCriticalRisks());x.setPendingGovernedActions(r.pendingGovernedActions());x.setEnterpriseHealthScore(health);x.setEnterpriseRiskScore(risk);x.setEnterpriseReadinessScore(readiness);x.setAutonomyMaturityScore(autonomy);x.setRiskLevel(riskLevel);x.setStatus(status);x.setRecommendedDecision(decision);
  x.setEnterpriseSummary("Enterprise health "+health+", risk "+risk+", readiness "+readiness+", autonomy maturity "+autonomy+".");
  x.setRecommendation(recommendation(decision));x.setGovernedActionPlan("Validate evidence; review affected modules; obtain required approval; perform dry run; execute through approved adapter; verify health; record outcome and rollback evidence.");
  return repository.save(x);
 }

 @Transactional(readOnly=true) public AutonomousEnterpriseOverviewResponse overview(){
  List<AutonomousEnterpriseAssessment> all=repository.findAll();
  double health=all.stream().mapToInt(AutonomousEnterpriseAssessment::getEnterpriseHealthScore).average().orElse(0),risk=all.stream().mapToInt(AutonomousEnterpriseAssessment::getEnterpriseRiskScore).average().orElse(0),readiness=all.stream().mapToInt(AutonomousEnterpriseAssessment::getEnterpriseReadinessScore).average().orElse(0),autonomy=all.stream().mapToInt(AutonomousEnterpriseAssessment::getAutonomyMaturityScore).average().orElse(0);
  long critical=repository.countByRiskLevel("CRITICAL"),pending=repository.countByStatus("GOVERNED_ACTION_PENDING");
  String state=all.isEmpty()?"NO_DATA":critical>0?"EXECUTIVE_INTERVENTION_REQUIRED":pending>0?"GOVERNED_ACTION_PENDING":health>=85&&readiness>=85?"ENTERPRISE_READY":"IMPROVEMENT_REQUIRED";
  return new AutonomousEnterpriseOverviewResponse(all.size(),round(health),round(risk),round(readiness),round(autonomy),critical,pending,state);
 }
 @Transactional(readOnly=true) public Page<AutonomousEnterpriseAssessment> history(Pageable p){return repository.findAll(p);}
 @Transactional(readOnly=true) public List<AutonomousEnterpriseAssessment> recommendations(){return repository.findTop10ByOrderByCreatedAtDesc();}
 @Transactional(readOnly=true) public AutonomousEnterpriseAssessment get(Long id){return repository.findById(id).orElseThrow(()->new NoSuchElementException("Autonomous enterprise assessment not found: "+id));}
 private String decision(AutonomousEnterpriseRequest r,int health,int readiness){if(r.openCriticalRisks()>0)return "RESOLVE_CRITICAL_ENTERPRISE_RISKS";if(r.governanceSecurityScore()<80)return "STRENGTHEN_GOVERNANCE_SECURITY";if(r.disasterRecoveryScore()<80)return "IMPROVE_ENTERPRISE_RESILIENCE";if(r.finOpsScore()<80)return "ACCELERATE_COST_OPTIMIZATION";if(r.sreScore()<80||r.platformScore()<80)return "IMPROVE_PLATFORM_RELIABILITY";if(r.pendingGovernedActions()>0)return "COMPLETE_GOVERNED_ACTIONS";return health>=85&&readiness>=85?"CONTINUE_AUTONOMOUS_ENTERPRISE_OPERATIONS":"PRIORITIZE_ENTERPRISE_IMPROVEMENTS";}
 private String recommendation(String d){return switch(d){case"RESOLVE_CRITICAL_ENTERPRISE_RISKS"->"Assign executive owners and resolve critical enterprise risks before autonomous execution.";case"STRENGTHEN_GOVERNANCE_SECURITY"->"Remediate governance and security gaps before expanding automation.";case"IMPROVE_ENTERPRISE_RESILIENCE"->"Improve recovery coverage, dependency readiness and continuity validation.";case"ACCELERATE_COST_OPTIMIZATION"->"Prioritize verified FinOps opportunities and track realized savings.";case"IMPROVE_PLATFORM_RELIABILITY"->"Improve SRE performance, platform capacity and operational automation.";case"COMPLETE_GOVERNED_ACTIONS"->"Review pending governed actions, obtain approvals and record outcomes.";default->"Enterprise indicators support continued governed autonomous operations.";};}
 private static int clamp(int v){return Math.max(0,Math.min(100,v));}
 private static double round(double v){return Math.round(v*100.0)/100.0;}
}
