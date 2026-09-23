package ai.nexusone.dto;
import java.util.List;
public record ReasoningResponse(String query,String conclusion,String recommendedAction,double confidenceScore,List<Long> supportingMemoryIds,List<String> evidence) {}
