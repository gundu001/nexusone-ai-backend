package ai.nexusone.dto.response;
public record ChangeRiskAssessmentResponse(Long changeId,String application,String changeType,int filesChanged,int linesChanged,int historicalFailures,double riskScore,String riskLevel,String status) {}
