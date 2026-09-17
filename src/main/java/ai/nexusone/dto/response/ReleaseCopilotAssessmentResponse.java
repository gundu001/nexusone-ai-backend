package ai.nexusone.dto.response;
import java.util.List;
public record ReleaseCopilotAssessmentResponse(Long releaseId,String recommendation,String decision,double confidence,String riskLevel,boolean approvalRequired,List<String>evidence,List<String>actions) {}
