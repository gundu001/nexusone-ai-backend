package ai.nexusone.service;

import ai.nexusone.dto.request.ExecutiveIntelligenceRequest;
import ai.nexusone.dto.response.ExecutiveIntelligenceOverviewResponse;
import ai.nexusone.entity.ExecutiveIntelligenceAssessment;
import ai.nexusone.repository.ExecutiveIntelligenceAssessmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service @Transactional
public class ExecutiveDecisionIntelligenceService {
    private final ExecutiveIntelligenceAssessmentRepository repository;
    public ExecutiveDecisionIntelligenceService(ExecutiveIntelligenceAssessmentRepository repository){this.repository=repository;}
    public ExecutiveIntelligenceAssessment generate(ExecutiveIntelligenceRequest r){
        int enterprise=(int)Math.round(r.sreScore()*.20+r.platformScore()*.15+r.governanceScore()*.15+r.securityScore()*.15+r.finOpsScore()*.10+r.disasterRecoveryScore()*.15+r.autonomousOperationsScore()*.10);
        int readiness=(int)Math.round(r.sreScore()*.20+r.governanceScore()*.20+r.securityScore()*.20+r.disasterRecoveryScore()*.25+r.finOpsScore()*.15);
        int operations=(int)Math.round(r.sreScore()*.30+r.platformScore()*.25+r.disasterRecoveryScore()*.20+r.autonomousOperationsScore()*.25);
        int maturity=(int)Math.round(r.platformScore()*.25+r.governanceScore()*.20+r.securityScore()*.20+r.finOpsScore()*.15+r.autonomousOperationsScore()*.20);
        String risk=r.openCriticalRisks()>0||enterprise<60?"HIGH":enterprise<80?"MEDIUM":"LOW";
        String status="HIGH".equals(risk)?"EXECUTIVE_ACTION_REQUIRED":r.pendingExecutiveDecisions()>0?"DECISION_PENDING":enterprise>=85?"ENTERPRISE_READY":"IMPROVEMENT_REQUIRED";
        String decision=decision(r,enterprise);
        ExecutiveIntelligenceAssessment x=new ExecutiveIntelligenceAssessment();
        x.setOrganizationName(r.organizationName());x.setReportingPeriod(r.reportingPeriod());x.setSreScore(r.sreScore());x.setPlatformScore(r.platformScore());x.setGovernanceScore(r.governanceScore());x.setSecurityScore(r.securityScore());x.setFinOpsScore(r.finOpsScore());x.setDisasterRecoveryScore(r.disasterRecoveryScore());x.setAutonomousOperationsScore(r.autonomousOperationsScore());x.setOpenCriticalRisks(r.openCriticalRisks());x.setPendingExecutiveDecisions(r.pendingExecutiveDecisions());x.setEnterpriseHealthScore(enterprise);x.setBusinessReadinessScore(readiness);x.setOperationalExcellenceScore(operations);x.setTechnologyMaturityScore(maturity);x.setRiskLevel(risk);x.setStatus(status);x.setRecommendedDecision(decision);x.setExecutiveSummary("Enterprise health "+enterprise+", business readiness "+readiness+", operational excellence "+operations+", technology maturity "+maturity+".");x.setRecommendation(recommendation(decision));
        return repository.save(x);
    }
    @Transactional(readOnly=true) public ExecutiveIntelligenceOverviewResponse overview(){
        List<ExecutiveIntelligenceAssessment> all=repository.findAll();
        double health=all.stream().mapToInt(ExecutiveIntelligenceAssessment::getEnterpriseHealthScore).average().orElse(0),readiness=all.stream().mapToInt(ExecutiveIntelligenceAssessment::getBusinessReadinessScore).average().orElse(0),ops=all.stream().mapToInt(ExecutiveIntelligenceAssessment::getOperationalExcellenceScore).average().orElse(0),maturity=all.stream().mapToInt(ExecutiveIntelligenceAssessment::getTechnologyMaturityScore).average().orElse(0);
        long high=repository.countByRiskLevel("HIGH"),actions=repository.countByStatus("EXECUTIVE_ACTION_REQUIRED")+repository.countByStatus("DECISION_PENDING");
        String state=all.isEmpty()?"NO_DATA":high>0?"EXECUTIVE_ATTENTION_REQUIRED":actions>0?"DECISION_PENDING":health>=85?"ENTERPRISE_READY":"IMPROVEMENT_REQUIRED";
        return new ExecutiveIntelligenceOverviewResponse(all.size(),round(health),round(readiness),round(ops),round(maturity),high,actions,state);
    }
    @Transactional(readOnly=true) public Page<ExecutiveIntelligenceAssessment> history(Pageable p){return repository.findAll(p);}
    @Transactional(readOnly=true) public List<ExecutiveIntelligenceAssessment> recommendations(){return repository.findTop10ByOrderByGeneratedAtDesc();}
    @Transactional(readOnly=true) public ExecutiveIntelligenceAssessment get(Long id){return repository.findById(id).orElseThrow(()->new NoSuchElementException("Executive assessment not found: "+id));}
    private String decision(ExecutiveIntelligenceRequest r,int enterprise){if(r.openCriticalRisks()>0)return "RESOLVE_CRITICAL_ENTERPRISE_RISKS";if(r.securityScore()<80||r.governanceScore()<80)return "STRENGTHEN_GOVERNANCE_AND_SECURITY";if(r.disasterRecoveryScore()<80)return "IMPROVE_BUSINESS_CONTINUITY_READINESS";if(r.finOpsScore()<80)return "ACCELERATE_COST_OPTIMIZATION";if(r.platformScore()<80||r.sreScore()<80)return "IMPROVE_PLATFORM_RELIABILITY";if(r.pendingExecutiveDecisions()>0)return "COMPLETE_PENDING_EXECUTIVE_DECISIONS";return enterprise>=85?"CONTINUE_STRATEGIC_EXECUTION":"PRIORITIZE_ENTERPRISE_IMPROVEMENTS";}
    private String recommendation(String d){return switch(d){case"RESOLVE_CRITICAL_ENTERPRISE_RISKS"->"Assign executive owners and resolve critical risks before expanding autonomous operations.";case"STRENGTHEN_GOVERNANCE_AND_SECURITY"->"Prioritize control remediation, security hardening and evidence readiness.";case"IMPROVE_BUSINESS_CONTINUITY_READINESS"->"Fund recovery validation, dependency coverage and continuity exercises.";case"ACCELERATE_COST_OPTIMIZATION"->"Prioritize confirmed FinOps opportunities and track realized savings.";case"IMPROVE_PLATFORM_RELIABILITY"->"Increase platform reliability, SLO performance and engineering automation.";case"COMPLETE_PENDING_EXECUTIVE_DECISIONS"->"Review pending decisions, assign accountable owners and record outcomes.";default->"Enterprise indicators are healthy. Continue governed execution and monitor trend changes.";};}
    private double round(double v){return Math.round(v*100.0)/100.0;}
}
