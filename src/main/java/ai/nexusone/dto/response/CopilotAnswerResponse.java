package ai.nexusone.dto.response; import java.time.Instant; import java.util.List;
public record CopilotAnswerResponse(String incidentId,String question,String answer,double confidence,List<String> sources,Instant generatedAt){}
