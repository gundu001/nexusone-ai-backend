package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="governance_security_assessments", indexes={
 @Index(name="idx_gov_security_status",columnList="status"),
 @Index(name="idx_gov_security_assessed",columnList="assessedAt")
})
public class GovernanceSecurityAssessment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String applicationName;
 @Column(nullable=false) private String environment;
 @Column(nullable=false) private String complianceFramework;
 private boolean releaseApproved;
 private boolean securityScanPassed;
 private int criticalVulnerabilities;
 private int highVulnerabilities;
 private int complianceChecksPassed;
 private int complianceChecksFailed;
 private boolean auditEvidenceAvailable;
 private boolean encryptionEnabled;
 private boolean leastPrivilegeApplied;
 private double policyCoveragePercent;
 private int governanceScore;
 private int complianceScore;
 private int securityScore;
 private int overallScore;
 private int policyViolationCount;
 @Column(nullable=false) private String riskLevel;
 @Column(nullable=false) private String status;
 @Column(nullable=false) private String auditReadiness;
 @Column(nullable=false) private String recommendedAction;
 @Column(length=2500) private String recommendation;
 @Column(length=2500) private String evidenceSummary;
 @Column(nullable=false) private LocalDateTime assessedAt;
 @PrePersist void prePersist(){if(assessedAt==null)assessedAt=LocalDateTime.now();}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
 public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
 public String getComplianceFramework(){return complianceFramework;} public void setComplianceFramework(String v){complianceFramework=v;}
 public boolean isReleaseApproved(){return releaseApproved;} public void setReleaseApproved(boolean v){releaseApproved=v;}
 public boolean isSecurityScanPassed(){return securityScanPassed;} public void setSecurityScanPassed(boolean v){securityScanPassed=v;}
 public int getCriticalVulnerabilities(){return criticalVulnerabilities;} public void setCriticalVulnerabilities(int v){criticalVulnerabilities=v;}
 public int getHighVulnerabilities(){return highVulnerabilities;} public void setHighVulnerabilities(int v){highVulnerabilities=v;}
 public int getComplianceChecksPassed(){return complianceChecksPassed;} public void setComplianceChecksPassed(int v){complianceChecksPassed=v;}
 public int getComplianceChecksFailed(){return complianceChecksFailed;} public void setComplianceChecksFailed(int v){complianceChecksFailed=v;}
 public boolean isAuditEvidenceAvailable(){return auditEvidenceAvailable;} public void setAuditEvidenceAvailable(boolean v){auditEvidenceAvailable=v;}
 public boolean isEncryptionEnabled(){return encryptionEnabled;} public void setEncryptionEnabled(boolean v){encryptionEnabled=v;}
 public boolean isLeastPrivilegeApplied(){return leastPrivilegeApplied;} public void setLeastPrivilegeApplied(boolean v){leastPrivilegeApplied=v;}
 public double getPolicyCoveragePercent(){return policyCoveragePercent;} public void setPolicyCoveragePercent(double v){policyCoveragePercent=v;}
 public int getGovernanceScore(){return governanceScore;} public void setGovernanceScore(int v){governanceScore=v;}
 public int getComplianceScore(){return complianceScore;} public void setComplianceScore(int v){complianceScore=v;}
 public int getSecurityScore(){return securityScore;} public void setSecurityScore(int v){securityScore=v;}
 public int getOverallScore(){return overallScore;} public void setOverallScore(int v){overallScore=v;}
 public int getPolicyViolationCount(){return policyViolationCount;} public void setPolicyViolationCount(int v){policyViolationCount=v;}
 public String getRiskLevel(){return riskLevel;} public void setRiskLevel(String v){riskLevel=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public String getAuditReadiness(){return auditReadiness;} public void setAuditReadiness(String v){auditReadiness=v;}
 public String getRecommendedAction(){return recommendedAction;} public void setRecommendedAction(String v){recommendedAction=v;}
 public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;}
 public String getEvidenceSummary(){return evidenceSummary;} public void setEvidenceSummary(String v){evidenceSummary=v;}
 public LocalDateTime getAssessedAt(){return assessedAt;} public void setAssessedAt(LocalDateTime v){assessedAt=v;}
}
