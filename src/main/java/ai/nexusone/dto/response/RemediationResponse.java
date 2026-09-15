package ai.nexusone.dto.response; import java.util.List;
public record RemediationResponse(String incidentId,String recommendation,String riskLevel,boolean approvalRequired,List<RemediationStep> steps){public record RemediationStep(int order,String action,String command,boolean automated){}}
