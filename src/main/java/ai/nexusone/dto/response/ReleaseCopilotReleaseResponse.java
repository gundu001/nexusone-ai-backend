package ai.nexusone.dto.response;
import java.util.List;
public record ReleaseCopilotReleaseResponse(Long releaseId,String application,String version,String environment,double readinessScore,String readinessLevel,String decision,double changeRiskScore,double securityScore,List<String>signals) {}
