package ai.nexusone.service;
import ai.nexusone.dto.request.*;
import ai.nexusone.dto.response.DisasterRecoveryOverviewResponse;
import ai.nexusone.entity.*;
import ai.nexusone.repository.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class DisasterRecoveryService {
 private final DisasterRecoveryAssessmentRepository assessments; private final RecoverySimulationRepository simulations; private final BusinessImpactAnalysisRepository impacts;
 public DisasterRecoveryService(DisasterRecoveryAssessmentRepository a,RecoverySimulationRepository s,BusinessImpactAnalysisRepository i){assessments=a;simulations=s;impacts=i;}
 public DisasterRecoveryAssessment assess(RecoveryAssessmentRequest r){
  int score=(int)Math.round(r.backupScore()*.35+r.replicationScore()*.30+r.failoverScore()*.35);
  DisasterRecoveryAssessment x=new DisasterRecoveryAssessment(); x.setApplicationName(r.applicationName());x.setPrimaryCloud(r.primaryCloud());x.setRecoveryCloud(r.recoveryCloud());x.setRtoMinutes(r.rtoMinutes());x.setRpoMinutes(r.rpoMinutes());x.setReadinessScore(score);x.setRecoveryProbability(Math.min(99.0,score*.98));x.setRiskLevel(score>=85?"LOW":score>=65?"MEDIUM":"HIGH");x.setBackupHealth(r.backupScore()>=80?"HEALTHY":r.backupScore()>=60?"WARNING":"CRITICAL");x.setFailoverReadiness(r.failoverScore()>=80?"READY":r.failoverScore()>=60?"PARTIALLY_READY":"NOT_READY");return assessments.save(x);
 }
 public RecoverySimulation simulate(RecoverySimulationRequest r){int estimate=Math.max(1,(int)Math.round(r.targetRtoMinutes()*(1.5-r.infrastructureHealth()/200.0))); boolean ok=estimate<=r.targetRtoMinutes(); RecoverySimulation x=new RecoverySimulation();x.setApplicationName(r.applicationName());x.setScenario(r.scenario());x.setEstimatedRecoveryMinutes(estimate);x.setAchievedRtoMinutes(estimate);x.setDataLossMinutes(Math.max(0,r.targetRpoMinutes()-(r.infrastructureHealth()/20)));x.setStatus(ok?"PASSED":"FAILED");x.setRecoveryPlan("1. Declare incident; 2. Isolate failure; 3. Activate recovery region; 4. Restore data; 5. Validate health; 6. Resume traffic; 7. Record audit evidence");return simulations.save(x);}
 public BusinessImpactAnalysis analyzeImpact(BusinessImpactRequest r){BusinessImpactAnalysis x=new BusinessImpactAnalysis();x.setApplicationName(r.applicationName());x.setIncidentType(r.incidentType());x.setAffectedServices(r.affectedServices());x.setEstimatedUsersAffected(r.estimatedUsersAffected());x.setEstimatedFinancialImpact(r.estimatedFinancialImpact());String sev=r.affectedServices()>=10||r.estimatedUsersAffected()>=10000||r.estimatedFinancialImpact()>=100000?"CRITICAL":r.affectedServices()>=5||r.estimatedUsersAffected()>=1000?"HIGH":"MODERATE";x.setSeverity(sev);x.setContinuityRecommendation(sev.equals("CRITICAL")?"Invoke DR plan, prioritize tier-1 services, notify continuity leadership, and validate recovery checkpoints.":"Monitor service health, execute the approved recovery playbook, and validate RTO/RPO.");return impacts.save(x);}
 @Transactional(readOnly=true) public DisasterRecoveryOverviewResponse overview(){var aa=assessments.findAll();var ss=simulations.findAll();double avg=aa.stream().mapToInt(DisasterRecoveryAssessment::getReadinessScore).average().orElse(0);long passed=ss.stream().filter(x->"PASSED".equals(x.getStatus())).count();String state=avg>=85?"RESILIENT":avg>=65?"AT_RISK":"CRITICAL";return new DisasterRecoveryOverviewResponse(aa.size(),ss.size(),passed,impacts.count(),Math.round(avg*100.0)/100.0,state);}
 @Transactional(readOnly=true) public Page<DisasterRecoveryAssessment> assessments(Pageable p){return assessments.findAll(p);} @Transactional(readOnly=true) public Page<RecoverySimulation> simulations(Pageable p){return simulations.findAll(p);} @Transactional(readOnly=true) public Page<BusinessImpactAnalysis> impacts(Pageable p){return impacts.findAll(p);}
}
