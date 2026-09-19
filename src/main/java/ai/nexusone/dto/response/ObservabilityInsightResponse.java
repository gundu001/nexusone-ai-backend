package ai.nexusone.dto.response;
import java.time.Instant; import java.util.List;
public record ObservabilityInsightResponse(String serviceName,String question,String observation,String impact,String recommendation,double confidence,String severity,List<String> correlations,List<String> actions,Instant createdAt) {}
