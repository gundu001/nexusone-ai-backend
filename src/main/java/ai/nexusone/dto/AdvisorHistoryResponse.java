package ai.nexusone.dto;
import java.time.LocalDateTime;
public class AdvisorHistoryResponse {
    private Long id; private String repositoryName; private Integer riskScore; private String severity;
    private Integer confidenceScore; private String deploymentDecision; private LocalDateTime generatedAt;
    public AdvisorHistoryResponse() {}
    public AdvisorHistoryResponse(Long id, String repositoryName, Integer riskScore, String severity,
                                  Integer confidenceScore, String deploymentDecision, LocalDateTime generatedAt) {
        this.id=id; this.repositoryName=repositoryName; this.riskScore=riskScore; this.severity=severity;
        this.confidenceScore=confidenceScore; this.deploymentDecision=deploymentDecision; this.generatedAt=generatedAt;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getRepositoryName(){return repositoryName;} public void setRepositoryName(String v){repositoryName=v;}
    public Integer getRiskScore(){return riskScore;} public void setRiskScore(Integer v){riskScore=v;}
    public String getSeverity(){return severity;} public void setSeverity(String v){severity=v;}
    public Integer getConfidenceScore(){return confidenceScore;} public void setConfidenceScore(Integer v){confidenceScore=v;}
    public String getDeploymentDecision(){return deploymentDecision;} public void setDeploymentDecision(String v){deploymentDecision=v;}
    public LocalDateTime getGeneratedAt(){return generatedAt;} public void setGeneratedAt(LocalDateTime v){generatedAt=v;}
}
