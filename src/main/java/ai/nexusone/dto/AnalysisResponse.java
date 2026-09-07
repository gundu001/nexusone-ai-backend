package ai.nexusone.dto;
import java.util.List;
public record AnalysisResponse(String repositoryUrl,String branch,int healthScore,String status,List<RiskFinding> findings){}
