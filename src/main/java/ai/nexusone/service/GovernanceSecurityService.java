package ai.nexusone.service;

import ai.nexusone.dto.request.GovernanceSecurityAssessmentRequest;
import ai.nexusone.dto.response.GovernanceSecurityOverviewResponse;
import ai.nexusone.entity.GovernanceSecurityAssessment;
import ai.nexusone.repository.GovernanceSecurityAssessmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service @Transactional
public class GovernanceSecurityService {
 private final GovernanceSecurityAssessmentRepository repository;
 public GovernanceSecurityService(GovernanceSecurityAssessmentRepository repository){this.repository=repository;}
 public GovernanceSecurityAssessment assess(GovernanceSecurityAssessmentRequest r){
  int totalChecks=r.complianceChecksPassed()+r.complianceChecksFailed();
  double complianceRatio=totalChecks==0?0:r.complianceChecksPassed()*100.0/totalChecks;
  int governance=(int)Math.round((r.releaseApproved()?30:0)+(r.auditEvidenceAvailable()?25:0)+(r.policyCoveragePercent()*.45));
  int compliance=(int)Math.round(complianceRatio*.8+(r.auditEvidenceAvailable()?20:0));
  int security=100-r.criticalVulnerabilities()*35-r.highVulnerabilities()*10;
  if(!r.securityScanPassed())security-=20;if(!r.encryptionEnabled())security-=15;if(!r.leastPrivilegeApplied())security-=15;
  security=Math.max(0,Math.min(100,security));
  int overall=(int)Math.round(governance*.34+compliance*.33+security*.33);
  int violations=(r.releaseApproved()?0:1)+(r.securityScanPassed()?0:1)+r.complianceChecksFailed()+(r.auditEvidenceAvailable()?0:1)+(r.encryptionEnabled()?0:1)+(r.leastPrivilegeApplied()?0:1);
  String risk=r.criticalVulnerabilities()>0||overall<60?"CRITICAL":overall<80?"HIGH":overall<90?"MEDIUM":"LOW";
  String status=violations==0&&overall>=85?"COMPLIANT":overall>=65?"ACTION_REQUIRED":"NON_COMPLIANT";
  String audit=r.auditEvidenceAvailable()&&r.policyCoveragePercent()>=80?"READY":"NOT_READY";
  String action=action(r,status);
  GovernanceSecurityAssessment x=new GovernanceSecurityAssessment();
  x.setApplicationName(r.applicationName());x.setEnvironment(r.environment());x.setComplianceFramework(r.complianceFramework());
  x.setReleaseApproved(r.releaseApproved());x.setSecurityScanPassed(r.securityScanPassed());x.setCriticalVulnerabilities(r.criticalVulnerabilities());x.setHighVulnerabilities(r.highVulnerabilities());
  x.setComplianceChecksPassed(r.complianceChecksPassed());x.setComplianceChecksFailed(r.complianceChecksFailed());x.setAuditEvidenceAvailable(r.auditEvidenceAvailable());x.setEncryptionEnabled(r.encryptionEnabled());x.setLeastPrivilegeApplied(r.leastPrivilegeApplied());x.setPolicyCoveragePercent(round(r.policyCoveragePercent()));
  x.setGovernanceScore(governance);x.setComplianceScore(compliance);x.setSecurityScore(security);x.setOverallScore(overall);x.setPolicyViolationCount(violations);x.setRiskLevel(risk);x.setStatus(status);x.setAuditReadiness(audit);x.setRecommendedAction(action);x.setRecommendation(recommendation(action));
  x.setEvidenceSummary("Framework="+r.complianceFramework()+"; passed="+r.complianceChecksPassed()+"; failed="+r.complianceChecksFailed()+"; critical vulnerabilities="+r.criticalVulnerabilities()+"; high vulnerabilities="+r.highVulnerabilities()+".");
  return repository.save(x);
 }
 @Transactional(readOnly=true) public GovernanceSecurityOverviewResponse overview(){
  List<GovernanceSecurityAssessment> all=repository.findAll();
  double g=all.stream().mapToInt(GovernanceSecurityAssessment::getGovernanceScore).average().orElse(0),c=all.stream().mapToInt(GovernanceSecurityAssessment::getComplianceScore).average().orElse(0),s=all.stream().mapToInt(GovernanceSecurityAssessment::getSecurityScore).average().orElse(0);
  long compliant=repository.countByStatus("COMPLIANT"),nonCompliant=repository.countByStatus("NON_COMPLIANT"),critical=repository.countByRiskLevel("CRITICAL");
  long violations=all.stream().mapToLong(GovernanceSecurityAssessment::getPolicyViolationCount).sum();
  String state=all.isEmpty()?"NO_DATA":critical>0||nonCompliant>0?"ATTENTION_REQUIRED":violations>0?"REMEDIATION_REQUIRED":"COMPLIANT";
  return new GovernanceSecurityOverviewResponse(all.size(),round(g),round(c),round(s),compliant,nonCompliant,critical,violations,state);
 }
 @Transactional(readOnly=true) public Page<GovernanceSecurityAssessment> history(Pageable p){return repository.findAll(p);}
 @Transactional(readOnly=true) public List<GovernanceSecurityAssessment> violations(){return repository.findAll().stream().filter(x->x.getPolicyViolationCount()>0).toList();}
 @Transactional(readOnly=true) public List<GovernanceSecurityAssessment> recommendations(){return repository.findTop10ByOrderByAssessedAtDesc();}
 @Transactional(readOnly=true) public GovernanceSecurityAssessment get(Long id){return repository.findById(id).orElseThrow(()->new NoSuchElementException("Governance assessment not found: "+id));}
 private String action(GovernanceSecurityAssessmentRequest r,String status){if(r.criticalVulnerabilities()>0)return "BLOCK_RELEASE_AND_REMEDIATE";if(!r.securityScanPassed())return "REMEDIATE_SECURITY_SCAN";if(r.complianceChecksFailed()>0)return "RESOLVE_COMPLIANCE_FAILURES";if(!r.auditEvidenceAvailable())return "COLLECT_AUDIT_EVIDENCE";if(!r.encryptionEnabled()||!r.leastPrivilegeApplied())return "HARDEN_SECURITY_CONTROLS";return "CONTINUE_GOVERNED_OPERATIONS";}
 private String recommendation(String a){return switch(a){case"BLOCK_RELEASE_AND_REMEDIATE"->"Block production release until critical vulnerabilities are remediated and rescanned.";case"REMEDIATE_SECURITY_SCAN"->"Resolve security scan findings and require a passing scan before approval.";case"RESOLVE_COMPLIANCE_FAILURES"->"Assign control owners, remediate failed checks and attach evidence.";case"COLLECT_AUDIT_EVIDENCE"->"Collect approval, scan, test and control evidence for audit readiness.";case"HARDEN_SECURITY_CONTROLS"->"Enable encryption and enforce least-privilege access before production approval.";default->"Controls are satisfactory. Continue governed monitoring and evidence retention.";};}
 private double round(double v){return Math.round(v*100.0)/100.0;}
}
